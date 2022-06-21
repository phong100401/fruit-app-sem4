package com.example.restapiprojectsem4.util;

import com.example.restapiprojectsem4.exception.RequestValidException;
import com.example.restapiprojectsem4.specification.FieldOrder;
import com.example.restapiprojectsem4.specification.HandlerSpecification;
import com.example.restapiprojectsem4.specification.ObjectFilter;
import com.example.restapiprojectsem4.specification.SearchCriteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;

public class HandlerQuery {
    public static Specification creatQuery(ObjectFilter filter) {

        HashMap<String, String> mapField = filter.getMapField();
        Specification specification = Specification.where(null);
        if (filter.getShipName() != null && filter.getShipName().length() > 0) {
            specification = specification.or(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.SHIP_NAME),
                    Operation.EQUAL,
                    filter.getShipName())));
        }

        if (filter.getName() != null && filter.getName().length() > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria("name", "join-productName", filter.getName().trim())));
        }

        if(filter.getAccountName() != null && filter.getAccountName().length() > 0){
            specification = specification.and(new HandlerSpecification(new SearchCriteria("name", "join", filter.getAccountName().trim())));
        }

        if(filter.getPhone() != null && filter.getPhone().length() > 0){
            specification = specification.and(new HandlerSpecification(new SearchCriteria("phone", "join", filter.getPhone().trim())));
        }

        if(!(filter.getStatusO() < 0)){
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.STATUS_ORDER),
                    Operation.EQUAL,
                    filter.getStatusO()
            )));
        }

        if(filter.isCheckOut())

        if (filter.getEmail() != null && filter.getEmail().length() > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.EMAIL),
                    Operation.EQUAL,
                    filter.getEmail())));
        }

        if (filter.getShipPhone() != null && filter.getShipPhone().length() > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.SHIP_PHONE),
                    Operation.EQUAL,
                    filter.getPhone())));
        }

        int id = filter.getId();
        if (id < 0) {
            throw new RequestValidException("Order is not found! Please check the information again.");
        } else if (id > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.ID),
                    Operation.EQUAL,
                    filter.getId())));
        }


        if (filter.getFrom() != null && filter.getFrom().length() > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.CREATED_AT),
                    Operation.GREATER_THAN_OR_EQUAL_TO,
                    filter.getFrom())));
        }

        if (filter.getTo() != null && filter.getTo().length() > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.CREATED_AT),
                    Operation.lESS_THAN_OR_EQUAL_TO,
                    filter.getTo())));
        }

        double minPrice = filter.getMinPrice();
        if (minPrice < 0) {
            throw new RequestValidException("Order is not found! Please check the information again.");
        } else if (minPrice > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.PRICE),
                    Operation.GREATER_THAN_OR_EQUAL_TO, filter.getMinPrice())));
        }

        double maxPrice = filter.getMaxPrice();
        if (maxPrice < 0) {
            throw new RequestValidException("Order is not found! Please check the information again.");
        } else if (maxPrice > 0) {
            specification = specification.and(new HandlerSpecification(new SearchCriteria(
                    mapField.get(ObjectFilter.PRICE),
                    Operation.lESS_THAN_OR_EQUAL_TO, filter.getMaxPrice())));
        }

//

        if (filter.getIsRemoved() != 1 && filter.getIsRemoved() != 0) {
            throw new RequestValidException("Order is not found! Please check the information again.");
        } else {
            specification = specification.and(new HandlerSpecification((new SearchCriteria(
                    mapField.get(ObjectFilter.IS_REMOVED),
                    Operation.EQUAL, filter.getIsRemoved()))));
        }



        return specification;
    }

    public static Pageable creatPagination(int page, int pageSize) {
        return PageRequest.of(page - 1, pageSize, Sort.Direction.DESC, ObjectFilter.CREATED_AT);
    }

//    private boolean check(int isCheck){
//        if (isCheck == 1){
//            return true;
//        }else if (isCheck == 0){
//            return false;
//        }
//        else {
//            throw new RequestValidException("đmm");
//        }
//    }
}
