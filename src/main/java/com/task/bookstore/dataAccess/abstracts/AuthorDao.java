package com.task.bookstore.dataAccess.abstracts;

import com.task.bookstore.entity.concretes.users.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Integer> {
}
