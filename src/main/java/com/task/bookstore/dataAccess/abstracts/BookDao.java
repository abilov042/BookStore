package com.task.bookstore.dataAccess.abstracts;

import com.task.bookstore.entity.concretes.users.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Integer> {
}
