package com.facultate.biblioteca.service;

import com.facultate.biblioteca.model.Loan;
import com.facultate.biblioteca.repository.LoanRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan saveLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public Loan getLoanById(Long id) {
        return loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Eroare: Împrumutul cu ID-ul " + id + " nu a fost găsit!"));
    }

    public void deleteLoan(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException("Eroare la ștergere: Împrumutul cu ID-ul " + id + " nu există!");
        }
        loanRepository.deleteById(id);
    }
}