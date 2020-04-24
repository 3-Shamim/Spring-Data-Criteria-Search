package com.learningstuff.springdatacriteriaqueries.specifications;

import com.learningstuff.springdatacriteriaqueries.models.Book;
import org.springframework.data.jpa.domain.Specification;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১১:৩৯ AM
 * Email: mdshamim723@gmail.com
 */

public class BookSpecifications {

    public static Specification<Book> hasAuthor(String author) {
        return (book, cq, cb) -> cb.equal(book.get("author"), author);
    }

    public static Specification<Book> titleContaining(String title) {
        return (book, cq, cb) -> cb.like(book.get("title"), "%" + title + "%");
    }

}
