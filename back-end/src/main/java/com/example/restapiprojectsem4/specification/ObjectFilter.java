package com.example.restapiprojectsem4.specification;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
public class ObjectFilter {
    public static final String FROM = "from";
    public static final String TO = "to";
    public static final String ID = "id";
    public static final String PRICE = "price";
    public static final String SHIP_NAME = "shipName";
    public static final String SHIP_PHONE = "shipPhone";
    public static final String EMAIL = "email";
    public static final String DETAIL = "detail";
    public static final String CREATED_AT = "createdAt";
    public static final String IS_REMOVED = "isRemoved";
    public static final String STATUS = "status";
    public static final String PRODUCT_NAME = "name";
    public static final String ACCOUNT_NAME = "name";
    public static final String PHONE_ACCOUNT = "phone";
    public static final String IS_CHECKOUT = "isCheckOut";
    public static final String STATUS_ORDER = "status";

    private int id;
    private int categoryId;
    private double minPrice;
    private double maxPrice;
    private int page;
    private int pageSize;
    private String shipName;
    private String status;
    private String detail;
    private String name;
    private String email;
    private int statusO;
    private String accountName;
    private String shipPhone;
    private String phone;
    private boolean isCheckOut;
    private String from;
    private String to;
    private int isRemoved;
    private HashMap<String,String> mapField;


    public static final class ObjectFilterBuilder {
        private int id;
        private int categoryId;
        private double minPrice;
        private double maxPrice;
        private int page;
        private int pageSize;
        private String shipName;
        private String status;
        private String detail;
        private String name;
        private String email;
        private int statusO;
        private String accountName;
        private String shipPhone;
        private String phone;
        private boolean isCheckOut;
        private String from;
        private String to;
        private int isRemoved;
        private HashMap<String,String> mapField;

        private ObjectFilterBuilder() {
        }

        public static ObjectFilterBuilder anObjectFilter() {
            return new ObjectFilterBuilder();
        }

        public ObjectFilterBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public ObjectFilterBuilder withCategoryId(int categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public ObjectFilterBuilder withMinPrice(double minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public ObjectFilterBuilder withMaxPrice(double maxPrice) {
            this.maxPrice = maxPrice;
            return this;
        }

        public ObjectFilterBuilder withPage(int page) {
            this.page = page;
            return this;
        }

        public ObjectFilterBuilder withPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ObjectFilterBuilder withShipName(String shipName) {
            this.shipName = shipName;
            return this;
        }

        public ObjectFilterBuilder withStatus(String status) {
            this.status = status;
            return this;
        }

        public ObjectFilterBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public ObjectFilterBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public ObjectFilterBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ObjectFilterBuilder withStatusO(int statusO) {
            this.statusO = statusO;
            return this;
        }

        public ObjectFilterBuilder withAccountName(String accountName) {
            this.accountName = accountName;
            return this;
        }

        public ObjectFilterBuilder withShipPhone(String shipPhone) {
            this.shipPhone = shipPhone;
            return this;
        }

        public ObjectFilterBuilder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public ObjectFilterBuilder withIsCheckOut(boolean isCheckOut) {
            this.isCheckOut = isCheckOut;
            return this;
        }

        public ObjectFilterBuilder withFrom(String from) {
            this.from = from;
            return this;
        }

        public ObjectFilterBuilder withTo(String to) {
            this.to = to;
            return this;
        }

        public ObjectFilterBuilder withIsRemoved(int isRemoved) {
            this.isRemoved = isRemoved;
            return this;
        }

        public ObjectFilterBuilder withMapField(HashMap<String, String> mapField) {
            this.mapField = mapField;
            return this;
        }

        public ObjectFilter build() {
            ObjectFilter objectFilter = new ObjectFilter();
            objectFilter.setId(id);
            objectFilter.setCategoryId(categoryId);
            objectFilter.setMinPrice(minPrice);
            objectFilter.setMaxPrice(maxPrice);
            objectFilter.setPage(page);
            objectFilter.setPageSize(pageSize);
            objectFilter.setShipName(shipName);
            objectFilter.setStatus(status);
            objectFilter.setDetail(detail);
            objectFilter.setName(name);
            objectFilter.setEmail(email);
            objectFilter.setStatusO(statusO);
            objectFilter.setAccountName(accountName);
            objectFilter.setShipPhone(shipPhone);
            objectFilter.setPhone(phone);
            objectFilter.setFrom(from);
            objectFilter.setTo(to);
            objectFilter.setIsRemoved(isRemoved);
            objectFilter.setMapField(mapField);
            objectFilter.isCheckOut = this.isCheckOut;
            return objectFilter;
        }
    }
}
