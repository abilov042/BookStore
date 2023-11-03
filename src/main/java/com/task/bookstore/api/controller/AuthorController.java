package com.task.bookstore.api.controller;

import com.task.bookstore.business.abstracts.AuthorService;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.entity.concretes.dtos.request.request.AuthorRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@CrossOrigin
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping("/save")
    public ResponseEntity<Result> save(@RequestBody @Valid AuthorRequest authorRequest){

        return ResponseEntity.ok(authorService.save(authorRequest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result> update(@PathVariable int id, @RequestBody @Valid AuthorRequest authorRequest){

        return ResponseEntity.ok(authorService.update(id, authorRequest));
    }
}
