package com.task.bookstore.entity.concretes.dtos.request.request;

import com.task.bookstore.entity.concretes.users.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubscribeRequest {
    List<Author> authors;
}
