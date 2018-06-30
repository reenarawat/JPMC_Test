package com.jpmc.assessment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.jpmc.assessment.dto.Currency;
import com.jpmc.assessment.dto.TransactionDTO;
import com.jpmc.assessment.main.ApplicationMain;
import com.jpmc.assessment.util.AppUtil;

public class ApplicationTest {

	@Test
	public void testApplication_whenCombinedScenarios_thenPrintsData() {
		try {
			ApplicationMain main = new ApplicationMain();

			TransactionDTO transaction = AppUtil.createTestData("FOO", 'B', 0.5f, Currency.SGP, 2016, 0, 1, 2016, 0, 2,
					200, 100.25);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("TOM", 'S', 0.5f, Currency.SGP, 2016, 0, 1, 2016, 0, 2, 500, 102.30);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("SCOTT", 'B', 0.36f, Currency.SAR, 2016, 0, 1, 2016, 0, 2, 50, 145.6);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("HARRY", 'S', 0.36f, Currency.SAR, 2016, 0, 1, 2016, 0, 2, 150, 142.6);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("BAR", 'B', 0.22f, Currency.AED, 2016, 0, 2, 2016, 0, 3, 450, 150.5);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("TIGER", 'S', 0.22f, Currency.AED, 2016, 0, 2, 2016, 0, 3, 210, 152.5);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("BARRY", 'B', 0.5f, Currency.SGP, 2016, 0, 2, 2016, 0, 3, 250, 100.25);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("TOMMY", 'B', 0.5f, Currency.SGP, 2016, 0, 2, 2016, 0, 3, 500, 102.30);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("KANE", 'B', 0.36f, Currency.SAR, 2016, 0, 2, 2016, 0, 3, 50, 145.6);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("MIKE", 'S', 0.34f, Currency.SAR, 2016, 0, 5, 2016, 0, 7, 50, 145.6);
			main.recordTransaction(transaction);

			transaction = AppUtil.createTestData("MARK", 'S', 0.25f, Currency.AED, 2016, 6, 15, 2016, 0, 7, 100, 148.3);
			main.recordTransaction(transaction);

			main.showAllReport();
			assertTrue(true);
		} catch (Exception e) {
			assertFalse(true);
		}
	}

}
