package se.lexicon.marek.booklender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.Entity.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findByReserved(boolean reserved);

    Book findByAvailable(boolean available);

    Book findByTitle(String title);

}
