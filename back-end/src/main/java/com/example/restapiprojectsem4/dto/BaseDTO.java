package com.example.restapiprojectsem4.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class BaseDTO {
    private Integer page;
    private Integer size;
}
