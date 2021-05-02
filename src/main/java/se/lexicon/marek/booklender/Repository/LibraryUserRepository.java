package se.lexicon.marek.booklender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.Entity.LibraryUser;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    LibraryUser findByEmail(String email);

}
