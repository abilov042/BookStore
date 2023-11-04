package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.BookService;
import com.task.bookstore.core.excepstions.config.NotFountException;
import com.task.bookstore.core.mapper.BookMapper;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.BookDao;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.users.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookManager implements BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;

    @Override
    public Result save(BookRequest bookRequest) {
        bookDao.save(bookMapper.bookRequestToBook(bookRequest));
        return new SuccessResult("Book saved");
    }

    @Override
    public Result update(int id, BookRequest bookRequest) {
        Book book = bookDao.findById(id).orElseThrow(() -> new NotFountException("Book note found"));
        bookDao.save(bookMapper.updateBookRequestToEntity(book, bookRequest));
        return new SuccessResult("Book updated");
    }

    @Override
    public Result deleteById(int id) {
        bookDao.deleteById(id);
        return new SuccessResult("user successfully removed");
    }
}
