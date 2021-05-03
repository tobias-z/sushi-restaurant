package dtos;

import entities.meal.Category;
import entities.meal.Meal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MealDTO {

    private Integer id;
    private String name;
    private double price;
    private Category category;
    private String description;

    public static List<MealDTO> getFromMealList(List<Meal> meals) {
        return meals.stream()
            .map(meal -> new MealDTO(meal))
            .collect(Collectors.toList());
    }

    public MealDTO(String name, double price, Category category, String description) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public MealDTO(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.price = meal.getPrice();
        this.category = meal.getCategory();
        this.description = meal.getDescription();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MealDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", category=" + category +
            ", description='" + description + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MealDTO)) {
            return false;
        }
        MealDTO mealDTO = (MealDTO) o;
        return Double.compare(mealDTO.getPrice(), getPrice()) == 0 && Objects
            .equals(getId(), mealDTO.getId()) && Objects.equals(getName(), mealDTO.getName())
            && getCategory() == mealDTO.getCategory();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getCategory());
    }
}
