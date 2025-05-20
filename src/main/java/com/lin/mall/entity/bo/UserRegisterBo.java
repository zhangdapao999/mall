package com.lin.mall.entity.bo;

import lombok.Data;

@Data
public class UserRegisterBo {

    private String username;

    private String password;

    private String repeatPassword;
}
