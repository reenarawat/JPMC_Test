package com.jpmc.assessment.service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.jpmc.assessment.dao.CRUDOperations;
import com.jpmc.assessment.dao.TransactionDaoImpl;
import com.jpmc.assessment.dto.TransactionDTO;
import com.jpmc.assessment.util.AppConstants;

/**
 * @author Reena
 *
 */
public class ReportServiceImpl implements ReportService, AppConstants {
	
	private CRUDOperations<TransactionDTO> transactionDao;
	
	private final SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
	private final DecimalFormat moneyFormat = new DecimalFormat("####0.00");
	
	/**
	 * 
	 */
	public ReportServiceImpl() {
		this.transactionDao = new TransactionDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.service.ReportService#generateIncomingReport()
	 * 
	 * Computes amount in USD settled incoming everyday
	 */
	@Override
	public void generateIncomingReport() {
		List<TransactionDTO> data = transactionDao.fetchData();
		Map<String, Double> inTotalSettledAmount = new TreeMap<>();
		
		for (TransactionDTO transaction : data) {
			if(transaction.getTransactionType() == OPERATION_SELL) {
				calculateAmountOfTrade(inTotalSettledAmount, transaction);
			}
		}
		printDailyAmountOfTradeData(inTotalSettledAmount);
	}

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.service.ReportService#generateOutgoingReport()
	 * 
	 * Computes amount in USD settled outgoing everyday
	 */
	@Override
	public void generateOutgoingReport() {
		List<TransactionDTO> data = transactionDao.fetchData();
		Map<String, Double> outTotalSettledAmount = new TreeMap<>();
		
		for (TransactionDTO transaction : data) {
			if(transaction.getTransactionType() == OPERATION_BUY) {
				calculateAmountOfTrade(outTotalSettledAmount, transaction);
			}
		}
		printDailyAmountOfTradeData(outTotalSettledAmount);
	}
	
	/**
	 * @param totalSettledAmount
	 * @param transaction
	 * 
	 * Helper method to calculate amount of trade date-wise.
	 * This method is generic and used by both incoming & outgoing trns data
	 */
	private void calculateAmountOfTrade(Map<String, Double> totalSettledAmount, TransactionDTO transaction) {
		double amountOfTrade = computeAmountOfTrade(transaction);
		String dateKey = createDateKey(transaction.getSettlementDate());
		
		if(totalSettledAmount.containsKey(dateKey)){
			amountOfTrade = totalSettledAmount.get(dateKey) + amountOfTrade;
		}
		totalSettledAmount.put(dateKey, amountOfTrade);
	}
	
	/**
	 * @param totalSettledAmount
	 * Helper method to print amount settled everyday
	 */
	private void printDailyAmountOfTradeData(Map<String, Double> totalSettledAmount) {
		/* Sort & Print */
		System.out.println("Date \t\t Amount");
		for(Map.Entry<String, Double> record : totalSettledAmount.entrySet()){
			System.out.println(record.getKey() + " \t " + Double.valueOf(moneyFormat.format(record.getValue())));
		}
	}
	
	/**
	 * @param date
	 * @return
	 */
	private String createDateKey(Date date) {
		return sdf.format(date);
	}
	
	/**
	 * @param transaction
	 * @return
	 * 
	 * Helper method to compute amount of trade for a given transaction
	 */
	private double computeAmountOfTrade(TransactionDTO transaction) {
		return transaction.getPricePerUnit() * transaction.getUnits() * transaction.getAgreedFx();
	}

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.service.ReportService#generateEntityIncomingRankReport()
	 * 
	 * Computes entity ranking for all incoming transactions and ranks based on 
	 * amount of trade entity is dealing
	 */
	@Override
	public void generateEntityIncomingRankReport() {
		List<TransactionDTO> incomingData = new ArrayList<>(transactionDao.fetchData());
		incomingData.removeIf(record -> record.getTransactionType() == OPERATION_SELL);
		sortTransactionOnAmountOfTrade(incomingData);
		printRankData(incomingData);
	}

	/* (non-Javadoc)
	 * @see com.jp.assessment.Test1.service.ReportService#generateEntityOutgoingRankReport()
	 * 
	 * Computes entity ranking for all outgoing transactions and ranks based on 
	 * amount of trade entity is dealing
	 */
	@Override
	public void generateEntityOutgoingRankReport() {
		List<TransactionDTO> outgoingData = new ArrayList<>(transactionDao.fetchData());
		outgoingData.removeIf(record -> record.getTransactionType() == OPERATION_BUY);
		sortTransactionOnAmountOfTrade(outgoingData);
		printRankData(outgoingData);
	}

	/**
	 * @param data
	 * 
	 * Helper method to print data
	 */
	private void printRankData(List<TransactionDTO> data) {
		System.out.println("Rank \t Entity \t Amount");
		for (int i = 0; i < data.size(); i++) {
			TransactionDTO record = data.get(i);
			Double amountOfTrade = record.getPricePerUnit() * record.getUnits() * record.getAgreedFx();
			System.out.println((i + 1) + " \t " + data.get(i).getEntity() + " \t\t " + moneyFormat.format(amountOfTrade));
		}
	}
	
	/**
	 * @param data
	 * 
	 * Helper method to sort data in descending order for entity ranking.
	 * It sorts based on amount of trade entity is dealing
	 */
	private void sortTransactionOnAmountOfTrade(List<TransactionDTO> data) {
		Collections.sort(data, new Comparator<TransactionDTO>() {
			@Override
			public int compare(TransactionDTO o1, TransactionDTO o2) {
				return Double.compare((o2.getPricePerUnit() * o2.getUnits() * o2.getAgreedFx()), 
						(o1.getPricePerUnit() * o1.getUnits() * o1.getAgreedFx()));
			}
		});
	}

}
