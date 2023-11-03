package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.StudentService;
import com.task.bookstore.core.mapper.StudentMapper;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.StudentDao;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import com.task.bookstore.entity.concretes.users.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {
    private final StudentDao  studentDao;
    private final StudentMapper studentMapper;

    @Override
    public Result save(StudentRequest studentRequest) {

        studentDao.save(studentMapper.studentRequestToStudent(studentRequest));
        return new SuccessResult("Student added");
    }

    @Override
    public Result update(int id, StudentRequest studentRequest) {
        Student student = studentDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("student not found"));
        studentDao.save(studentMapper.updateRequestToEntity(student, studentRequest));

        return new SuccessResult("student successfully updated");
    }
}
