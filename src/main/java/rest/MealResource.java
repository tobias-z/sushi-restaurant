package rest;

import dtos.MealDTO;
import dtos.RestaurantDTO;
import entities.meal.MealRepository;
import facades.MealFacade;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import rest.provider.Provider;

@Path("meals")
public class MealResource extends Provider {

    private static final MealRepository REPO = MealFacade.getInstance(EMF);

    @Override
    public Response getAll() {
        List<MealDTO> mealDTOS = REPO.getAllMeals();
        RestaurantDTO restaurantDTO = new RestaurantDTO(mealDTOS);
        return Response.ok(GSON.toJson(restaurantDTO)).build();
    }

    @GET
    @Path("/generate")
    public Response generateAllMeals() {
        REPO.generateAllMeals();
        return Response.ok().build();
    }

    @Override
    public Response create(String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response getById(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response update(int id, String jsonBody) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }

    @Override
    public Response delete(int id) {
        //TODO (tz): implement this!
        throw new UnsupportedOperationException("Not yet implemented!");
    }
}