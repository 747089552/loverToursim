package com.aTourism.ZhouXY.service.impl;

import com.aTourism.ZhouXY.bean.User;
import com.aTourism.ZhouXY.mapper.UserMapper;
import com.aTourism.ZhouXY.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl  implements UserService{

    @Autowired
    private UserMapper userMapper;

    /**
     * 注册
     * @param user
     * @return
     */
    @Transactional
    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    /**
     * 验证登录
     * @param user
     * @return
     */
    public Long validateLogin(User user) {
        return userMapper.validateLogin(user);
    }
}
