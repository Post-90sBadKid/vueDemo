package com.wry.vueservice.config.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wry.vueservice.dto.Result;
import com.wry.vueservice.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 在控制器执行前调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //设置返回的是JSON数据
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        System.out.println("URI:"+request.getRequestURI());
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");


        if (loginUser==null){
            //获取response的书写器
            PrintWriter writer = response.getWriter();
            //创建返回结果对象
            Result result=new Result();
            result.setCode(403);
            result.setSuccess(true);
            result.setMessage("没有权限，请登陆后重试！");
            //利用ObjectMapper转换为JSON数据
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr=mapper.writeValueAsString(result);
            //返回前端页面
            writer.print(jsonStr);

            return false;
        }
        return true;
    }

    /**
     * 在控制器执行后调用
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求执行完成后调用
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
