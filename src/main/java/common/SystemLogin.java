package common;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class SystemLogin {

	// Identifying web elements

	@FindBy(css = ".login")
	private WebElement signInbtn;

	@FindBy(css = "#login_form > div:nth-child(2) > div:nth-child(1)")
	private WebElement emailProprty;

	@FindBy(css = "div.form-group:nth-child(2)")
	private WebElement passwordProprty;

	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(id = "passwd")
	private WebElement password;
	
	@FindBy(id = "SubmitLogin")
	private WebElement submitButton;

	@FindBy(css = "#center_column > div:nth-child(2) > ol:nth-child(2) > li:nth-child(1)")
	private WebElement authenticationFailed;

	/**
	 * Go to Login page by clicking on SignIn button
	 */
	public void clickSignIn()
	{
		try {
			TimeUnit.SECONDS.sleep(2);
			signInbtn.click();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Enter the Login details
	 * @param userName	Username of the user
	 * @param userPassword	Password of the user

	 */
	public void enterLoginDetails(String userName, String userPassword) {
		try {
			TimeUnit.SECONDS.sleep(2);
			email.sendKeys(userName);
			password.sendKeys(userPassword);
			email.click();
		} catch (Exception ex) {
			ex.printStackTrace();

		}
	}

	/**
	 * Login by clicking on submitButton
	 */
	public void submitButton()
	{
		submitButton.click();
	}

	/**
	 * Verify Unsuccessful Login attempt
	 */
	public String unsuccessfulLoginMsg()
	{
		return authenticationFailed.getText();
	}


	/**
	 * Check class name of DIV for email
	 */
	public String checkEmailProperty()
	{
		return emailProprty.getAttribute("class");
	}

	/**
	 * Check class name of DIV for Password
	 */
	public String checkPassProperty()
	{
		return passwordProprty.getAttribute("class");

	}

	/**
	 * Verify emailI tool tip
	 * @param userName	Username of the user
	 */
	public String checkEmailTooltip(String userName)
	{
		email.sendKeys(userName);
		email.sendKeys(Keys.ENTER);
		return email.getAttribute("type");
	}
}
