package com.upstreampay.exercise.model;
import javax.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private BigDecimal amount;
    private String paymentMethode;
    private String status = TransactionStatus.NEW.name();
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="transaction_id")
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command){
        commands.add(command);
    }
}
