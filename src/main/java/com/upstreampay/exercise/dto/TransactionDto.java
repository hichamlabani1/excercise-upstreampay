package com.upstreampay.exercise.dto;

import com.upstreampay.exercise.model.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
    private int id;
    private BigDecimal amount;
    private String paymentMethode;
    private TransactionStatus status;
}
