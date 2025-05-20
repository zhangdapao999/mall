package com.lin.mall.controller;

import com.lin.mall.entity.UserInfo;
import com.lin.mall.entity.bo.UserRegisterBo;
import com.lin.mall.service.IUserInfoService;
import com.lin.mall.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@RestController
@RequestMapping("/userInfo")
public class UserInfoController {

    @Autowired
    private IUserInfoService iuserInfoService;

    @PostMapping("/register")
    public Result<UserInfo> register(@RequestBody UserRegisterBo userRegisterBo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(13212L);
        userInfo.setNickname("测试");
        userInfo.setAvatar("234");
        userInfo.setUsername("username");
        userInfo.setPassword("123456");
        userInfo.setBalance(new BigDecimal("23.3"));
        userInfo.setCreatedTime(LocalDateTime.now());
        iuserInfoService.save(userInfo);
        return Result.success(userInfo,"success");
    }

}
