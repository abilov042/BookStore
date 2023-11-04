package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.AddBookRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.SubscribeRequest;
import com.task.bookstore.entity.concretes.users.Book;
import com.task.bookstore.entity.concretes.users.Student;

public interface StudentService {
    Result save(StudentRequest studentRequest);
    Result update(int id, StudentRequest studentRequest);
    Result addBook(int id, AddBookRequest bookRequest);
    Result subscribe(int id, SubscribeRequest subscribeRequest);
}
