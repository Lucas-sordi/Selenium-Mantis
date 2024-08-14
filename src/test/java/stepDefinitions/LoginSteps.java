package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import pages.LoginPage;
import io.github.cdimascio.dotenv.Dotenv;
import static org.junit.Assert.*;

public class LoginSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;

    private Dotenv dotenv = Dotenv.load();
    private String username = dotenv.get("LOGIN_USERNAME");
    private String password = dotenv.get("LOGIN_PASSWORD");

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Dado("que estou na pagina de login")
    public void queEstouNaPaginaDeLogin() {
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
    }

    @Quando("insiro um nome de usuario valido")
    public void insiroUmNomeDeUsuarioValido() {
        loginPage.enterUsername(username);
    }

    @E("clico em 'Entrar'")
    public void clicoEmEntrar() {
        loginPage.clickEntrar();
    }

    @Quando("insiro uma senha valida")
    public void insiroUmaSenhaValida() {
        loginPage.enterPassword(password);
    }

    @Entao("devo ser redirecionado para a pagina principal")
    public void devoSerRedirecionadoParaAPaginaPrincipal() {
        String expectedUrl = "https://mantis-prova.base2.com.br/my_view_page.php";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);

        WebElement userInfoElement = wait.until(ExpectedConditions.visibilityOf(loginPage.getUserInfoElement()));
        String userInfoText = userInfoElement.getText();
        assertEquals(username, userInfoText);
    }

    @E("insiro uma senha nao correspondente ao nome de usuario")
    public void insiroUmaSenhaNaoCorrespondenteAoNomeDeUsuario() {
        loginPage.enterPassword("invalidPassword_21093809321");
    }

    @Entao("devo visualizar um alerta informando que nao foi possivel realizar login")
    public void devoVisualizarUmAlertaInformandoQueNaoFoiPossivelRealizarLogin() {
        WebElement alertElement = wait.until(ExpectedConditions.visibilityOf(loginPage.getAlertMessageElement()));
        String alertText = alertElement.getText();
        String expectedText = "Sua conta pode estar desativada ou bloqueada ou o nome de usuário e a senha que você digitou não estão corretos.";
        assertEquals(expectedText, alertText);
    }
}