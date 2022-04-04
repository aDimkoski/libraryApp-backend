package com.emt.libraryappemt.service;

import com.emt.libraryappemt.model.Author;
import com.emt.libraryappemt.model.dto.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> findById(Long id);
    List<Author> findAll();
    Optional<Author> save(AuthorDto authorDto);
    void deleteById(Long id);
}
