package com.aTourism.ZhouXY.mapper.impl;

import com.aTourism.ZhouXY.bean.User;
import com.aTourism.ZhouXY.mapper.UserMapper;
import com.aTourism.ZhouXY.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public class UserMapperImpl extends BasicSqlSupport implements UserMapper {

    /**
     * 注册
     * @param user
     * @return
     */
    public int addUser(User user) {
        return this.insert("com.aTourism.ZhouXY.mapping.addUser",user);
    }

    /**
     * 验证登录
     * @param user
     * @return
     */
    public Long validateLogin(User user) {
        return this.selectOne("com.aTourism.ZhouXY.mapping.validateLogin",user);
    }
}
