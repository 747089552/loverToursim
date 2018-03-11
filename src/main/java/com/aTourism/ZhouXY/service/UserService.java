package com.aTourism.ZhouXY.service;

import com.aTourism.ZhouXY.bean.User;

public interface UserService {

    /**
     * 注册
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 验证登录
     * @param user
     * @return
     */
    Long validateLogin(User user);
}
