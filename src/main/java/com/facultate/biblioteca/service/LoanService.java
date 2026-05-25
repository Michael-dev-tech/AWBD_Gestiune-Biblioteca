package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Loan;
import com.facultate.biblioteca.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan saveLoan(Loan loan) {
        LOGGER.info("Saving loan");
        Loan savedLoan = loanRepository.save(loan);
        LOGGER.debug("Saved loan with id={}", savedLoan.getId());
        return savedLoan;
    }

    public List<Loan> getAllLoans() {
        LOGGER.debug("Fetching all loans");
        List<Loan> loans = loanRepository.findAll();
        LOGGER.info("Fetched {} loans", loans.size());
        return loans;
    }

    public Loan getLoanById(Long id) {
        LOGGER.debug("Fetching loan with id={}", id);
        return loanRepository.findById(id)
                .orElseThrow(() -> {
                    LOGGER.warn("Loan with id={} was not found", id);
                    return new RuntimeException("Eroare: Imprumutul cu ID-ul " + id + " nu a fost gasit!");
                });
    }

    public void deleteLoan(Long id) {
        LOGGER.info("Deleting loan with id={}", id);
        if (!loanRepository.existsById(id)) {
            LOGGER.warn("Cannot delete loan with id={} because it does not exist", id);
            throw new RuntimeException("Eroare la stergere: Imprumutul cu ID-ul " + id + " nu exista!");
        }
        loanRepository.deleteById(id);
        LOGGER.debug("Deleted loan with id={}", id);
    }

    public Loan updateLoan(final Loan loan) {
        LOGGER.info("Updating loan with id={}", loan.getId());
        final var existingLoan = loanRepository.findById(loan.getId())
                .orElseThrow(() -> {
                    LOGGER.warn("Cannot update loan with id={} because it does not exist", loan.getId());
                    return new RuntimeException("Eroare: Imprumutul cu ID-ul " + loan.getId() + " nu a fost gasit!");
                });

        existingLoan.setUser(loan.getUser());
        existingLoan.setBook(loan.getBook());
        existingLoan.setLoanDate(loan.getLoanDate());
        existingLoan.setReturnDate(loan.getReturnDate());
        Loan updatedLoan = loanRepository.save(existingLoan);
        LOGGER.debug("Updated loan with id={}", updatedLoan.getId());
        return updatedLoan;
    }
}
