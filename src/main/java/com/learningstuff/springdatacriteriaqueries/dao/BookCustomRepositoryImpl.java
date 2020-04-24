package com.learningstuff.springdatacriteriaqueries.dao;

import com.learningstuff.springdatacriteriaqueries.models.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Md. Shamim
 * Date: ২৪/৪/২০
 * Time: ১০:৪৩ AM
 * Email: mdshamim723@gmail.com
 */

@AllArgsConstructor
@Repository
public class BookCustomRepositoryImpl implements BookCustomRepository {

    private final EntityManager em;

    @Override
    public List<Book> findBooksByAuthorAndTitle(String author, String title) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);

        Root<Book> book = cq.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if (author != null) {
//            predicates.add(cb.equal(cb.lower(book.get("author")), author.toLowerCase()));
            predicates.add(cb.equal(book.get("author"), author));
        }
        if (title != null) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Book> query = em.createQuery(cq);

        return query.getResultList();
    }

    @Override
    public Page<Book> findBooksByAuthorAndTitle(String author, String title, Pageable pageable) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Book> bookCQ = cb.createQuery(Book.class);

        Root<Book> book = bookCQ.from(Book.class);
        List<Predicate> predicates = new ArrayList<>();

        if (author != null) {
            predicates.add(cb.equal(book.get("author"), author));
        }
        if (title != null) {
            predicates.add(cb.like(book.get("title"), "%" + title + "%"));
        }
        bookCQ.where(predicates.toArray(new Predicate[0]));

        TypedQuery<Book> query = em.createQuery(bookCQ)
                .setFirstResult((int) pageable.getOffset()) // Page query
                .setMaxResults(pageable.getPageSize());

        List<Book> result = query.getResultList();

        // Create Count Query
        CriteriaQuery<Long> countCQ = cb.createQuery(Long.class);
        Root<Book> bookCount = countCQ.from(Book.class);
        countCQ.select(cb.count(bookCount)).where(predicates.toArray(new Predicate[0]));

        // Fetches the count of all Books as per given criteria
        Long count = em.createQuery(countCQ).getSingleResult();

        return new PageImpl<>(result, pageable, count);
    }
}
