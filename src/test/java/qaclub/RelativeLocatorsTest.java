package qaclub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class RelativeLocatorsTest {
    private WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://qaclub.online/products");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public void tearDown() {
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "/screenshots/currentWindow.png");

        try {
            FileHandler.copy(src, dest);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        driver.quit();
    }

    @Test
    public void testLeftBelowRelativeLocators() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("p6")));

        String id = driver.findElement(RelativeLocator.with(By.tagName("div"))
                .toLeftOf(By.id("p6")).below(By.id("p2")))
                .getAttribute("id");
        System.out.println("ID= " + id);

        assertEquals(id, "p5");
    }

    @Test
    public void testRightAboveRelativeLocators() {

        String id2 = driver.findElement(RelativeLocator.with(By.tagName("div"))
                        .toRightOf(By.id("p2")).above(By.id("p6")))
                .getAttribute("id");
        System.out.println("ID2= " + id2);

        assertEquals(id2, "p3");
    }

    @Test
    public void testNearPixelsRelativeLocators() {

        String id3 = driver.findElement(RelativeLocator.with(By.tagName("div"))
                        .near(By.id("p9"), 100))
                .getAttribute("id");
        System.out.println("ID3= " + id3);

        assertEquals(id3, "p6");
    }
}
