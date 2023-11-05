package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.dtos.response.StudentResponse;

import java.util.List;

public interface BookService {
    Result save(BookRequest bookRequest);
    Result update(int id, BookRequest bookRequest);
    Result deleteById(int id);
    DataResult<List<StudentResponse>> getByBookName(int id);
}
