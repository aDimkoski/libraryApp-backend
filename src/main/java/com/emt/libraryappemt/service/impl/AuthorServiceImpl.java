package com.emt.libraryappemt.service.impl;

import com.emt.libraryappemt.model.Author;
import com.emt.libraryappemt.model.Country;
import com.emt.libraryappemt.model.dto.AuthorDto;
import com.emt.libraryappemt.model.exceptions.CountryNotFoundException;
import com.emt.libraryappemt.repository.AuthorRepository;
import com.emt.libraryappemt.repository.CountryRepository;
import com.emt.libraryappemt.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = this.countryRepository.findById(authorDto.getCountry())
                .orElseThrow(()-> new CountryNotFoundException(authorDto.getCountry()));
        Author author = new Author(authorDto.getName(), authorDto.getSurname(), country);
        this.authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
