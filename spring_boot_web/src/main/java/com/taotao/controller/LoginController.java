package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.security.util.Password;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @ClassName LoginController
 * @Description
 * @Author tyq
 * @Date 2020/5/3 17:58
 * @Version V1.0
 **/
@Controller
public class LoginController {

    /**
     * 登录：用户名不为空，密码为123456；
     *      成功：dashboard
     *      失败：login
     * @param userName
     * @param password
     * @param map
     * @return
     */
    @PostMapping(value = "/user/login")
    public String login (@RequestParam("username") String userName,
                         @RequestParam("password") String password,
                         Map<String, Object> map, HttpSession httpSession) {

        if (!StringUtils.isEmpty(userName) && "123456".equals(password)) {
            // 登录成功，防止表单重复提交，可以重定向首页
            httpSession.setAttribute("userlogin",userName);
            return "redirect:/main.html";
        } else {
            // 登录失败
            map.put("msg","用户名或密码错误");
            return "redirect:/index.html";
        }
    }
}
