package com.example.restapiprojectsem4.specification;

import com.example.restapiprojectsem4.entity.Order;
import com.example.restapiprojectsem4.entity.OrderDetail;
import com.example.restapiprojectsem4.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDateTime;

public class OrderSpecification implements Specification<Order> {

    private final SearchCriteria criteria;

    public OrderSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    // criteriaBuilder giúp xử lý các toán tử.
    // root lấy ra trường và giá trị các trường.
    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        switch (searchCriteria.getOperation()){
//            case "EQUALS":
//                return criteriaBuilder.equal(
//                        root.get(searchCriteria.getKey()),
//                        searchCriteria.getValue());
//            case "GREATER_THAN":
//                return criteriaBuilder.greaterThan(
//                        root.get(searchCriteria.getKey()),
//                        String.valueOf(searchCriteria.getValue()));
//            case "GREATER_THAN_OR_EQUALS":
//                if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
//                    return criteriaBuilder.greaterThanOrEqualTo(
//                            root.get(searchCriteria.getKey()),
//                            (LocalDateTime) searchCriteria.getValue());
//                } else {
//                    return criteriaBuilder.greaterThanOrEqualTo(
//                            root.get(searchCriteria.getKey()),
//                            String.valueOf(searchCriteria.getValue()));
//                }
//            case "LESS_THAN":
//                return criteriaBuilder.lessThan(
//                        root.get(searchCriteria.getKey()),
//                        String.valueOf(searchCriteria.getValue()));
//            case "LESS_THAN_OR_EQUALS":
//                if (root.get(searchCriteria.getKey()).getJavaType() == LocalDateTime.class) {
//                    return criteriaBuilder.lessThanOrEqualTo(
//                            root.get(searchCriteria.getKey()),
//                            (LocalDateTime) searchCriteria.getValue());
//                } else {
//                    return criteriaBuilder.lessThanOrEqualTo(
//                            root.get(searchCriteria.getKey()),
//                            String.valueOf(searchCriteria.getValue()));
//                }
//            case "LIKE":
//                return criteriaBuilder.like(
//                        root.get(searchCriteria.getKey()), "%" +
//                                searchCriteria.getValue() + "%");
//            case "JOIN_ACCOUNT":
//                return criteriaBuilder.like(root.get("account").get(searchCriteria.getKey()), "%" + searchCriteria.getValue() + "%");
//            case "JOIN_PRODUCT":
//                Join<Order, OrderDetail> join = root.join("orderDetails");
//                Join<Order, Product> joinProduct = join.join("product");
//                return criteriaBuilder.like(joinProduct.get("name"), "%" + searchCriteria.getValue() + "%");


//        }
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return criteriaBuilder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if (criteria.getOperation().equalsIgnoreCase("join")) {
//            Join<Order, Account> orderAccountJoin = root.join("account");
//            Join<Account, User> accountUserJoin = root.join("account").join("user");
//            Predicate predicate = builder.or(
//                    builder.like(root.get("id"), "%" + criteria.getValue() + "%"),
//                    builder.like(orderAccountJoin.get("email"), "%" + criteria.getValue() + "%"),
//                    builder.like(accountUserJoin.get("phoneNumber"), "%" + criteria.getValue() + "%"),
//                    builder.like(accountUserJoin.get("fullName"), "%" + criteria.getValue() + "%")
//            );
//            return predicate;
        }

        return null;
    }
}
