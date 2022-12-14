package com.upstreampay.exercise.service;

import com.upstreampay.exercise.dao.TransactionRepository;
import com.upstreampay.exercise.dto.TransactionDto;
import com.upstreampay.exercise.exception.NotFoundException;
import com.upstreampay.exercise.exception.StatusTransactionException;
import com.upstreampay.exercise.exception.UpdateTransactionException;
import com.upstreampay.exercise.model.Transaction;
import com.upstreampay.exercise.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(int id) throws  NotFoundException {
        Optional<Transaction> transaction = transactionRepository.findById(id);
        if (transaction.isEmpty()) {
            throw new NotFoundException("transaction with id "+id +" is not found");
        }
        return transaction.get();

    }

    public Transaction updateTransaction(TransactionDto newTransaction) throws UpdateTransactionException, NotFoundException, StatusTransactionException {
        Optional<Transaction> oldTransaction = transactionRepository.findById(newTransaction.getId());

        if (oldTransaction.isEmpty()) {
            throw new NotFoundException("transaction with id "+newTransaction.getId() +" is not found");
        }

        Transaction transaction = oldTransaction.get();
        if(!isValidStatusTransaction(newTransaction.getStatus())){
            throw new StatusTransactionException("invalid transaction status !");
        }

        if (transaction.getStatus().equals(TransactionStatus.CAPTURED.name()) && !transaction.getStatus().equals(newTransaction.getStatus())) {
            throw new UpdateTransactionException("transaction's status cannot be changed !");
        }
        //check if the transaction status was changed from 'new' to 'captured'
        else if (transaction.getStatus().equals(TransactionStatus.NEW.name()) && newTransaction.getStatus().equals(TransactionStatus.CAPTURED.name())) {
            throw new UpdateTransactionException("not authorized to change transaction's status");
        } else {
            return transactionRepository.save(toTransactionObj(newTransaction , transaction));
        }


    }

    public Transaction addTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    //this methode is added to prevent editing commands of a transaction
    public Transaction toTransactionObj(TransactionDto transactionDto , Transaction oldransaction){
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setAmount(transactionDto.getAmount());
        transaction.setStatus(transactionDto.getStatus());
        transaction.setPaymentMethode(transactionDto.getPaymentMethode());
        transaction.setCommands(oldransaction.getCommands());
        return transaction;
    }

    private boolean isValidStatusTransaction(String newStatus) {
        boolean isValidStatus=false;
        for(TransactionStatus transactionStatus : TransactionStatus.values()){
            if (transactionStatus.name().equals(newStatus)) {
                isValidStatus = true;
                break;
            }
        }
        return isValidStatus;


    }






}
