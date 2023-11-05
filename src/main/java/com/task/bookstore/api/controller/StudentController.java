package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.StudentService;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.AddBookRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.SubscribeRequest;
import com.task.bookstore.entity.concretes.dtos.response.BookResponse;
import com.task.bookstore.entity.concretes.users.Book;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
@CrossOrigin
public class StudentController {
    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<Result> save(@Valid @RequestBody StudentRequest studentRequest){

        return ResponseEntity.ok(studentService.save(studentRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> update(@PathVariable int id, @RequestBody @Valid StudentRequest studentRequest){

        return ResponseEntity.ok(studentService.update(id,studentRequest));
    }

    @PutMapping("/addBook/{id}")
    public ResponseEntity<Result> addBook(@PathVariable int id, @RequestParam int bookId){

        return ResponseEntity.ok(studentService.addBook(id, bookId));
    }

    @PutMapping("/subscribe/{id}")
    public ResponseEntity<Result> subscribe(@PathVariable int id, int authorId){

        return ResponseEntity.ok(studentService.subscribe(id, authorId));
    }

    @GetMapping("/getBooksByStudent/{id}")
    public ResponseEntity<DataResult<List<BookResponse>>> getBooksByStudent(@PathVariable int id){

        return ResponseEntity.ok(studentService.getBooksByStudent(id));
    }
}
