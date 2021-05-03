package facades;

import dtos.MealDTO;
import entities.meal.Meal;
import entities.meal.MealRepository;
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

    @Override
    public void generateAllMeals() throws WebApplicationException {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}