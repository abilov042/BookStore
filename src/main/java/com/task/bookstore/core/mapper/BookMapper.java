package com.task.bookstore.core.mapper;

import com.task.bookstore.entity.concretes.dtos.request.request.BookRequest;
import com.task.bookstore.entity.concretes.dtos.response.BookResponse;
import com.task.bookstore.entity.concretes.users.Book;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book bookRequestToBook(BookRequest bookRequest);
    Book updateBookRequestToEntity(@MappingTarget Book book, BookRequest bookRequest);
    List<BookResponse> convertToListBooks(List<Book> books);
}
