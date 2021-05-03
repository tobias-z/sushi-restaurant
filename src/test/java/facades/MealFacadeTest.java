package facades;

import static org.junit.jupiter.api.Assertions.*;

import dtos.MealDTO;
import entities.meal.Category;
import entities.meal.Meal;
import entities.meal.MealRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;
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
        m1 = new Meal("Chicken", 40, Category.STICK);
        m2 = new Meal("Some Maki", 60, Category.MAKI);
        m3 = new Meal("Sashimi sushi", 60, Category.SASHIMI);

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
        @DisplayName("get all meals should return all of the meals")
        void getAllMealsShouldReturnAllOfTheMeals() {
            List<MealDTO> actual = REPO.getAllMeals();
            assertEquals(3, actual.size());
        }

        @Test
        @DisplayName("get all meals should not throw error if no meals are pressent")
        void getAllMealsShouldNotThrowErrorIfNoMealsArePressent() {
            clearMeals();
            assertDoesNotThrow(() -> REPO.getAllMeals());
        }

    }

}