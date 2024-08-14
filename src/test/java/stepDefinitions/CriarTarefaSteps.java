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
import pages.CriarTarefaPage;
import pages.LoginPage;
import io.github.cdimascio.dotenv.Dotenv;
import static org.junit.Assert.*;


public class CriarTarefaSteps {
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private CriarTarefaPage criarTarefaPage;

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
        criarTarefaPage = new CriarTarefaPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Dado("que estou logado")
    public void queEstouLogado() {
        driver.get("https://mantis-prova.base2.com.br/login_page.php");
        loginPage.doLogin(username, password);

        wait.until(ExpectedConditions.urlToBe("https://mantis-prova.base2.com.br/my_view_page.php"));
    }

    @E("estou na pagina de criacao de tarefas")
    public void estouNaPaginaDeCriacaoDeTarefas() {
        driver.get("https://mantis-prova.base2.com.br/bug_report_page.php");
    }

    @Quando("preencho o formulario corretamente")
    public void preenchoOFormularioCorretamente() {
        criarTarefaPage.selectCategory("1");
        criarTarefaPage.selectReproducibility("10");
        criarTarefaPage.selectSeverity("30");
        criarTarefaPage.selectPriority("40");
        criarTarefaPage.enterSummary("Erro ao acessar página de contato");
        criarTarefaPage.enterDescription("O texto está incorreto no footer da página de contatos. A palavra 'possível' está sem acento.");
        criarTarefaPage.enterStepsToReproduce("1. Abrir o site. 2. Clicar no botão de Contatos. 3. Scrollar a página até o footer");
        criarTarefaPage.enterAdditionalInfo("Ocorre em ambiente de homologação.");
    }

    @E("clico em 'Criar Nova Tarefa'")
    public void clicoEmCriarNovaTarefa() {
        criarTarefaPage.clickCriarNovaTarefa();
    }

    @Entao("devo ser redirecionado para a pagina de visualizacao da tarefa")
    public void devoSerRedirecionadoParaAPaginaDeVisualizacaoDaTarefa() {
        String expectedUrl = "https://mantis-prova.base2.com.br/view.php?id=";
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        assertTrue("A URL da tarefa não corresponde ao formato esperado.", currentUrl.startsWith(expectedUrl));
    }

    @Quando("preencho o formulario mas deixo de preencher 'categoria'")
    public void preenchoOFormularioMasDeixoDePreencherCategoria() {
        criarTarefaPage.selectReproducibility("10");
        criarTarefaPage.selectSeverity("30");
        criarTarefaPage.selectPriority("40");
        criarTarefaPage.enterSummary("Erro ao acessar página de contato");
        criarTarefaPage.enterDescription("O texto está incorreto no footer da página de contatos. A palavra 'possível' está sem acento.");
        criarTarefaPage.enterStepsToReproduce("1. Abrir o site. 2. Clicar no botão de Contatos. 3. Scrollar a página até o footer");
        criarTarefaPage.enterAdditionalInfo("Ocorre em ambiente de homologação.");
    }

    @Entao("devo visualizar uma mensagem de erro informando a obrigatoriedade do campo 'categoria'")
    public void devoVisualizarUmaMensagemDeErroInformandoAObrigatoriedadeDoCampoCategoria() {
        String expectedUrl = "https://mantis-prova.base2.com.br/bug_report.php?posted=1";
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
        String currentUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, currentUrl);

        WebElement errorInfoElement = wait.until(ExpectedConditions.visibilityOf(criarTarefaPage.getAlertMessageElement()));
        String errorInfoText = errorInfoElement.getText();
        String expectedText = "Um campo necessário 'category' estava vazio. Por favor, verifique novamente suas entradas.";
        assertTrue("A mensagem de erro não contém o texto esperado.", errorInfoText.contains(expectedText));
    }
}
