package se.lexicon.marek.booklender.service;

import se.lexicon.marek.booklender.dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> findByReserved(boolean reserved);

    List<BookDto> findByAvailable(boolean available);

    List<BookDto> findByTitle(String title);

    List<BookDto> generalFind(String title, boolean reserved, boolean available);

    BookDto findById(int bookId);

    List<BookDto> findAll();

    BookDto create(BookDto bookDto);

    BookDto update(BookDto bookDto);


    boolean delete(int bookId);
}
