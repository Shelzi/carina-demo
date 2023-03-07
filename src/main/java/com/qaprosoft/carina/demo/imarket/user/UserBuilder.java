package com.qaprosoft.carina.demo.imarket.user;

import com.zebrunner.carina.utils.R;

public class UserBuilder {
    public static User getValidUser() {
        return User.builder()
                .login(R.TESTDATA.get("imarket_credentials_login"))
                .password(R.TESTDATA.get("imarket_credentials_password"))
                .name(R.TESTDATA.get("imarket_credentials_name"))
                .phone(R.TESTDATA.get("imarket_credentials_phone"))
                .build();
    }
}
