package com.qaprosoft.carina.demo.allinstruents.user;

import com.zebrunner.carina.utils.R;

public class UserBuilder {
    public static User getValidUser() {
        return User.builder()
                .login(R.TESTDATA.get("vi_credentials_login"))
                .password(R.TESTDATA.get("vi_credentials_password"))
                .name("Артур")
                .phone("9999999999")
                .build();
    }
}
