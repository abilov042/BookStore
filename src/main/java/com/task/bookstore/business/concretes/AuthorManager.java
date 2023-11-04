package com.task.bookstore.business.concretes;

import com.task.bookstore.business.abstracts.AuthorService;
import com.task.bookstore.core.mapper.AuthorMapper;
import com.task.bookstore.core.result.Result;
import com.task.bookstore.core.result.SuccessResult;
import com.task.bookstore.dataAccess.abstracts.AuthorDao;
import com.task.bookstore.entity.concretes.dtos.request.request.AuthorRequest;
import com.task.bookstore.entity.concretes.users.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorManager implements AuthorService {
    private final AuthorDao authorDao;
    private final AuthorMapper authorMapper;
    @Override
    public Result save(AuthorRequest authorRequest) {
        System.out.println(authorMapper.authorRequestToAuthor(authorRequest));
        authorDao.save(authorMapper.authorRequestToAuthor(authorRequest));
        return new SuccessResult("Author successfully saved");
    }

    @Override
    public Result update(int id, AuthorRequest authorRequest) {
        Author author = authorDao.findById(id).orElseThrow(() -> new UsernameNotFoundException("Author not found"));
        authorDao.save(authorMapper.updateAuthorRequestToEntity(author, authorRequest));
        return new SuccessResult("Author successfully updated");
    }
}
