package com.example.restapiprojectsem4.entity;

import com.example.restapiprojectsem4.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "provinceId")
    private int provinceId;
    @Column(name = "districtId")
    private int districtId;
    @Column(name = "wardId")
    private int wardId;
    @Column(name = "shipPhone")
    private String shipPhone;
    @Column(name = "shipName")
    private String shipName;
    @Column(name = "shipAddress")
    private String shipAddress;
    @Column(columnDefinition = "TEXT", name = "shipNote")
    private String shipNote;
    private int status;
    @Column(name = "totalPrice")
    private double totalPrice;
    @Column(name = "isShoppingCart")
    private boolean isShoppingCart;
    @Column(name = "isCheckout")
    private boolean isCheckout;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt;
    private LocalDate deletedAt;
    private int isRemoved;
    @Column(name = "accountId")
    private int accountId;

    @ManyToOne
    @JoinColumn(name = "accountId",insertable = false,updatable = false)
    @JsonIgnore
    private Account account;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @JsonIgnore
    public int getTotal(){
        Set<OrderDetail> list = this.getOrderDetails();
        int total = 0;
        for(OrderDetail detail : list){
            total +=detail.getUnitPrice() * detail.getQuantity();
        }
        return total;
    }
    @JsonIgnore
    public void setTotalMoney(){
        this.totalPrice = getTotal();
    }

    public void removeOrderDetail(OrderDetail orderDetail){
        this.orderDetails.remove(orderDetail);
    }
}
