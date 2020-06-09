package com.pc.annotation;

import java.util.List;

public class PasswordUtil {

    @UseCase(id=47,description = "密码必须包含数字")
    public boolean validatePassword(String password) {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id = 48)
    public String encryPassword(String password) {
        return new StringBuilder(password).reverse().toString();//反转
    }

    @UseCase(id = 49,description = "新密码不能等于旧密码")
    public boolean checkForNewPassword(List<String> prevPasswoeds,String password) {
        return !prevPasswoeds.contains(password);
    }

    //
//    public static void main(String[] args) {
//        PasswordUtil passwordUtil = new PasswordUtil();
//
//        System.out.println(passwordUtil.encryPassword("abcdefg"));
//    }
}
