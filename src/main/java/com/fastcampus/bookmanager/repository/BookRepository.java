package com.fastcampus.bookmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.bookmanager.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
