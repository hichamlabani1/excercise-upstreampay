package com.upstreampay.exercise.controller;

import com.upstreampay.exercise.exception.BusinessException;
import com.upstreampay.exercise.model.Command;
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
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction){
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    @PutMapping("update-transaction/")
    public ResponseEntity<?> updateTransaction(@RequestBody Transaction transaction){
        ResponseEntity<?> response;
        try {
            response = ResponseEntity.ok(transactionService.updateTransaction(transaction));
        } catch (BusinessException e) {
            response = ResponseEntity.status(403).body(e.getMessage());
        }
        return response;
    }

    @GetMapping("getAll/")
    public List<Transaction> getAll(){
         return transactionService.getAllTransactions();
    }
}
