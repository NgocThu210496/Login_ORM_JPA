package com.ra.controller;

import com.ra.dto.request.LoginRequest;
import com.ra.dto.response.LoginResponse;
import com.ra.service.UserService;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/userController")
public class UserController {
    @Autowired
    //goi sang service
    private UserService userService;

    @PostMapping( "/login") //Model model: chuyen du lieu sang login
    public String login(LoginRequest loginRequest, ModelMap modelMap, HttpServletResponse response, HttpServletRequest request){ //phuong thuc de login
        //goi sang service de login -> và loginResponse: để hứng kết quả
        LoginResponse loginResponse = userService.login(loginRequest);
        if(loginResponse==null){
            //dang nhap that bai se chuyen den ->login
            // attributeValue:"error" : tham số
            modelMap.addAttribute("error", "Username or password incorrect");
            return "login";  //login.jsp
        }else {
            //dang nhap ok
        }
        return "redirect:wellcome";
    }
}
