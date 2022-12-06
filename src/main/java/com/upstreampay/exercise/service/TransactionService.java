package com.upstreampay.exercise.service;
import com.upstreampay.exercise.dao.TransactionRepository;
import com.upstreampay.exercise.exception.BusinessException;
import com.upstreampay.exercise.model.Transaction;
import com.upstreampay.exercise.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;


    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getTransactionById(int id){
        return transactionRepository.findById(id);
    }

    public Transaction updateTransaction(Transaction newTransaction) throws BusinessException {
        Optional<Transaction> oldTransaction = transactionRepository.findById(newTransaction.getId());

        if(oldTransaction.isEmpty()){
            throw new BusinessException("transaction is not found "+ newTransaction.getId());
        }

        Transaction transaction = oldTransaction.get();
        if(transaction.getStatus().equals(TransactionStatus.CAPTURED.name()) && !transaction.getStatus().equals(newTransaction.getStatus())){
            throw new BusinessException("transaction's status cannot be changed !");
        }
        //check if the transaction status was changed from 'new' to 'captured'
        else if(transaction.getStatus().equals(TransactionStatus.NEW.name()) && newTransaction.getStatus().equals(TransactionStatus.CAPTURED.name())){
            throw new BusinessException("not authorized to change transaction's status");
        }

        else{
            return transactionRepository.save(newTransaction);
        }


    }

    public Transaction addTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }





}
