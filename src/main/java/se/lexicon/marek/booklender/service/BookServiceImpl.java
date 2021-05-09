package se.lexicon.marek.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marek.booklender.entity.Book;
import se.lexicon.marek.booklender.repository.BookRepository;
import se.lexicon.marek.booklender.dto.BookDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Book> books = bookRepository.findByReserved(reserved);
        List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public List<BookDto> findByAvailable(boolean available) {
        List<Book> books = bookRepository.findByAvailable(available);
        List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return bookDtos;
    }

    @Override
    public List<BookDto> findByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
        return bookDtos;


    }

    @Override
    public List<BookDto> generalFind(String title, boolean reserved, boolean available) {
        if (title != null) return findByTitle(title);
        else if (reserved != false) return findByReserved(reserved);
        else if (available != false) return findByAvailable(available);
        else return findAll();
    }

    @Override
    public BookDto findById(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("");
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            BookDto dto = modelMapper.map(optional, BookDto.class);
            return dto;
        }
        return null;
    }

    @Override
    public List<BookDto> findAll() {
        List<Book> books = new ArrayList<>();
        bookRepository.findAll().iterator().forEachRemaining(books::add);
        List<BookDto> bookDtos = books.stream().map(book -> modelMapper.map(book, BookDto.class)).collect(Collectors.toList());
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
        if (bookDto == null) throw new IllegalArgumentException("");
        if (bookDto.getBookId() == 0) throw new IllegalArgumentException("");
        Optional<Book> optional = bookRepository.findById(bookDto.getBookId());
        if (optional.isPresent())
            return modelMapper.map(bookRepository.save(modelMapper.map(bookDto, Book.class)), BookDto.class);
        return null;
    }

    @Override
    public boolean delete(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("");
        Optional<Book> optional = bookRepository.findById(bookId);
        if (optional.isPresent()) {
            bookRepository.deleteById(bookId);
            return true;
        }
        return false;
    }
}
