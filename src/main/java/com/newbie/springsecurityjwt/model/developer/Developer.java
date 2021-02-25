package com.newbie.springsecurityjwt.model.developer;

import com.google.common.base.Objects;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Data
@Table(name = "developers")
public class Developer extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialty")
    private String specialty;

    @OneToOne (cascade=CascadeType.ALL)
    @JoinColumn (name="account_id")
    private Account account;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable (name="developer_skills",
            joinColumns=@JoinColumn (name="developer_id"),
            inverseJoinColumns=@JoinColumn(name="skill_id"))
    private Set<Skill> skills = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Developer developer = (Developer) o;
        return Objects.equal(firstName, developer.firstName) && Objects.equal(lastName, developer.lastName) && Objects.equal(specialty, developer.specialty);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), firstName, lastName, specialty);
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + super.getId() + ", " +
                "firstName:" + firstName + ", " +
                "lastName:" + lastName + ", " +
                "specialty:" + specialty + ", " +
                "account:" + account.toString() +
                "skills: " + skills;
    }
}
