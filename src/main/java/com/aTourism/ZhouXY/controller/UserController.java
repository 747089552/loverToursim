package com.aTourism.ZhouXY.controller;

import com.aTourism.ZhouXY.bean.User;
import com.aTourism.ZhouXY.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @Resource(name = "userService")
    private UserService userService;

    /**
     * 登录
     * @param request
     * @param user
     * @return
     */
    @RequestMapping("loging")
    public ModelAndView logining(HttpServletRequest request ,User user){
        ModelAndView mv = new ModelAndView();
        String url = "";

        Object userInfo = request.getSession().getAttribute("userInfo");
        if(userInfo != null){
            mv.addObject("backData","你已登录！！！logining success");
        }else{
            Long returnId = userService.validateLogin(user);
            if(returnId != null && returnId >0){
                mv.addObject("backData","登录成功！！！logining success");
                request.getSession().setAttribute("userInfo",returnId);
            }else{
                mv.addObject("backData","登录失败！！！your userName or password is not corret...");
            }
        }
        mv.setViewName("backData");
        return mv;
    }


}
