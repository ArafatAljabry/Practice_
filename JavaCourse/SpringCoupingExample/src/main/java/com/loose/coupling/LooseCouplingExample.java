package com.loose.coupling;

public class LooseCouplingExample {
    static void main(String[] args) {
        UserDatabaseProvider userDataProvider = new UserDatabaseProvider();
        UserManager userManager = new UserManager(userDataProvider);
        System.out.println(userManager .getUserDetails());
    }
}