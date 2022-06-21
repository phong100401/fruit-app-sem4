package com.example.restapiprojectsem4.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private String shipName;
    private int provinceId;
    private int districtId;
    private int wardId;
    private String shipAddress;
    private String shipPhone;
    private String shipNote;
    private int accountId;
}
