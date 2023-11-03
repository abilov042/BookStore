package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.LoginRequest;
import com.task.bookstore.entity.concretes.dtos.request.SignupRequest;
import com.task.bookstore.entity.concretes.dtos.response.UserInfoResponse;

public interface UserService {
    DataResult<UserInfoResponse> login(LoginRequest loginRequest);
    Result register(SignupRequest signupRequest);
}
