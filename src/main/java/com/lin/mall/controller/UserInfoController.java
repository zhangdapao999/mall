package com.lin.mall.controller;

import com.lin.mall.entity.UserInfo;
import com.lin.mall.entity.bo.UserLoginBo;
import com.lin.mall.entity.bo.UserRegisterBo;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.service.IUserInfoService;
import com.lin.mall.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询用户名是否存在
     */
    @GetMapping("/checkUsernameExist")
    public Result<String> checkUsernameExist(@RequestParam(required = false) String username) {
        if (StringUtils.isBlank(username)) {
            return Result.error(ResponseStatusEnum.PARAM_MISSING);
        }
        Boolean isExist = iuserInfoService.checkUsernameExist(username);
        return isExist ? Result.ok() : Result.error(ResponseStatusEnum.USERNAME_EXIST);
    }


    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<String> register(@RequestBody UserRegisterBo userRegisterBo) {
        iuserInfoService.register(userRegisterBo);
        return Result.ok();
    }

    /**
     * 注册
     */
    @PostMapping("/login")
    public Result<String> login(@RequestBody UserLoginBo userLoginBo) {
        String token = iuserInfoService.login(userLoginBo);
        return Result.success(token);
    }

}
