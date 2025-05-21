package com.lin.mall.service;

import com.lin.mall.entity.UserInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lin.mall.entity.bo.UserRegisterBo;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
public interface IUserInfoService extends IService<UserInfo> {

    /**
     * 用户注册
     * @param userRegisterBo 注册信息
     */
    void register(UserRegisterBo userRegisterBo);

    /**
     * 校验用户名是否存在
     * @param username 用户名
     * @return true:不存在,可注册 false:已存在,不可注册
     */
    Boolean checkUsernameExist(String username);
}
