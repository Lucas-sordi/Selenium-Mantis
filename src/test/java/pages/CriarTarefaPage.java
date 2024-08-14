package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CriarTarefaPage {
    private WebDriver driver;

    public CriarTarefaPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "category_id")
    private WebElement categorySelect;

    @FindBy(id = "reproducibility")
    private WebElement reproducibilitySelect;

    @FindBy(id = "severity")
    private WebElement severitySelect;

    @FindBy(id = "priority")
    private WebElement prioritySelect;

    @FindBy(id = "summary")
    private WebElement summaryInput;

    @FindBy(id = "description")
    private WebElement descriptionTextarea;

    @FindBy(id = "steps_to_reproduce")
    private WebElement stepsToReproduceTextarea;

    @FindBy(id = "additional_info")
    private WebElement additionalInfoTextarea;

    @FindBy(xpath = "//input[@value='Criar Nova Tarefa']")
    private WebElement criarNovaTarefaButton;

    @FindBy(xpath = "//div[contains(@class, 'alert alert-danger')]")
    private WebElement alertMessageElement;

    public void selectCategory(String value) {
        Select select = new Select(categorySelect);
        select.selectByValue(value);
    }

    public void selectReproducibility(String value) {
        Select select = new Select(reproducibilitySelect);
        select.selectByValue(value);
    }

    public void selectSeverity(String value) {
        Select select = new Select(severitySelect);
        select.selectByValue(value);
    }

    public void selectPriority(String value) {
        Select select = new Select(prioritySelect);
        select.selectByValue(value);
    }

    public void enterSummary(String summary) {
        summaryInput.sendKeys(summary);
    }

    public void enterDescription(String description) {
        descriptionTextarea.sendKeys(description);
    }

    public void enterStepsToReproduce(String stepsToReproduce) {
        stepsToReproduceTextarea.sendKeys(stepsToReproduce);
    }

    public void enterAdditionalInfo(String additionalInfo) {
        additionalInfoTextarea.sendKeys(additionalInfo);
    }

    public void clickCriarNovaTarefa() {
        criarNovaTarefaButton.click();
    }

    public WebElement getAlertMessageElement() {
        return alertMessageElement;
    }
}
