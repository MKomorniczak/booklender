package se.lexicon.marek.booklender.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.marek.booklender.entity.Loan;
import se.lexicon.marek.booklender.repository.LoanRepository;
import se.lexicon.marek.booklender.dto.LoanDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanServiceImpl implements LoanService {
    LoanRepository loanRepository;
    ModelMapper modelMapper;


    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public LoanDto findById(long loanId) {
        if (loanId == 0) throw new IllegalArgumentException("");
        Optional<Loan> optional = loanRepository.findById(loanId);
        if (optional.isPresent()) {
            LoanDto loanDto = modelMapper.map(optional.get(), LoanDto.class);
            return loanDto;
        }
        return null;
    }


    @Override
    public List<LoanDto> findByBookId(int bookId) {
        if (bookId == 0) throw new IllegalArgumentException("bookId should not be null");
        List<Loan> loans = loanRepository.findAllByBookBookId(bookId);
        List<LoanDto> loanDtoList = loans.stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByUserId(int userId) {
        if (userId == 0) throw new IllegalArgumentException("bookId should not be null");
        List<Loan> loanList = loanRepository.findByLoanTaker_UserId(userId);
        List<LoanDto> loanDtoList = loanList.stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findByTerminated(boolean terminated) {
        if (terminated == false) throw new IllegalArgumentException("terminated should not be false");
        List<Loan> loanList =  loanRepository.findByTerminated(terminated);
        List<LoanDto> loanDtoList = loanList.stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public List<LoanDto> findAll() {
        List<Loan> loanList = new ArrayList<>();
        loanRepository.findAll().iterator().forEachRemaining(loanList::add);
        List<LoanDto> loanDtoList = loanList.stream().map(loan -> modelMapper.map(loan, LoanDto.class)).collect(Collectors.toList());
        return loanDtoList;
    }

    @Override
    public LoanDto create(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("");
        if (loanDto.getLoanId() != 0) throw new IllegalArgumentException("");
        Loan loan = modelMapper.map(loanDto, Loan.class);
        Loan savedLoan = loanRepository.save(loan);
        LoanDto convertedToDto = modelMapper.map(savedLoan, LoanDto.class);
        return convertedToDto;
    }

    @Override
    public LoanDto update(LoanDto loanDto) {
        if (loanDto == null) throw new IllegalArgumentException("");
        if (loanDto.getLoanId() == 0) throw new IllegalArgumentException("");
        Optional<Loan> optional = loanRepository.findById(loanDto.getLoanId());
        if (optional.isPresent())
            return modelMapper.map(loanRepository.save(modelMapper.map(loanDto, Loan.class)), LoanDto.class);
        return null;
    }


    @Override
    public boolean delete(long loanId) {
        if (loanId == 0) throw new IllegalArgumentException("loanId should not be null");
       Optional<Loan> optional = loanRepository.findById(loanId);
       if (optional.isPresent()){
           loanRepository.deleteById(loanId);
           return true;
       }
        return false;
    }
}
