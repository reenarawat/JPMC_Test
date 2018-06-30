package com.jpmc.assessment.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.jpmc.assessment.dao.CRUDOperations;
import com.jpmc.assessment.dao.TransactionDaoImpl;
import com.jpmc.assessment.dto.Currency;
import com.jpmc.assessment.dto.TransactionDTO;
import com.jpmc.assessment.exception.CurrencyNotFoundException;
import com.jpmc.assessment.util.AppConstants;
import com.jpmc.assessment.util.AppUtil;

/**
 * @author Reena
 *
 */
public class TransactionServiceImpl implements TransactionService, AppConstants {
	
	private CRUDOperations<TransactionDTO> transactionDao;
	
	/**
	 * 
	 */
	public TransactionServiceImpl(){
		this.transactionDao = new TransactionDaoImpl();
	}
	
	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.service.TransactionService#performTransaction(com.jp.assessment.Test1.dto.TransactionDTO)
	 * 
	 * This method records transaction by verifying settlement date
	 */
	@Override
	public void performTransaction(TransactionDTO transactionDTO) {
		verifySettlementDate(transactionDTO);
		
		transactionDao.add(transactionDTO);
	}
	
	/**
	 * @param transactionDTO
	 */
	private void verifySettlementDate(TransactionDTO transactionDTO) {
		List<String> nonWorkDays = getNonWorkDaysForCurrency(transactionDTO.getCurrency());
		
		LocalDateTime settleDate = transactionDTO.getSettlementDate().
				toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		while(nonWorkDays.contains(settleDate.getDayOfWeek().toString())){
			settleDate = settleDate.plusDays(1);
			transactionDTO.setSettlementDate(Date.from(settleDate.atZone(ZoneId.systemDefault()).toInstant()));
		}
	}
	
	/**
	 * @param currency
	 * @return
	 */
	private static List<String> getNonWorkDaysForCurrency(Currency currency) {
		String days = AppUtil.getProperty(currency.toString());
		if(days == null){
			throw new CurrencyNotFoundException(currency.toString() + " currency information not found");
		}
		
		return Arrays.asList(days.split(PROPERTY_STRING_SEPARATOR));
	}

}
