package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.MealDTO;
import entities.meal.Category;
import entities.meal.Meal;
import entities.meal.MealRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

class MealFacadeTest {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactoryForTest();
    private static final MealRepository REPO = MealFacade.getInstance(EMF);

    private static Meal m1, m2, m3;

    @BeforeEach
    void setUp() {
        m1 = new Meal("Chicken", 40, Category.STICK, "this is an awesome chicken");
        m2 = new Meal("Some Maki", 60, Category.MAKI, "Get your own Maki!");
        m3 = new Meal("Sashimi sushi", 60, Category.SASHIMI, "Some Sashimi has never hurt anyone");

        EntityManager em = EMF.createEntityManager();
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

    private void clearMeals() {
        EntityManager em = EMF.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Meal.deleteAllRows").executeUpdate();
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    void tearDown() {
        clearMeals();
    }

    @Nested
    @DisplayName("Get all meals")
    class GetAllMeals {

        @Test
        @DisplayName("should return all of the meals")
        void shouldReturnAllOfTheMeals() {
            List<MealDTO> actual = REPO.getAllMeals();
            assertEquals(3, actual.size());
        }

        @Test
        @DisplayName("should not throw error if no meals are present")
        void shouldNotThrowErrorIfNoMealsArePresent() {
            clearMeals();
            assertDoesNotThrow(() -> REPO.getAllMeals());
        }

    }

    @Nested
    @DisplayName("generate all meals")
    class generateAllMeals {

        @BeforeEach
        void setup() {
            clearMeals();
            REPO.generateAllMeals();
        }

        @Test
        @DisplayName("should generate all of the meals")
        void shouldGenerateAllOfTheMeals() {
            List<MealDTO> generatedMeals = REPO.getAllMeals();
            assertFalse(generatedMeals.isEmpty());
        }

        @Test
        @DisplayName("should be a list of multiple meals")
        void shouldBeAListOfMultipleMeals() {
            List<MealDTO> generatedMeals = REPO.getAllMeals();
            assertTrue(generatedMeals.size() > 1);
        }

        @Test
        @DisplayName("should have multiple categories of meals")
        void shouldHaveMultipleCategoriesOfMeals() {
            List<MealDTO> generatedMeals = REPO.getAllMeals();
            List<Category> categories = new ArrayList<>();
            for (MealDTO meal : generatedMeals) {
                if (!categories.contains(meal.getCategory())) {
                    categories.add(meal.getCategory());
                }
            }
            assertTrue(categories.size() > 1);
        }

    }

}