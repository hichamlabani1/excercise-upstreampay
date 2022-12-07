package com.upstreampay.exercise;

import com.upstreampay.exercise.dto.TransactionDto;
import com.upstreampay.exercise.exception.NotFoundException;
import com.upstreampay.exercise.exception.UpdateTransactionException;
import com.upstreampay.exercise.model.Command;
import com.upstreampay.exercise.model.PaymentMethodes;
import com.upstreampay.exercise.model.Transaction;
import com.upstreampay.exercise.model.TransactionStatus;
import com.upstreampay.exercise.service.CommandService;
import com.upstreampay.exercise.service.TransactionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ExerciseApplicationTests {

	@Autowired
	TransactionService transactionService;
	@Autowired
	CommandService commandService;

	static Transaction transaction1;
	static Transaction transaction2;
	@Test
	void contextLoads() throws InterruptedException {


		transactionService.addTransaction(transaction1);
		System.out.println("---------transaction 1 inserted------------");

		transactionService.addTransaction(transaction2);
		System.out.println("---------transaction 2 inserted------------");


		System.out.println("---------modification du status de transaction (to authorized)----------");
		updateStatusTransaction(toTransactionDto(transaction1)  , TransactionStatus.AUTHORIZED.name());

		System.out.println("---------modification du status de transaction (to captured)--------");
		updateStatusTransaction(toTransactionDto(transaction1)  , TransactionStatus.CAPTURED.name());

		System.out.println("---------all commands--------");
		List<Command> allCommands = getAllCommands();
		allCommands.forEach(command -> {
			System.out.println(command.toString());
		});
	}


	@BeforeAll
	static void init(){
		transaction1 = new Transaction();
		transaction1.setAmount(new BigDecimal("54.80"));
		transaction1.setPaymentMethode(PaymentMethodes.GIFT_CARD.name());
		Command command1 = new Command("skiGloves",4, new BigDecimal("10"));
		Command command2 = new Command("bonnet",4, new BigDecimal("14.80"));
		transaction1.addCommand(command1);
		transaction1.addCommand(command2);

		transaction2 = new Transaction();
		transaction2.setAmount(new BigDecimal("208"));
		transaction2.setPaymentMethode(PaymentMethodes.PAYPAL.name());
		Command commandTransaction2 = new Command("bike",4, new BigDecimal("208"));
		transaction2.addCommand(commandTransaction2);

	}

	private void updateStatusTransaction(TransactionDto transactionDto , String status){
		transactionDto.setStatus(TransactionStatus.valueOf(status));
		try {

			transactionService.updateTransaction(transactionDto);
		} catch (UpdateTransactionException e) {
			throw new RuntimeException(e);
		} catch (NotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	private List<Command> getAllCommands(){
		return commandService.getAllcommands();
	}
	private TransactionDto toTransactionDto(Transaction transaction){
		return new TransactionDto(transaction.getId() , transaction.getAmount() ,transaction.getPaymentMethode() , transaction.getStatus());
	}
}
