package com.example.auction.controller;

import com.example.auction.dto.UserCreateForm;
import com.example.auction.repository.UsersRepository;
import com.example.auction.entity.Users;
import com.example.auction.repository.UsersRepository;
import com.example.auction.service.UserService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        ProductController.testOwnerId=1;
        return "/login";
    }


    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "/signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/signup";
        }

        if(! userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password2", "passwordIncorrect",
                    "2개의 패스워드가 일치하지 않습니다.");
            return "/signup";
        }

        try {
            userService.createUser(userCreateForm);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자 입니다.");
            return "/signup";
        } catch (Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "/signup";
        }
        return "redirect:/login";
    }
}