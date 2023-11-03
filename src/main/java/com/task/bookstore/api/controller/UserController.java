package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.UserService;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.entity.concretes.dtos.request.LoginRequest;
import com.task.bookstore.entity.concretes.dtos.request.SignupRequest;
import com.task.bookstore.entity.concretes.dtos.response.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<DataResult<UserInfoResponse>> login(LoginRequest loginRequest){

        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<Result> register(SignupRequest signupRequest){

        return ResponseEntity.ok(userService.register(signupRequest));
    }

}
