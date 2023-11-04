package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;

public interface BookService {
    Result save(BookRequest bookRequest);
    Result update(int id, BookRequest bookRequest);
    Result deleteById(int id);
}
