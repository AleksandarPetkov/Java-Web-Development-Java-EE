package sboj.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
//•	Has a Sector
//        •	Has a Profession
//        •	Has a Salary
//        •	Has a Description

@Entity
public class JobApplication extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sector sector;

    @Column(nullable = false)
    private String profession;

    @Column(nullable = false)
    private BigDecimal salary;

    @Column()
    private String description;

    public JobApplication() {
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

