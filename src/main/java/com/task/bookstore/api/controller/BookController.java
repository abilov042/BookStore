package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.BookService;
import com.task.bookstore.core.result.DataResult;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.dtos.response.StudentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {
    private final BookService bookService;

    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody @Valid BookRequest bookRequest){

        return ResponseEntity.ok(bookService.save(bookRequest));
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Result> update(@PathVariable int id,@RequestBody @Valid BookRequest bookRequest){

        return ResponseEntity.ok(bookService.update(id, bookRequest));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Result> delete(@PathVariable int id){

        return ResponseEntity.ok(bookService.deleteById(id));
    }

    @GetMapping("/getByBookName/{id}")
    public ResponseEntity<DataResult<List<StudentResponse>>> getByBookName(@PathVariable int id){

        return ResponseEntity.ok(bookService.getByBookName(id));
    }
}
