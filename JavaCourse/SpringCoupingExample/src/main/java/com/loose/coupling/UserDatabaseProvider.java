package com.loose.coupling;

public class UserDatabaseProvider implements UserDataProvider {
    @Override
    public String getUserDetails() {
        return "Get user details from database";
    }
}
