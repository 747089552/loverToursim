package com.aTourism.ZhouXY.mapper;

import com.aTourism.ZhouXY.bean.User;

public interface UserMapper {

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
