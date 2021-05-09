package se.lexicon.marek.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.entity.LibraryUser;

import java.util.Optional;

public interface LibraryUserRepository extends CrudRepository<LibraryUser, Integer> {
    Optional<LibraryUser> findByEmail(String email);

}
