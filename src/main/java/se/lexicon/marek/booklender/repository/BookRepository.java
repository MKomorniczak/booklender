package se.lexicon.marek.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByReserved(boolean reserved);

    List<Book> findByAvailable(boolean available);

    List<Book> findByTitle(String title);

}
