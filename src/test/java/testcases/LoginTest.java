package testcases;

import common.BaseTest;
import dataproviders.DataProviderFactory;
import helpers.CaptureHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import listeners.TestListener;
import pages.LoginPage;
import utils.LogUtils;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Hashtable;
	
	@Listeners(TestListener.class)
	public class LoginTest extends BaseTest{
		
			LoginPage loginPage;
			
			@Epic("Regression")
			@Feature("DMS")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Link("https://anhtester.com/dms/login/873")
			@Issue("https://jira.com/anhtester/dms/issue")
			@Test(priority = 0, dataProvider = "data_provider_login_success", dataProviderClass = DataProviderFactory.class)
			public void loginSuccess(String email, String password) throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(email, password);
				loginPage.verifyLoginSuccess();
			}
			
			@Epic("Regression")
			@Feature("DMS")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.CRITICAL)
			@Test(priority = 1, dataProvider = "data_provider_login_excel", dataProviderClass = DataProviderFactory.class)
			public void loginSuccessFromDataProvider(String email, String password) throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(email, password);
				loginPage.verifyLoginSuccess();
			}
			
			@Epic("Regression")
			@Feature("Inventory")
			@Story("Login")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Test(priority = 2, dataProvider = "data_provider_login_excel_hashtable", dataProviderClass = DataProviderFactory.class)
			public void loginSuccessFromDataProviderHashTable(Hashtable<String, String> data) throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM(data.get("Email"), data.get("Password"));
				loginPage.verifyLoginSuccess();
			}
			
			@Feature("Smoke")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Test (priority = 3)
			public void loginFailWithEmailInvalid() throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM("admin@example123.com", "123456");
				loginPage.verifyLoginFail("Invalid email or password");
			}
			
			@Feature("Smoke")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Test (priority = 4)
			public void loginFailWithPasswordInvalid() throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM("admin@example.com", "123456789");
				loginPage.verifyLoginFail("Invalid email or password");
			}
			
			@Feature("Smoke")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.NORMAL)
			@Test (priority = 5)
			public void loginFailWithEmailNull() throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM("", "123456");
				loginPage.verifyLoginFail("The Email Address field is required.");
			}
			
			@Feature("Smoke")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.MINOR)
			@Test (priority = 6)
			public void loginFailWithPasswordNull() throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM("admin@example.com", "");
				loginPage.verifyLoginFail("The Password field is required.");
			}
			
			@Feature("Smoke")
			@Owner("Bin Tester")
			@Severity(SeverityLevel.MINOR)
			@Test (priority = 7)
			public void loginFailWithEmailNullAndPasswordNull() throws Exception
			{
				loginPage = new LoginPage();
				loginPage.loginCRM("", "");
				loginPage.verifyLoginFailWithNullFields();
			}
}