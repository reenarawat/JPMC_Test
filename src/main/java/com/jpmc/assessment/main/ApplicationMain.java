package com.jpmc.assessment.main;

import com.jpmc.assessment.dto.Currency;
import com.jpmc.assessment.dto.TransactionDTO;
import com.jpmc.assessment.service.ReportService;
import com.jpmc.assessment.service.ReportServiceImpl;
import com.jpmc.assessment.service.TransactionService;
import com.jpmc.assessment.service.TransactionServiceImpl;
import com.jpmc.assessment.util.AppUtil;

/**
 * @author Reena
 *
 */
public class ApplicationMain {
	
	private TransactionService transactionService;
	
	private ReportService reportService;
	
	/**
	 * 
	 */
	public ApplicationMain() {
		this.transactionService = new TransactionServiceImpl();
		this.reportService = new ReportServiceImpl();
	}
	
	/* This is the main method to execute the application.
	 * It contains sample data which will be recorded and
	 * later report will be shown */
	public static void main(String[] args) {
		ApplicationMain main = new ApplicationMain();
		
		TransactionDTO transaction = AppUtil.createTestData("FOO", 'B', 0.5f, Currency.SGP, 2016, 0, 1, 2016, 0 , 2, 200, 100.25);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("TOMMY", 'S', 0.5f, Currency.SGP, 2016, 0, 1, 2016, 0 , 2, 500, 102.30);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("SCOTT", 'B', 0.36f, Currency.SAR, 2016, 0, 1, 2016, 0 , 2, 50, 145.6);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("HARRY", 'S', 0.36f, Currency.SAR, 2016, 0, 1, 2016, 0 , 2, 150, 142.6);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("BARRY", 'B', 0.22f, Currency.AED, 2016, 0, 2, 2016, 0 , 3, 450, 150.5);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("TIGER", 'S', 0.22f, Currency.AED, 2016, 0, 2, 2016, 0 , 3, 210, 152.5);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("BAR", 'B', 0.5f, Currency.SGP, 2016, 0, 2, 2016, 0 , 3, 250, 100.25);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("TOMMY", 'B', 0.5f, Currency.SGP, 2016, 0, 2, 2016, 0 , 3, 500, 102.30);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("KANE", 'B', 0.36f, Currency.SAR, 2016, 0, 2, 2016, 0 , 3, 50, 145.6);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("MIKE", 'S', 0.34f, Currency.SAR, 2016, 0, 5, 2016, 0 , 7, 50, 145.6);
		main.recordTransaction(transaction);
		
		transaction = AppUtil.createTestData("MARK", 'S', 0.25f, Currency.AED, 2016, 6, 15, 2016, 0 , 7, 100, 148.3);
		main.recordTransaction(transaction);
		
		main.showAllReport();
	}

	/**
	 * @param transactionDTO
	 * 
	 * Used for storing/recording information sent to the application.
	 */
	public void recordTransaction(TransactionDTO transactionDTO) {
		transactionService.performTransaction(transactionDTO);
	}
	
	/**
	 * Used for display report based on transactions recorded so far.
	 */
	public void showAllReport() {
		System.out.println("Datewise Incoming Report \n");
		reportService.generateIncomingReport();
		
		System.out.println("\n =========== \n");
			
		System.out.println("Datewise Outgoing Report \n");
		reportService.generateOutgoingReport();
		
		System.out.println("\n =========== \n");
		
		System.out.println("Entity Rank For Incoming Report \n");
		reportService.generateEntityIncomingRankReport();
		
		System.out.println("\n =========== \n");
		
		System.out.println("Entity Rank For Outgoing Report \n");
		reportService.generateEntityOutgoingRankReport();
	}
	
}
