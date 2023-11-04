package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.StudentService;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.AddBookRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Result> addBook(@PathVariable int id, @RequestBody AddBookRequest bookRequest){

        return ResponseEntity.ok(studentService.addBook(id, bookRequest));
    }
}
