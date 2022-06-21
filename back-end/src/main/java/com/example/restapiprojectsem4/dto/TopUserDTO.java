package com.example.restapiprojectsem4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopUserDTO {
    private String username;
    private int accountId;
    private double totalPrice;
}
