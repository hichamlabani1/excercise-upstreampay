package com.upstreampay.exercise.model;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private String paymentMethode;
    private String status="NEW";

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentMethode(String paymentMethode) {
        this.paymentMethode = paymentMethode;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getPaymentMethode() {
        return paymentMethode;
    }

    public String getStatus() {
        return status;
    }

    public List<Command> getCommands() {
        return commands;
    }

    @OneToMany(cascade=CascadeType.ALL)
    private List<Command> commands;
}
