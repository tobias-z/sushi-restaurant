package entities.meal;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "meal")
@NamedQueries({
    @NamedQuery(name = "Meal.deleteAllRows", query = "DELETE from Meal")
})
public class Meal implements Serializable {

    private static final long serialVersionUID = -4916078826882619304L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private double price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    public Meal() {
    }

    public Meal(String name, double price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
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
        return "Meal{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", price=" + price +
            ", category=" + category +
            '}';
    }
}
