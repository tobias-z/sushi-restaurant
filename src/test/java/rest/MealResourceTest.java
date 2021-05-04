package rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertFalse;

import dtos.MealDTO;
import entities.meal.Category;
import entities.meal.Meal;
import io.restassured.http.ContentType;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MealResourceTest extends SetupRestTests {

    private static Meal m1, m2, m3;

    @BeforeAll
    static void setUpClass() {
        setupServer();
    }

    @AfterAll
    static void closeTestServer() {
        shutdownServer();
    }

    @BeforeEach
    void setUp() {
        m1 = new Meal("Chicken", 40, Category.STICK, "this is an awesome chicken");
        m2 = new Meal("Some Maki", 60, Category.MAKI, "Get your own Maki!");
        m3 = new Meal("Sashimi sushi", 60, Category.SASHIMI, "Some Sashimi has never hurt anyone");

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(m1);
            em.persist(m2);
            em.persist(m3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Meal.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Nested
    @DisplayName("get all")
    class GetAll {

        @Test
        @DisplayName("should get all of the meals that we have")
        void shouldGetAllOfTheMealsThatWeHave() {
            given()
                .contentType(ContentType.JSON)
                .when()
                .get("/meals")
                .then()
                .statusCode(200);
        }

        @Test
        @DisplayName("should have a restaurant name on in")
        void shouldHaveARestaurantNameOnIn() {
            given()
                .contentType(ContentType.JSON)
                .when()
                .get("/meals")
                .then()
                .body("name", equalTo("Sushi Lovers"));
        }

        @Test
        @DisplayName("should have a list of meals on it")
        void shouldHaveAListOfMealsOnIt() {
            List<MealDTO> meals = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/meals")
                .then()
                .extract().body().jsonPath().getList("meals", MealDTO.class);

            assertFalse(meals.isEmpty());
        }
    }

    @Nested
    @DisplayName("generate all meals")
    class GenerateAllMeals {

        @Test
        @DisplayName("should generate meals")
        void shouldGenerateMeals() {
            given()
                .contentType(ContentType.JSON)
                .when()
                .get("/meals/generate")
                .then()
                .statusCode(200);
        }
    }

}