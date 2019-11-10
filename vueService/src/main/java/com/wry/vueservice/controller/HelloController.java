package com.wry.vueservice.controller;

import com.wry.vueservice.dto.Result;
import com.wry.vueservice.pojo.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin(allowCredentials = "true")
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "helloWorld!";
    }


    @RequestMapping("/login")
    public Result login(@RequestParam("userName") String name,
                        @RequestParam("passWord")String password,
                        HttpSession session, Result result) {

        if (name.equals("admin") && password.equals("123456")) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);

            session.setAttribute("loginUser", user);
            result.setSuccess(true);
            result.setCode(200);
            result.setMessage(user);
        }else {
            result.setSuccess(false);
            result.setCode(400);
            result.setMessage("用户名或密码错误!");
        }

        return result;
    }
}
