package com.jpmc.assessment.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Properties;

import com.jpmc.assessment.dto.Currency;
import com.jpmc.assessment.dto.TransactionDTO;
import com.jpmc.assessment.exception.PropertyFileAccessException;

/**
 * @author Reena
 *
 */
public class AppUtil {
	
	private static Properties properties;
	
	private AppUtil() {}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		if(properties == null){
			readPropertiesFile();
		}
		return properties.getProperty(key);
	}
	
	/**
	 * 
	 */
	private static void readPropertiesFile() {
		properties = new Properties();
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("currencyNonWorkDays.properties");
		if(inputStream == null) {
			throw new PropertyFileAccessException("currencyNonWorkDays.properties file not found");
		}
		
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new PropertyFileAccessException(e);
		} finally {
			if(inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					/* Only Log */
				}
			}
		}
	}

	/**
	 * @param entity
	 * @param transactType
	 * @param agreedFx
	 * @param currency
	 * @param instructYear
	 * @param instructMonth
	 * @param instructDay
	 * @param settleYear
	 * @param settleMonth
	 * @param settleDay
	 * @param units
	 * @param pricePerUnit
	 * @return
	 * 
	 * This method is for brevity to form sample data called from main method
	 */
	public static TransactionDTO createTestData(String entity, char transactType, float agreedFx, 
			Currency currency, int instructYear, int instructMonth, int instructDay,
			int settleYear, int settleMonth, int settleDay, int units, double pricePerUnit) {
		
		TransactionDTO transaction = new TransactionDTO();
		transaction.setEntity(entity);
		transaction.setTransactionType(transactType);
		transaction.setAgreedFx(agreedFx);
		transaction.setCurrency(currency);
	
		Calendar instructCal = Calendar.getInstance();
		instructCal.set(instructYear, instructMonth, instructDay);
		transaction.setInstructionDate(instructCal.getTime());
	
		Calendar settleCal = Calendar.getInstance();
		settleCal.set(settleYear, settleMonth, settleDay);
		transaction.setSettlementDate(settleCal.getTime());
		
		transaction.setUnits(units);
		transaction.setPricePerUnit(pricePerUnit);
		return transaction;
	}

}
