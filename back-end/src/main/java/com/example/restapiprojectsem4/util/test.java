package com.example.restapiprojectsem4.util;


import com.github.javafaker.Faker;

public class test {
    public static void main(String[] args) {
        Faker faker = new Faker();
        System.out.println("random: " + faker.random().nextBoolean());
    }
}
