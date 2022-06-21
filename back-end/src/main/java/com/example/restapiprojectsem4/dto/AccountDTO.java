package com.example.restapiprojectsem4.dto;

import com.example.restapiprojectsem4.entity.Account;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    private String username;
    private String password;
    private String confirmPassword;
    private String name;
    private String phone;
    private String address;
}
