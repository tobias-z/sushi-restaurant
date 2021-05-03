package dtos;

import entities.meal.Category;
import entities.meal.Meal;
import java.util.Objects;

public class MealDTO {

    private Integer id;
    private String name;
    private double price;
    private Category category;

    public MealDTO(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public MealDTO(Meal meal) {
        this.id = meal.getId();
        this.name = meal.getName();
        this.price = meal.getPrice();
        this.category = meal.getCategory();
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

    @Override
    public String toString() {
        return "MealDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", category=" + category +
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
