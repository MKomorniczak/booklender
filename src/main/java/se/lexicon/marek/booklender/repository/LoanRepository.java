package se.lexicon.marek.booklender.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.entity.Loan;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findByLoanTaker_UserId(int userId);

    //Loan findByBook_BookId(int bookId);
    List<Loan> findAllByBookBookId(int bookId);

    List<Loan> findByTerminated(boolean terminated);

}
