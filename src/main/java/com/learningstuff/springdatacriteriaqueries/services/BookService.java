package com.learningstuff.springdatacriteriaqueries.services;

import com.learningstuff.springdatacriteriaqueries.dao.BookCustomRepository;
import com.learningstuff.springdatacriteriaqueries.models.Book;
import com.learningstuff.springdatacriteriaqueries.repositories.BookRepository;
import com.learningstuff.springdatacriteriaqueries.specifications.BookSpecifications;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.learningstuff.springdatacriteriaqueries.specifications.BookSpecifications.*;
import static org.springframework.data.jpa.domain.Specification.*;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:৩১ AM
 * Email: mdshamim723@gmail.com
 */

@AllArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;
//    private final BookCustomRepository bookCustomRepository;

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findAllByAuthorAndTitle(String author, String title) {
//        return bookCustomRepository.findBooksByAuthorAndTitle(author, title);
        return bookRepository.findBooksByAuthorAndTitle(author, title);
    }

    public Page<Book> findAllByAuthorAndTitle(String author, String title, PageRequest pageRequest) {
        return bookRepository.findBooksByAuthorAndTitle(author, title, pageRequest);
    }

    public List<Book> findAllByAuthorAndTitleWithSpecification(String author, String title) {
        return bookRepository.findAll(where(hasAuthor(author)).and(titleContaining(title)));
    }

    public Page<Book> findAllByAuthorAndTitleWithSpecification(String author, String title, PageRequest pageRequest) {
        return bookRepository.findAll(where(hasAuthor(author)).and(titleContaining(title)), pageRequest);
    }

    public Optional<Book> findOptionalBookById(long id) {
        return bookRepository.findById(id);
    }

    public void deleteBookById(long id) {
        bookRepository.findById(id).ifPresent(bookRepository::delete);
    }

}
