package com.newbie.springsecurityjwt.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.newbie.springsecurityjwt.model.developer.Account;
import com.newbie.springsecurityjwt.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Service
public class AccountDto {

    private Long id;
    private String data;
    private UserDto userDto;

    @Autowired
    @JsonIgnore
    private UserService userService;

    public Account toAccount() {
        Account account = new Account();
        account.setId(id);
        account.setData(data);
        account.setUser(userDto.toUser());
        return account;
    }

    public static AccountDto fromAccount(Account account){
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setData(account.getData());
        accountDto.setUserDto(UserDto.fromUser(account.getUser()));
        return accountDto;
    }
}
