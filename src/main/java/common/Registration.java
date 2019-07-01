package common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.concurrent.TimeUnit;

public class Registration {

    // Identifying web elements
    @FindBy(id = "SubmitCreate")
    private WebElement createAccountBtn;

    @FindBy(id = "email")
    private WebElement emailId;

    @FindBy(id = "password")
    private WebElement actualPassword;

    @FindBy(id = "password2")
    private WebElement confirmPassword;

    @FindBy(css = "span.radio-content:nth-child(2) > label:nth-child(2)")
    private WebElement civilityDetails;

    @FindBy(id = "nom")
    private WebElement lastName;

    @FindBy(id = "prenom")
    private WebElement firstName;

    @FindBy(id = "dateJour")
    private WebElement birthDay;

    @FindBy(id = "dateMois")
    private WebElement bithMonth;

    @FindBy(id = "dateAnnee")
    private WebElement birthYear;

    @FindBy(id = "adresse")
    private WebElement streetName;

    @FindBy(id = "adresseDetail")
    private WebElement addressLine;

    @FindBy(id = "adresseDetail2")
    private WebElement addressLineCont;

    @FindBy(id = "codePostal")
    private WebElement zipCode;

    @FindBy(id = "ville")
    private WebElement cityName;

    @FindBy(id = "lieuDit")
    private WebElement stateName;

    @FindBy(id = "telephonePortable")
    private WebElement mobileNum;

    @FindBy(id = "telephoneFixe")
    private WebElement homePhone;

    @FindBy(xpath = "//label[@class='CheckboxPerso']")
    private WebElement checkBox;

    @FindBy(id = "BtnCreationSubmit")
    private WebElement continueButton;


    /**
     * Click on Create Account button
     */
    public void createAccount() {
        try {
            TimeUnit.SECONDS.sleep(2);
            createAccountBtn.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Enter the registration details
     *
     * @param email     Email Id
     * @param password  Password
     * @param cPassword Confirm Password
     * @param lName     Last Name
     * @param fName     First Name
     * @param day       Day for the birth date
     * @param month     Month for the birth date
     * @param year      Year for the birth date
     * @param street    Street
     * @param apartment Address Line1
     * @param address   Address Line2
     * @param zip       Zip Code
     * @param mobile    Mobile Number
     * @param home      Home Number
     */
    public void enterRegistrationDetails(String email, String password, String cPassword, String lName, String fName, String day, String month, String year, String street, String apartment, String address, String zip, String mobile, String home) {
        try {
            TimeUnit.SECONDS.sleep(2);
            emailId.sendKeys(email);
            actualPassword.sendKeys(password);
            confirmPassword.sendKeys(cPassword);
            civilityDetails.click();
            lastName.sendKeys(lName);
            firstName.sendKeys(fName);
            birthDay.sendKeys(day);
            bithMonth.sendKeys(month);
            birthYear.sendKeys(year);
            streetName.sendKeys(street);
            addressLine.sendKeys(apartment);
            addressLineCont.sendKeys(address);
            zipCode.sendKeys(zip);
            mobileNum.sendKeys(mobile);
            homePhone.sendKeys(home);
            checkBox.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Click on Continue button
     */
    public void clickContinue() {
        continueButton.click();
    }

    /**
     * Check Auto populated City Name
     */
    public String checkCity() {
        return cityName.getAttribute("value");
    }

    /**
     * Check Auto populated State Name
     */
    public String checkState() {
        return stateName.getAttribute("value");
    }
}
