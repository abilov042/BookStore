package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.BookService;
import com.task.bookstore.core.campanents.MailSenderComp;
import com.task.bookstore.core.excepstions.config.NotFountException;
import com.task.bookstore.core.mapper.BookMapper;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.AuthorDao;
import com.task.bookstore.dataAccess.abstracts.BookDao;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.users.Author;
import com.task.bookstore.entity.concretes.users.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookManager implements BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;
    private final MailSenderComp mailSender;
    private final AuthorDao authorDao;

    @Override
    public Result save(BookRequest bookRequest) {
        Book book = bookMapper.bookRequestToBook(bookRequest);
        Author author = authorDao.findById(bookRequest.getAuthor().getId()).orElseThrow();
        if(author.getSubscribers()!=null){

            author.getSubscribers().forEach(sub -> {
                mailSender.sendMail(sub.getUser().getEmail(), bookRequest.getName(), author.getName());
            });
        }


        bookDao.save(book);
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
