package com.learningstuff.springdatacriteriaqueries.repositories;

import com.learningstuff.springdatacriteriaqueries.dao.BookCriteriaRepository;
import com.learningstuff.springdatacriteriaqueries.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:৩১ AM
 * Email: mdshamim723@gmail.com
 */

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookCriteriaRepository, JpaSpecificationExecutor<Book> {
}
