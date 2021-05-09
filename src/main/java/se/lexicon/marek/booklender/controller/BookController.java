package se.lexicon.marek.booklender.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.marek.booklender.dto.BookDto;
import se.lexicon.marek.booklender.service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {
    BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BookDto>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> findById(@PathVariable("bookId") int bookId) {
        return ResponseEntity.ok(bookService.findById(bookId));
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> create(@RequestBody BookDto bookDto) {
        BookDto result = bookService.create(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

    }

    @PutMapping("/")
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {
        BookDto result = bookService.update(bookDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<BookDto> deleteById(@PathVariable("bookId") int bookId) {
        bookService.delete(bookId);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/find")
    public ResponseEntity<List<BookDto>> generalFind(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "available", required = false) boolean available,
            @RequestParam(value = "reserved", required = false) boolean reserved) {
        List<BookDto> resultList = bookService.generalFind(title, reserved, available);
        return ResponseEntity.ok(resultList);
    }
}
