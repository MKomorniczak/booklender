package se.lexicon.marek.booklender.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long loanId;
    @ManyToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
    private LibraryUser loanTaker;
    @ManyToOne(cascade = CascadeType.ALL)
    @Column(nullable = false)
    private Book book;
    @Column(nullable = false)
    private LocalDate loanDate;
    @Column(nullable = false)
    private boolean terminated;

    public Loan(LibraryUser loanTaker, Book book, LocalDate loanDate, boolean terminated) {
        this.loanTaker = loanTaker;
        this.book = book;
        this.loanDate = loanDate;
        this.terminated = terminated;
    }

    public Loan() {
    }

    public long getLoanId() {
        return loanId;
    }

    public LibraryUser getLoanTaker() {
        return loanTaker;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public boolean isTerminated() {
        return terminated;
    }


    public void setLoanTaker(LibraryUser loanTaker) {
        this.loanTaker = loanTaker;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return loanId == loan.loanId && terminated == loan.terminated && Objects.equals(loanTaker, loan.loanTaker) && Objects.equals(book, loan.book) && Objects.equals(loanDate, loan.loanDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, loanTaker, book, loanDate, terminated);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", loanTaker=" + loanTaker +
                ", book=" + book +
                ", loanDate=" + loanDate +
                ", terminated=" + terminated +
                '}';
    }

    public boolean isOverdue() {
        // check if is terminated is true => false
        // 2021-04-23 + 7 = 2021-04-30
        // get loan date + maxloandays
        // compare with current date

        if (isTerminated())
            return false;
        else {
            LocalDate overdueDate = loanDate.plusDays(book.getMaxLoanDays());
            LocalDate now = LocalDate.now();
            return now.isAfter(overdueDate);


        }
/*        Book book = new Book();
         book.setMaxLoanDays(7);
        loanDate = LocalDate.parse("2021-04-20");
        LocalDate overdueDate = loanDate.plusDays(book.getMaxLoanDays());
        LocalDate now = LocalDate.now();
        if (now.isAfter(overdueDate))
            return true;
        else {
            return false;
        }*/
    }

    public BigDecimal getFine() {

        return null;
    }


    public boolean extendLoan(int days) {

        return false;
    }
}
