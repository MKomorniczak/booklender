package se.lexicon.marek.booklender.Repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.marek.booklender.Entity.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Loan findByLoanTaker_UserId(int userId);

    Loan findByBook_BookId(int bookId);

    Loan findByTerminated(boolean terminated);

}
