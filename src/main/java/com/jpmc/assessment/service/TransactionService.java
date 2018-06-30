package com.jpmc.assessment.service;

import com.jpmc.assessment.dto.TransactionDTO;

/**
 * @author Reena
 *
 */
public interface TransactionService {
	
	void performTransaction(TransactionDTO transactionDTO);
	
}
