package com.task.bookstore.entity.concretes.dtos.request.request;

import com.task.bookstore.entity.concretes.users.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorRequest {
    @NotBlank
    @NotNull
    private String name;

    private int age;

    private User user;
}
