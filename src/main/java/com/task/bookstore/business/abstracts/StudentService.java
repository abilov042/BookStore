package com.task.bookstore.business.abstracts;

import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;

public interface StudentService {
    Result save(StudentRequest studentRequest);
    Result update(int id, StudentRequest studentRequest);
}
