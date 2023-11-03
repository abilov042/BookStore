package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.UserService;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.LoginRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.SignupRequest;
import com.task.bookstore.entity.concretes.dtos.response.UserInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<DataResult<UserInfoResponse>> login(@RequestBody LoginRequest loginRequest){

        return ResponseEntity.ok(userService.login(loginRequest));
    }


    @PostMapping("/register")
    public ResponseEntity<Result> register(@RequestBody @Valid SignupRequest signupRequest){

        return ResponseEntity.ok(userService.register(signupRequest));
    }

}
