package com.lin.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lin.mall.entity.UserInfo;
import com.lin.mall.entity.bo.UserRegisterBo;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.exception.BusinessException;
import com.lin.mall.mapper.UserInfoMapper;
import com.lin.mall.service.IUserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author lin
 * @since 2025-05-20
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

    @Value("${system.salt")
    private String salt;

    @Override
    public void register(UserRegisterBo userRegisterBo) {
        // 判断参数是否为空
        if (StringUtils.isBlank(userRegisterBo.getUsername()) || StringUtils.isBlank(userRegisterBo.getPassword()) || StringUtils.isBlank(userRegisterBo.getRepeatPassword())) {
            throw new BusinessException(ResponseStatusEnum.PARAM_MISSING);
        }
        // 判断两次密码是否相同
        if (!userRegisterBo.getPassword().equals(userRegisterBo.getRepeatPassword())) {
            throw new BusinessException(ResponseStatusEnum.PASSWORD_NOT_SAME);
        }

        // 判断username是否重复
        Boolean isExist = this.checkUsernameExist(userRegisterBo.getUsername());
        if (!isExist) {
            throw new BusinessException(ResponseStatusEnum.USERNAME_EXIST);
        }
    }

    @Override
    public Boolean checkUsernameExist(String username) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUsername, username.trim());
        UserInfo one = this.getOne(queryWrapper);
        // 如果为空,说明可以注册
        return one == null;
    }
}
