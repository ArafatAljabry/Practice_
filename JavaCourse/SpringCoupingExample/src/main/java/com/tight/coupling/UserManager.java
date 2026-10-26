package com.tight.coupling;

public class UserManager {
    public UserDatabase userDatabase = new UserDatabase();

    public String getUserDetails(){
        return userDatabase.getUserDetails();
    }
}