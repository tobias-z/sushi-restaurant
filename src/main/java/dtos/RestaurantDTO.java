package dtos;

import java.util.List;
import java.util.Objects;

public class RestaurantDTO {

    private final String name;
    private final String description;
    private List<MealDTO> meals;

    public RestaurantDTO(List<MealDTO> meals) {
        this.name = "Sushi Lovers";
        this.description = "Sushi house with amazing sushi."
            + " We have some of the best sushi in the business!";
        this.meals = meals;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<MealDTO> getMeals() {
        return meals;
    }

    public void setMeals(List<MealDTO> meals) {
        this.meals = meals;
    }

    @Override
    public String toString() {
        return "RestaurantDTO{" +
            "name='" + name + '\'' +
            ", meals=" + meals +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RestaurantDTO)) {
            return false;
        }
        RestaurantDTO that = (RestaurantDTO) o;
        return Objects.equals(getName(), that.getName()) && Objects
            .equals(getMeals(), that.getMeals());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getMeals());
    }
}
