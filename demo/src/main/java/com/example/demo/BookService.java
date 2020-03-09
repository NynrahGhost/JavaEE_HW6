package com.example.demo;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

    private final EntityManager entityManager;

    @Transactional
    public BookEntity createBook(String title, String isbn, String author) {
        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setAuthor(author);

        return entityManager.merge(book);
    }
    
    public List<BookEntity> getBooks() {
    	return entityManager.createQuery("FROM BookEntity", BookEntity.class).getResultList();
    }
    
    public List<BookEntity> getBook(int id) {
    	return entityManager.createQuery("FROM BookEntity WHERE id="+id, BookEntity.class).getResultList();
    }
    
    public List<BookEntity> getBookByTitle(String title) {
    	return entityManager.createQuery("FROM BookEntity WHERE title="+title, BookEntity.class).getResultList();
    }
    
    public List<BookEntity> getBookByISBN(String isbn) {
    	return entityManager.createQuery("FROM BookEntity WHERE isbn="+isbn, BookEntity.class).getResultList();
    }
    
    public List<BookEntity> getBookByAuthor(String author) {
    	return entityManager.createQuery("FROM BookEntity WHERE author="+author, BookEntity.class).getResultList();
    }

}