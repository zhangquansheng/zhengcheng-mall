package com.zhengcheng.mall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhengcheng.mall.common.config.MallProperties;
import com.zhengcheng.mall.domain.entity.User;
import com.zhengcheng.mall.domain.mapper.UserMapper;
import com.zhengcheng.mall.service.UserService;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;

/**
 * 用户(User)表服务实现类
 *
 * @author quansheng1.zhang
 * @since 2021-07-15 16:31:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper            userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private MallProperties        mallProperties;
    @Autowired
    private RSA                   rsa;

    @Override
    public boolean save(User user) {
        user.setUserNo(RandomUtil.randomString(mallProperties.getUserNoRandomLength()));
        user.setPassword(this.bCryptEncodePassword(user.getPassword()));
        return userMapper.insert(user) > 0;
    }

    @Override
    public boolean isSamePassword(String password, String encodedPassword) {
        return bCryptPasswordEncoder.matches(md5(password), encodedPassword);
    }

    @Override
    public String rasDecrypt(String enPassword) {
        return new String(rsa.decrypt(Base64.decode(enPassword), KeyType.PrivateKey));
    }

    @Override
    public String bCryptEncodePassword(String password) {
        return bCryptPasswordEncoder.encode(md5(password));
    }

    private String md5(String password) {
        return SecureUtil.md5(StrUtil.format("{}{}", password, mallProperties.getUserPasswordMd5Sign()));
    }

}
