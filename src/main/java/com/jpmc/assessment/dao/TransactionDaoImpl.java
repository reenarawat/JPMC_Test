package com.jpmc.assessment.dao;

import java.util.ArrayList;
import java.util.List;

import com.jpmc.assessment.dto.TransactionDTO;

/**
 * @author Reena
 * 
 * This DAO layer usually communicates with other service or DB.
 * Currently storing information in-memory
 */
public class TransactionDaoImpl implements CRUDOperations<TransactionDTO> {
	
	private static List<TransactionDTO> listOfTransactions = new ArrayList<>();

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.dao.CRUDOperations#add(java.lang.Object)
	 */
	@Override
	public void add(TransactionDTO t) {
		listOfTransactions.add(t);		
	}

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.dao.CRUDOperations#fetchData()
	 */
	@Override
	public List<TransactionDTO> fetchData() {
		return listOfTransactions;
	}

}
