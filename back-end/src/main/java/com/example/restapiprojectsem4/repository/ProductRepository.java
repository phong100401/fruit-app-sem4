package com.example.restapiprojectsem4.repository;

import com.example.restapiprojectsem4.dto.ChartDTO;
import com.example.restapiprojectsem4.dto.TopUserDTO;
import com.example.restapiprojectsem4.dto.TotalChart;
import com.example.restapiprojectsem4.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Query("select p from Product p where p.status = 'ACTIVE'")
    List<Product> findAllByStatus();
    @Query("select p from Product p where upper(p.name) like %:name%")
    List<Product> findByName(String name);

    @Query(value = "select  p.name AS name_product, " +
            "od.product_id AS product_id, " +
            "sum(od.quantity) AS total_quantity " +
            "from products as p " +
            "inner join order_details as od on p.id = od.product_id " +
            "inner join orders as o on o.id = od.order_id WHERE o.is_shopping_cart = 0 " +
            "group by p.id order by sum(od.quantity) desc LIMIT 5", nativeQuery = true)
    List<Object> chart();

    @Query(value = "select new com.example.restapiprojectsem4.dto.ChartDTO(p.name, od.productId, sum(od.quantity)) FROM Product as p inner join OrderDetail as od on p.id = od.productId inner join Order as o on o.id = od.orderId where o.isShoppingCart = false group by p.id order by sum (od.quantity) desc")
    List<ChartDTO> findAllChart();

    @Query(value = "select new com.example.restapiprojectsem4.dto.TopUserDTO(a.username, o.accountId, sum(o.totalPrice)) from Account  as a inner join Order as o on a.id = o.accountId where o.isShoppingCart = false group by a.id order by sum (o.totalPrice) desc ")
    List<TopUserDTO> findUserChart();

//    @Query(value = "SELECT YEAR(NOW()) as `year`,MONTH(created_at) as `month`, SUM(total_price) as 'total_price' From  orders\n" +
//            "     WHERE YEAR(created_at)=YEAR(NOW()) AND MONTH(created_at)=6 AND is_shopping_cart=0\n" +
//            "        group by YEAR(NOW()),MONTH(NOW())", nativeQuery = true)
//    Map<Object, TotalChart> totalChart();

    @Query(value = "SELECT YEAR(NOW()) as `year`,MONTH(created_at) as `month`, SUM(total_price) as 'total_price' From  orders\n" +
            "     WHERE YEAR(created_at)=:year AND MONTH(created_at)=:month AND is_shopping_cart=0\n" +
            "        group by YEAR(:year),MONTH(:month)", nativeQuery = true)
    Map<Object, TotalChart> totalChart(@Param("year") Integer year, @Param("month") Integer month);
}
