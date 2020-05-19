package com.taotao.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandlerInterceptor
 * @Description:登录拦截
 * @Author tyq
 * @Date 2020/5/3 22:15
 * @Version V1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {
    // 在目标方法执行之前执行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         Object user = request.getSession().getAttribute("userlogin");
         if (user == null) {
             // 未登录，返回登录页
             request.setAttribute("msg","没有权限，请先登录");
             request.getRequestDispatcher("/index.html").forward(request,response);
             return false;
         } else {
             // 登录放行
             return true;
         }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
