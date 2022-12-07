package com.upstreampay.exercise.api;

import com.upstreampay.exercise.dto.TransactionDto;
import com.upstreampay.exercise.exception.NotFoundException;
import com.upstreampay.exercise.exception.UpdateTransactionException;
import com.upstreampay.exercise.model.Transaction;
import com.upstreampay.exercise.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class Transactions {
    private final TransactionService transactionService;

    @PostMapping()
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(201).body(transactionService.addTransaction(transaction));
    }

    @PutMapping()
    public ResponseEntity<Transaction> updateTransaction(@RequestBody TransactionDto transactionDto) throws UpdateTransactionException, NotFoundException {
        return ResponseEntity.ok(transactionService.updateTransaction(transactionDto));
    }

    @GetMapping()
    public List<Transaction> getAll() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable int id) throws  NotFoundException {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }


}
