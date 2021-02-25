package com.newbie.springsecurityjwt.model.developer;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "skills")
public class Skill extends BaseEntity {

    @Column(name = "name")
    private String name;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable (name="developer_skills",
            joinColumns=@JoinColumn (name="skill_id"),
            inverseJoinColumns=@JoinColumn(name="developer_id"))
    private Set<Developer> developers;

    @Override
    public String toString() {
        return "{" +
                "id: " + super.getId() + ", " +
                "name:" + name + "}";
    }
}
