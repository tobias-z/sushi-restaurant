package facades;

import dtos.MealDTO;
import entities.meal.MealRepository;
import java.util.List;
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
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public void generateAllMeals() throws WebApplicationException {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}