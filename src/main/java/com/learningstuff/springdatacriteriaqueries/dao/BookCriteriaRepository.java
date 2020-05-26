package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:৪২ AM
 * Email: mdshamim723@gmail.com
 */

public interface BookCriteriaRepository {

    public List<Book> findBooksByAuthorAndTitle(String author, String title);

    public Page<Book> findBooksByAuthorAndTitle(String author, String title, Pageable pageable);

}
