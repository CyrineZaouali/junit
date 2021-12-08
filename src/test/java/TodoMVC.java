import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TodoMVC {
    private static WebDriver driver;
    private static JavascriptExecutor javaScriptExecutor;
    private static WebDriverWait driverWait;

    private static final String url = "https://www.todomvc.com";

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        javaScriptExecutor = (JavascriptExecutor) driver;
        driverWait = new WebDriverWait(driver, 3);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @BeforeEach
    public void getUrl() {
        driver.get(url);
    }

    @Test
    public void test() throws InterruptedException {

        WebElement backboneJSLink = driver.findElement(By.linkText("Backbone.js"));
        driverWait.until(ExpectedConditions.visibilityOf(backboneJSLink));
        backboneJSLink.click();

        WebElement todoInput = driver.findElement(By.className("new-todo"));
        driverWait.until(ExpectedConditions.visibilityOf(todoInput));
        todoInput.sendKeys("Meet a Friend");
        todoInput.sendKeys(Keys.ENTER);

        todoInput.sendKeys("Buy Meat");
        todoInput.sendKeys(Keys.ENTER);

        todoInput.sendKeys("Clean the car");
        todoInput.sendKeys(Keys.ENTER);

        WebElement todoCount = driver.findElement(By.cssSelector(".todo-count > strong"));
        driverWait.until(ExpectedConditions.textToBePresentInElement(todoCount, "3"));

        List<WebElement> toggleCheckbox = driver.findElements(By.className("toggle"));
        toggleCheckbox.get(0).click();
        toggleCheckbox.get(2).click();

        todoCount = driver.findElement(By.cssSelector(".todo-count > strong"));
        driverWait.until(ExpectedConditions.textToBePresentInElement(todoCount, "1"));

    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

}
