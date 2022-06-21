package com.example.restapiprojectsem4.specification;

import java.util.HashMap;

public class FieldOrder {
    public static final String ID = "id";
    public static final String PRICE = "totalPrice";
    public static final String SHIP_NAME = "shipName";
    public static final String PHONE = "shipPhone";
    public static final String EMAIL = "shipEmail";
    public static final String CREATED_AT = "createdAt";
    public static final String CHECK_OUT = "ísCheckOut";
    public static final String NAME_PRODUCT = "name";
    public static final String ACCOUNT_NAME = "name";
    public static final String ACCOUNT_PHONE = "phone";
    public static final String ORDER_STATUS = "status";

    public static HashMap<String, String> createdField() {
        HashMap<String, String> mapOrder = new HashMap<>();
        mapOrder.put(ID, ID);
        mapOrder.put(ObjectFilter.SHIP_NAME, SHIP_NAME);
        mapOrder.put(ObjectFilter.SHIP_PHONE, PHONE);
        mapOrder.put(ObjectFilter.EMAIL, EMAIL);
        mapOrder.put(ObjectFilter.PRICE, PRICE);
        mapOrder.put(ObjectFilter.IS_REMOVED, ObjectFilter.IS_REMOVED);
        mapOrder.put(CREATED_AT, CREATED_AT);
        mapOrder.put(ORDER_STATUS, ObjectFilter.STATUS_ORDER);
        mapOrder.put(CHECK_OUT, ObjectFilter.IS_CHECKOUT);
        mapOrder.put(ACCOUNT_NAME, ObjectFilter.ACCOUNT_NAME);
        mapOrder.put(NAME_PRODUCT, ObjectFilter.PRODUCT_NAME);
        mapOrder.put(ACCOUNT_PHONE, ObjectFilter.PHONE_ACCOUNT);
        return mapOrder;
    }
}
