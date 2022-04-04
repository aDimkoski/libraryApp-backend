package com.emt.libraryappemt.service.impl;

import com.emt.libraryappemt.model.Author;
import com.emt.libraryappemt.model.Book;
import com.emt.libraryappemt.model.dto.BookDto;
import com.emt.libraryappemt.model.exceptions.AuthorNotFoundException;
import com.emt.libraryappemt.model.exceptions.BookNotFoundException;
import com.emt.libraryappemt.repository.AuthorRepository;
import com.emt.libraryappemt.repository.BookRepository;
import com.emt.libraryappemt.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return this.bookRepository.findByNameLike(name);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author=this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthor()));

        Book book = new Book(bookDto.getName(), bookDto.getCategory(), author, bookDto.getAvailableCopies());
        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book=this.bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException(id));
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());
        Author author=this.authorRepository.findById(bookDto.getAuthor())
                .orElseThrow(()-> new AuthorNotFoundException(bookDto.getAuthor()));
        book.setAuthor(author);
        return Optional.of(this.bookRepository.save(book));
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book=this.bookRepository.findById(id)
                .orElseThrow(()->new BookNotFoundException(id));
        if(book.getAvailableCopies()>0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
        }
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }
}
