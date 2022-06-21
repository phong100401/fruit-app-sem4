package com.example.restapiprojectsem4.service.order;

import com.example.restapiprojectsem4.dto.OrderDTO;
import com.example.restapiprojectsem4.dto.OrderDetailDTO;
import com.example.restapiprojectsem4.entity.Order;
import com.example.restapiprojectsem4.entity.OrderDetail;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Set;

public interface OrderService {
    Order getCart(int accountId);
    Order getCart1();
    Order addToCart(int accountId, OrderDetailDTO orderDetailDTO);
    Order addToCart1(OrderDetailDTO orderDetailDTO);
    Order updateCart(int accountId, Set<OrderDetail> list);
    Order controlValue(int accountId, OrderDetailDTO orderDetailDTO);
    Order controlValue1(OrderDetailDTO orderDetailDTO);
    Order removeItem(int accountId, int productId);
    Order removeItem1(int productId);
    Order proceedOrder(int accountId, OrderDTO orderDTO);
    Order proceedOrder1(OrderDTO orderDTO);
    Order updateStatus(int id, int status);
    Page findAll(ObjectFilter objectFilter);
    Order findById(int id);
    Order delete(int id);
    Order updateCheckOut(int id, boolean isCheckout);
    List<Order> getHistoryOrder();
}
