package se.lexicon.marek.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marek.booklender.Entity.Book;
import se.lexicon.marek.booklender.Entity.LibraryUser;
import se.lexicon.marek.booklender.Repository.BookRepository;
import se.lexicon.marek.booklender.dto.BookDto;
import se.lexicon.marek.booklender.dto.LibraryUserDto;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    ModelMapper modelMapper;

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BookDto> findByReserved(boolean reserved) {
        return null;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        return null;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        return null;
    }

    @Override
    public BookDto findById(int bookId) {
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        return null;
    }

    @Override
    public BookDto create(BookDto bookDto) {
        if (bookDto == null) throw new IllegalArgumentException("");
        if (bookDto.getBookId() != 0) throw new IllegalArgumentException("");
        Book bookEntity = modelMapper.map(bookDto, Book.class);
        Book savedEntity = bookRepository.save(bookEntity);
        BookDto converted = modelMapper.map(savedEntity, BookDto.class);
        return converted;
    }

    @Override
    public BookDto update(BookDto bookDto) {
        return null;
    }

    @Override
    public boolean delete(int bookId) {
        return false;
    }
}
