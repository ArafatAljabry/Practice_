package com.loose.coupling;

public interface UserDataProvider {
    //Whatever methods are needed to interact with the database, without adding implementation
    String getUserDetails();

}
