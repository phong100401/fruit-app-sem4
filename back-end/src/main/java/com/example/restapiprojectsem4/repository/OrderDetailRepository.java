package com.example.restapiprojectsem4.repository;

import com.example.restapiprojectsem4.dto.OrderDetailDTO;
import com.example.restapiprojectsem4.entity.OrderDetail;
import com.example.restapiprojectsem4.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
//    @Query("select new com.example.restapiprojectsem4.dto.OrderDetailDTO(" +
//            "od.id.orderId," +
//            "od.id.productId," +
//            "od.product.thumbnail," +
//            "od.product.name," +
//            "od.quantity," +
//            "od.unitPrice ," +
//            "od.quantity * od.unitPrice)  from OrderDetail od " +
//            "inner join Order o " +
//            "on od.id.orderId = o.id " +
//            "where o.id = :orderId")
//    List<OrderDetailDTO> findOrderDetailByOrderId(@Param("orderId") int orderId);
}
