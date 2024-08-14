package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "username")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@value='Entrar']")
    private WebElement entrarButton;

    @FindBy(xpath = "//span[contains(@class, 'user-info')]")
    private WebElement userInfoElement;

    @FindBy(xpath = "//div[contains(@class, 'alert alert-danger')]//p")
    private WebElement alertMessageElement;

    public void enterUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void clickEntrar() {
        entrarButton.click();
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public WebElement getUserInfoElement() {
        return userInfoElement;
    }

    public void doLogin(String username, String password) {
        this.enterUsername(username);
        this.clickEntrar();
        this.enterPassword(password);
        this.clickEntrar();
    }

    public WebElement getAlertMessageElement() {
        return alertMessageElement;
    }
}
