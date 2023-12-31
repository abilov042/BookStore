package com.task.bookstore.entity.concretes.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoResponse {

    private int id;
    private String username;
    private String email;
    private String jwtToken;
    private List<String> roles;
}
