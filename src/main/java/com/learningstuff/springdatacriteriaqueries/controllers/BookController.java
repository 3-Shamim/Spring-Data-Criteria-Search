package com.learningstuff.springdatacriteriaqueries.controllers;

import com.learningstuff.springdatacriteriaqueries.models.Book;
import com.learningstuff.springdatacriteriaqueries.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:৩৬ AM
 * Email: mdshamim723@gmail.com
 */

@AllArgsConstructor
@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @GetMapping(value = "")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable(value = "id") long id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findOptionalBookById(id));
    }

    @GetMapping(value = "/criteria-search")
    public ResponseEntity<?> criteriaSearch(@RequestParam(value = "author", required = false) String author,
                                            @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByAuthorAndTitle(author, title));
    }

    @GetMapping(value = "/criteria-search-pagination")
    public ResponseEntity<?> criteriaSearchPagination(@RequestParam(value = "author", required = false) String author,
                                                      @RequestParam(value = "title", required = false) String title,
                                                      @RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByAuthorAndTitle(author, title, PageRequest.of(page, size)));
    }

    @GetMapping(value = "/specification-search")
    public ResponseEntity<?> specificationSearch(@RequestParam(value = "author", required = false) String author,
                                                 @RequestParam(value = "title", required = false) String title) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByAuthorAndTitle(author, title));
    }

    @GetMapping(value = "/specification-search-pagination")
    public ResponseEntity<?> specificationSearchPagination(@RequestParam(value = "author", required = false) String author,
                                                           @RequestParam(value = "title", required = false) String title,
                                                           @RequestParam(value = "page", defaultValue = "0") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size) {

        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllByAuthorAndTitle(author, title, PageRequest.of(page, size)));
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody @Valid Book book) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(book));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable(value = "id") long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted.");
    }

}
