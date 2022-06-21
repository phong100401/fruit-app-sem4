package com.example.restapiprojectsem4.repository;

import com.example.restapiprojectsem4.entity.Account;
import com.example.restapiprojectsem4.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    @Query("select o from Order o where o.accountId= :id and o.isShoppingCart = true and o.isCheckout = false ")
    Order getCart(@Param("id") int id);

    @Query("select o FROM  Order o where o.id = :id")
    Order findOrderById(@Param("id") int id);

    @Modifying
    @Transactional
    @Query("update Order o set o.status = :status where o.id = :id")
    void updateStatus(@Param("id") int id, @Param("status") int status);

    @Query("SELECT o FROM Order o" +
            " INNER JOIN OrderDetail od" +
            " ON o.id = od.id.orderId" +
            " INNER JOIN Product p" +
            " ON od.id.productId = p.id " +
            " WHERE p.name LIKE %:name%" +
            " GROUP BY o.id")
    Page<Order> findOrderByNameProduct(@Param("name") String name, Pageable pageable);

    @Modifying
    @Transactional
    @Query("update Order o set o.isRemoved = 1 where o.id = :id")
    void removeById(@Param("id") int id);

//    @Query(value = "SELECT DATE_FORMAT(created_at, \"%d\") as Day, " +
//            "DATE_FORMAT(created_at, \"%m\") as Month, " +
//            "DATE_FORMAT(created_at, \"%y\") as Year, " +
//            "SUM(total_price) FROM orders " +
//            "GROUP BY  DATE_FORMAT(created_at, \"%d\"), DATE_FORMAT(created_at, \"%m\"), " +
//            "DATE_FORMAT(created_at, \"%y\");", nativeQuery = true)
//    Double chartTotal();

    List<Order> findOrderByAccount(Account account);
}
