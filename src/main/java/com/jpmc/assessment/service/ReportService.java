package com.jpmc.assessment.service;

/**
 * @author Reena
 * 
 * Interface for all individual reports
 */
public interface ReportService {
	
	void generateIncomingReport();
	
	void generateOutgoingReport();
	
	void generateEntityIncomingRankReport();
	
	void generateEntityOutgoingRankReport();

}
