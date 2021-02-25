package com.newbie.springsecurityjwt.model.developer;


import com.newbie.springsecurityjwt.model.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table (name="accounts")
public class Account extends BaseEntity {

    @Column(name="data")
    private String data;

    @OneToOne (mappedBy = "")
    @JoinColumn (name="user_id")
    private User user;

    @OneToOne (mappedBy="account")
    private Developer developer;

    @Override
    public String toString() {
        return "{" +
                "data: " + data + ", " +
                "user: " + user.getId() + "}";
    }
}
