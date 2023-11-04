package com.task.bookstore.core.mapper;

import com.task.bookstore.entity.concretes.dtos.request.request.AddBookRequest;
import com.task.bookstore.entity.concretes.dtos.request.request.StudentRequest;
import com.task.bookstore.entity.concretes.users.Student;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "user", source = "user")
    Student studentRequestToStudent(StudentRequest studentRequest);

    Student updateRequestToEntity(@MappingTarget Student student, StudentRequest studentRequest);

    @Mapping(target = "name", ignore = true)
    @Mapping(target = "age", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "books", source = "books")
    Student addBookRequestToStudent(AddBookRequest bookRequest);

    Student updateAddBookToEntity(@MappingTarget Student student, AddBookRequest bookRequest);
}
