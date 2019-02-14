package app.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "cats")
public class Cat extends BaseEntity {

    @Column(nullable = false)
    @Length(min = 2, max = 10)
    private String name;

    @Column(nullable = false)
    @Length(min = 5, max = 20)
    private String breed;

    @Column
    private String color;

    @Column(nullable = false)
    @Min(value = 1, message = "Age should not be less than 1")
    @Max(value = 31, message = "Age should not be greater than 31")
    private int age;

    @Column
    private String genger;

    @Column(nullable = false)
    @Min(value = 0)
    private BigDecimal price;

    @Column(name = "added_on", columnDefinition = "DATETIME")
    private LocalDate addedOn;

    private boolean hasPassport;

    public Cat() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGenger() {
        return genger;
    }

    public void setGenger(String genger) {
        this.genger = genger;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDate addedOn) {
        this.addedOn = addedOn;
    }

    public boolean isHasPassport() {
        return hasPassport;
    }

    public void setHasPassport(boolean hasPassport) {
        this.hasPassport = hasPassport;
    }
}
