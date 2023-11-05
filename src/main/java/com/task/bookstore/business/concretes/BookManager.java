package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.BookService;
import com.task.bookstore.core.campanents.MailSenderComp;
import com.task.bookstore.core.excepstions.config.NotFountException;
import com.task.bookstore.core.mapper.BookMapper;
import com.task.bookstore.core.mapper.StudentMapper;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessDataResult;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.AuthorDao;
import com.task.bookstore.dataAccess.abstracts.BookDao;
import com.task.bookstore.dataAccess.abstracts.StudentDao;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.dtos.response.StudentResponse;
import com.task.bookstore.entity.concretes.users.Author;
import com.task.bookstore.entity.concretes.users.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookManager implements BookService {

    private final BookDao bookDao;
    private final BookMapper bookMapper;
    private final MailSenderComp mailSender;
    private final AuthorDao authorDao;
    private final StudentDao studentDao;
    private final StudentMapper studentMapper;

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

    @Override
    public DataResult<List<StudentResponse>> getByBookName(int id) {
        Book book = bookDao.findById(id).orElseThrow(()-> new NotFountException("students not found"));

        return new SuccessDataResult<>(studentMapper.convertToListStudent(studentDao.findStudentByBooksContains(book)),
                "Students listed");
    }
}
