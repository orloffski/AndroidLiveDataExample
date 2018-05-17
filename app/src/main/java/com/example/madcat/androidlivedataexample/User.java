package com.example.madcat.androidlivedataexample;

public class User {

    private String name;
    private int userId;
    private static int number = 0;

    private User(String name) {
        this.name = name;
        this.userId = ++number;
    }

    public static User getUser(String name){
        return new User(name);
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }
}
