package entities.meal;

import dtos.MealDTO;
import java.util.List;
import javax.ws.rs.WebApplicationException;

public interface MealRepository {

    List<MealDTO> getAllMeals() throws WebApplicationException;

    void generateAllMeals() throws WebApplicationException;

}
