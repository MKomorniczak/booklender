package se.lexicon.marek.booklender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.Entity.Book;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    List<Book> findByReserved(boolean reserved);

    List<Book> findByAvailable(boolean available);

    List<Book> findByTitle(String title);

}
