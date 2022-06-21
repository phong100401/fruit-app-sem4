package com.example.restapiprojectsem4.response;

import com.example.restapiprojectsem4.service.order.OrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyPage{
    private int page;
    private int pageSize;
    private int totalPage;
    private Object content;
}
