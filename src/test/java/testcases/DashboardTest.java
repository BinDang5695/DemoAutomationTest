package testcases;

import common.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;

import org.testng.annotations.Test;

public class DashboardTest extends BaseTest {

	LoginPage loginPage;
	DashboardPage dashboardPage;

	@Test
	public void testCheckDashboardTotal() throws Exception {
		loginPage = new LoginPage();
		//loginPage.loginCRM(); // Chỉ login

		dashboardPage = loginPage.loginCRM();

		dashboardPage.verifyInvoicesAwaitingPaymentTotal("0 / 2");

	}
}