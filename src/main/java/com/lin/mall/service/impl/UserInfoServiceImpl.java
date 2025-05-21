package com.lin.mall.service.impl;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lin.mall.entity.UserInfo;
import com.lin.mall.entity.bo.UserLoginBo;
import com.lin.mall.entity.bo.UserRegisterBo;
import com.lin.mall.enums.ResponseStatusEnum;
import com.lin.mall.enums.UserStatusEnum;
import com.lin.mall.exception.BusinessException;
import com.lin.mall.mapper.UserInfoMapper;
import com.lin.mall.service.IUserInfoService;
import com.lin.mall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @Value("${system.salt}")
    private String salt;
    @Value("${system.defaultAvatar}")
    private String defaultAvatar;

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
        Boolean isNotExist = this.checkUsernameExist(userRegisterBo.getUsername());
        if (!isNotExist) {
            throw new BusinessException(ResponseStatusEnum.USERNAME_EXIST);
        }
        // 注册,生成新用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setId(IdUtil.getSnowflakeNextId());
        userInfo.setNickname("新用户");
        userInfo.setAvatar(defaultAvatar);
        userInfo.setUsername(userRegisterBo.getUsername());
        userInfo.setPassword(MD5Util.encrypt(userRegisterBo.getPassword(), salt));
        userInfo.setBalance(new BigDecimal("0"));
        userInfo.setStatus(UserStatusEnum.NORMAL.getCode());
        userInfo.setCreatedTime(LocalDateTime.now());
        this.save(userInfo);
    }

    @Override
    public Boolean checkUsernameExist(String username) {
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUsername, username.trim());
        UserInfo one = this.getOne(queryWrapper);
        // 如果为空,说明可以注册
        return one == null;
    }

    @Override
    public String login(UserLoginBo userLoginBo) {
        // 判断参数是否为空
        if (StringUtils.isBlank(userLoginBo.getUsername()) || StringUtils.isBlank(userLoginBo.getPassword())) {
            throw new BusinessException(ResponseStatusEnum.PARAM_MISSING);
        }
        // 判断账号是否存在
        LambdaQueryWrapper<UserInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserInfo::getUsername, userLoginBo.getUsername());
        UserInfo userInfo = this.getOne(queryWrapper);
        if (userInfo == null) {
            throw new BusinessException(ResponseStatusEnum.USERNAME_NOT_EXIST);
        }
        // 判断账号密码是否正确
        if (!userInfo.getPassword().equals(MD5Util.encrypt(userLoginBo.getPassword(), salt))) {
            throw new BusinessException(ResponseStatusEnum.PASSWORD_ERROR);
        }
        // 判断是否被冻结
        if (userInfo.getStatus().equals(UserStatusEnum.FROZEN.getCode())) {
            throw new BusinessException(ResponseStatusEnum.USER_FROZEN);
        }
        // 生成token todo 现阶段用uuid,redis缓存5分钟做过期,后面整合jwt
        String token = UUID.randomUUID().toString();

        return token;

    }
}
