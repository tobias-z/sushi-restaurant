package facades;

import dtos.MealDTO;
import entities.meal.Category;
import entities.meal.Meal;
import entities.meal.MealRepository;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.WebApplicationException;

public class MealFacade implements MealRepository {

    private static MealFacade instance;
    private static EntityManagerFactory emf;

    private MealFacade() {
    }

    public static MealFacade getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new MealFacade();
        }
        return instance;
    }

    @Override
    public List<MealDTO> getAllMeals() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            List<Meal> meals = em.createQuery("SELECT m FROM Meal m", Meal.class).getResultList();
            return MealDTO.getFromMealList(meals);
        } catch (Exception e) {
            throw new WebApplicationException("Unable to find meals for Sushi Lovers", 500);
        } finally {
            em.close();
        }
    }

    private List<Meal> getTransientMeals() {
        List<Meal> meals = new ArrayList<>();
        meals.add(new Meal("Hosomaki", 9.99, Category.MAKI, "Varying thin sushi rolls"));
        meals.add(new Meal("Chicken paradise", 11.99, Category.MAKI, "Sushi rolls with crunchy chicken inside"));
        meals.add(new Meal("Avocado lover", 10.99, Category.MAKI, "Sushi rolls surrounded with avocado"));
        meals.add(new Meal("Shrimp manifesto", 12.99, Category.MAKI, "Spicy rolls with shrimps"));
        meals.add(new Meal("The Vegetarian", 8.99, Category.MAKI, "Rolls filled with potato avocado and garlic"));

        meals.add(new Meal("Shake yaki", 5.99, Category.NIGIRI, "Grilled salmon"));
        meals.add(new Meal("Shake", 5.50, Category.NIGIRI, "Salmon"));
        meals.add(new Meal("Ebi", 4.99, Category.NIGIRI, "Shrimp"));
        meals.add(new Meal("Asparagusium", 4.99, Category.NIGIRI, "Slightly grilled asparagus"));
        meals.add(new Meal("Tuna", 5.99, Category.NIGIRI, "Tuna is great"));

        meals.add(new Meal("Bacon sticks", 8.99, Category.STICK, "Asparagus surrounded with bacon"));
        meals.add(new Meal("Lemon chicken", 9.99, Category.STICK, "Grilled chicken wings with lemon source on top"));
        meals.add(new Meal("The Duck", 9.99, Category.STICK, "Grilled duck"));
        meals.add(new Meal("Boneless beef", 9.99, Category.STICK, "Grilled boneless beef, perfect for a summer evening"));

        meals.add(new Meal("The Shake", 16.99, Category.SASHIMI, "Salmon peaces, great in combination with Sticks"));
        meals.add(new Meal("The King", 17.99, Category.SASHIMI, "Grilled yellowtail kingfish"));
        return meals;
    }

    @Override
    public void generateAllMeals() throws WebApplicationException {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            getTransientMeals().forEach(meal -> em.persist(meal));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new WebApplicationException("Unable to generate the meals for Sushi Lovers", 500);
        } finally {
            em.close();
        }
    }
}