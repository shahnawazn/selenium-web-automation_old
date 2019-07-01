package ui;

import common.Registration;
import common.SystemLogin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

class TestExecutable {
	private WebDriver driver;
	private Registration register;
	private SystemLogin login;

	// Initialize properties and start browser
	@BeforeClass
	public void openDriver() {
		System.setProperty("webdriver.gecko.driver", "./resources/geckodriver.exe");
		// Get driver and delete all cookies
		WebDriver driver = new FirefoxDriver();
		driver.manage().deleteAllCookies();
		this.driver = driver;
		
		// Initialize page objects
		login = new SystemLogin();
		register = new Registration();
		
		// Initialize page elements
		driver.get("https://marksandspicy.com/login");
		PageFactory.initElements(driver, login);
		PageFactory.initElements(driver,register);
}
	
	// Close browser
	@AfterClass
	public void closeDriver()
	{
		driver.close();
	}

	//Test case for Authentication Failed
	@Parameters({"userName", "userPassword"})
	@Test (description = "Verify Authentication Failed Message ", priority = 1)
	public void authenticationFailVerification(String userName, String userPassword)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.enterLoginDetails(userName,userPassword);
		login.submitButton();
		//Assertions
		softAssert.assertEquals(login.unsuccessfulLoginMsg(),"Authentication failed.") ;
	}

	//Test case for empty EmailId
	@Parameters()
	@Test (description = "Verify Error message when no emailId entered", priority = 2)
	public void emptyEmailId()
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.submitButton();
		//Assertions
		softAssert.assertEquals(login.unsuccessfulLoginMsg(),"An email address required.") ;
	}

	//Test case empty Password
	@Parameters({"userName"})
	@Test (description = "Verify Error message when no Password entered", priority = 3)
	public void emptyPassword(String userName)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.enterLoginDetails(userName,"");
		login.submitButton();
		//Assertions
		softAssert.assertEquals(login.unsuccessfulLoginMsg(),"Password is required.") ;
	}

    //Input box validation for empty fields
    @Parameters()
    @Test (description = "Validate input fields for empty data", priority = 4)
    public void emptyInputFieldValidation()
    {
        SoftAssert softAssert = new SoftAssert();
        // Enter Login details
        login.clickSignIn();
        login.enterLoginDetails("","");
        //Assertions
        softAssert.assertEquals(login.checkEmailProperty(),"form-group form-error") ;
		softAssert.assertEquals(login.checkPassProperty(),"form-group form-error") ;
    }

	//Input box validation for wrong Input
	@Parameters({"wrongUserName", "wrongUserPassword"})
	@Test (description = "Validate input fields for wrong data", priority = 5)
	public void wrongInputFieldValidation(String userName,String userPassword)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.enterLoginDetails(userName,userPassword);
		//Assertions
		softAssert.assertEquals(login.checkEmailProperty(),"form-group form-error") ;
		softAssert.assertEquals(login.checkPassProperty(),"form-group form-error") ;
	}

	//Input box validation for Correct Inputs
	@Parameters({"userName", "userPassword"})
	@Test (description = "Validate input fields for correct data", priority = 6)
	public void correctInputFieldValidation(String userName,String userPassword)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.enterLoginDetails(userName,userPassword);
		//Assertions
		softAssert.assertEquals(login.checkEmailProperty(),"form-group form-ok") ;
		softAssert.assertEquals(login.checkPassProperty(),"form-group form-ok") ;
	}


	//Tool tip validation for email ID
	@Parameters({"wrongUserName"})
	@Test (description = "Validate tool tip for email ID", priority = 7)
	public void ValidateToolTipEmail(String userName)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Login details
		login.clickSignIn();
		login.checkEmailTooltip(userName);
		//Assertions
		softAssert.assertEquals(login.checkEmailTooltip(userName),"email") ;
	}

	//Verify User Registration
	@Parameters({"email", "password","cPassword","lName", "fName","day", "month","year", "street","apartment", "address","zip", "mobile","home"})
	@Test (description = "Validate input fields for correct data", priority = 8)
	public void registrationCheckCityState(String email, String password, String cPassword, String lName, String fName, String day, String month, String year, String street, String apartment, String address, String zip, String mobile, String home)
	{
		SoftAssert softAssert = new SoftAssert();
		// Enter Registration details details
		register.createAccount();
		register.enterRegistrationDetails(email, password,cPassword,lName, fName,day, month,year, street,apartment, address, zip, mobile,home);

		//Assertions
		softAssert.assertNotEquals(register.checkCity(),null);
		softAssert.assertNotEquals(register.checkState(),null);
		register.clickContinue();
	}
}
