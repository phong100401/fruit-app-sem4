package com.example.restapiprojectsem4.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orderDetails")
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id = new OrderDetailId();
    @Column(name = "orderId",updatable = false,insertable = false)
    private int orderId;
    @Column(name = "productId",updatable = false,insertable = false)
    private int productId;
    @Column(name = "productName")
    private String productName;
    @Column(name = "thumbnailProduct")
    private String thumbnailProduct;
    @Column(name = "unitPrice")
    private double unitPrice;
    @Column(name = "quantity")
    private int quantity;

    //product
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;

    //order
    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order order;
}

