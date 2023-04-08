package com.example.cis2208mcfoodforge.Database;

public class User {
    private final int user_id;
    private final String user_name;
    private final String user_surname;

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_surname() {
        return user_surname;
    }

    public User(int user_id, String user_name, String user_surname) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_surname = user_surname;
    }
}
