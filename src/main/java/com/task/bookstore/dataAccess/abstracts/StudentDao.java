package com.task.bookstore.dataAccess.abstracts;

import com.task.bookstore.entity.concretes.users.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Integer> {
}
