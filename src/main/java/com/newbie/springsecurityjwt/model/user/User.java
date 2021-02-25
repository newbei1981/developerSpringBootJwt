package com.newbie.springsecurityjwt.model.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


/**
 * Simple domain object that represents application user.
 */

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name="phone_number")
    String phoneNumber;

    String sms;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sms='" + sms + '\'' +
                ", roles=" + roles +
                '}';
    }
}
