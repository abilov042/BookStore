package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.AuthorRequest;

public interface AuthorService {
    Result save(AuthorRequest authorRequest);
    Result update(int id, AuthorRequest authorRequest);
}
