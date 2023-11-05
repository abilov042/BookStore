package com.task.bookstore.dataAccess.abstracts;

import com.task.bookstore.entity.concretes.users.Book;
import com.task.bookstore.entity.concretes.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentDao extends JpaRepository<Student, Integer> {
    List<Student> findStudentByBooksContains(Book book);

}
