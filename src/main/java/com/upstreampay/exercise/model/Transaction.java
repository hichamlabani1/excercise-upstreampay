package com.upstreampay.exercise.model;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private String paymentMethode;
    private String status="new";
    @OneToMany(cascade=CascadeType.ALL)
    private List<Command> commands;
}
