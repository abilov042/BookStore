package com.task.bookstore.core.mapper;

import com.task.bookstore.entity.concretes.dtos.request.request.AuthorRequest;
import com.task.bookstore.entity.concretes.users.Author;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "user", source = "user")
    Author authorRequestToAuthor(AuthorRequest authorRequest);

    Author updateAuthorRequestToEntity(@MappingTarget Author author, AuthorRequest authorRequest);
}
