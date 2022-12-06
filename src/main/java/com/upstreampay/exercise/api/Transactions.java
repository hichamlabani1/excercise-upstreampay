package com.upstreampay.exercise.api;

import com.upstreampay.exercise.exception.BusinessException;
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

    @PostMapping("add-transaction/")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.status(201).body(transactionService.addTransaction(transaction));
    }

    @PutMapping("update-transaction/")
    public ResponseEntity<Transaction> updateTransaction(@RequestBody Transaction transaction) throws BusinessException {
        ResponseEntity<Transaction> response;
        response = ResponseEntity.ok(transactionService.updateTransaction(transaction));
        return response;
    }

    @GetMapping("getAll/")
    public List<Transaction> getAll() {
        return transactionService.getAllTransactions();
    }
}
