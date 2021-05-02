package se.lexicon.marek.booklender.service;

import se.lexicon.marek.booklender.dto.LoanDto;

import java.util.List;

public interface LoanService {
    LoanDto findById(int loanId);

    List<LoanDto> findByBookId(int bookId);

    List<LoanDto> findByUserId(int bookId);

    List<LoanDto> findByTerminated(boolean terminated);

    List<LoanDto> findAll();

    LoanDto create(LoanDto loanDto);

    LoanDto update(LoanDto loanDto);

    //todo: void delete
    boolean delete(int loanId);
}
