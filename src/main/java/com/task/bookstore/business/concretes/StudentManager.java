package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.StudentService;
import com.task.bookstore.core.excepstions.config.NotFountException;
import com.task.bookstore.core.mapper.BookMapper;
import com.task.bookstore.core.mapper.StudentMapper;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessDataResult;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.BookDao;
import com.task.bookstore.dataAccess.abstracts.StudentDao;
import com.task.bookstore.entity.concretes.dtos.request.request.AddBookRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.SubscribeRequest;
import com.task.bookstore.entity.concretes.dtos.response.BookResponse;
import com.task.bookstore.entity.concretes.users.Author;
import com.task.bookstore.entity.concretes.users.Book;
import com.task.bookstore.entity.concretes.users.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentManager implements StudentService {
    private final StudentDao  studentDao;
    private final StudentMapper studentMapper;
    private final BookDao bookDao;
    private  final BookMapper bookMapper;

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

    @Override
    public Result addBook(int id, int bookId) {
        Student student = studentDao.findById(id).orElseThrow(()-> new NotFountException("student not found"));
        Book book = new Book();
        List<Book> books = new ArrayList<>();
        book.setId(bookId);
        books.add(book);
        student.setBooks(books);
        studentDao.save(student);
        return new SuccessResult("Book added in library");
    }

    @Override
    public Result subscribe(int id, int authorId) {
        Student student = studentDao.findById(id).orElseThrow(()-> new NotFountException("student not found"));
        Author author = new Author();
        List<Author> authors = new ArrayList<>();
        author.setId(authorId);
        authors.add(author);
        student.setSubscribedAuthors(authors);
        studentDao.save(student);
        return new SuccessResult("Followed author");
    }

    @Override
    public DataResult<List<BookResponse>> getBooksByStudent(int id) {
        Student student = studentDao.findById(id).orElseThrow(()-> new NotFountException("Book not found"));

        return new SuccessDataResult<>(bookMapper.convertToListBooks(bookDao.findByStudentsContains(student)), "Books listed");
    }
}
