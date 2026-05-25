package com.facultate.biblioteca.controller;

import com.facultate.biblioteca.model.Loan;
import com.facultate.biblioteca.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("loan")
public class LoanController {
    private static final Logger logger = LoggerFactory.getLogger(LoanController.class);

    private final LoanService loanService;

    @GetMapping("all")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping(params = "id")
    public Loan getLoanById(@RequestParam final Long id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan saveLoan(@RequestBody Loan loan) {
        return loanService.saveLoan(loan);
    }

    @PutMapping
    public Loan updateLoan(@RequestBody Loan loan) {
        return loanService.updateLoan(loan);
    }

    @DeleteMapping
    public void deleteLoan(@RequestParam final Long id) {
        loanService.deleteLoan(id);
    }
}
