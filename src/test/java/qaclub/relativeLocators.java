package qaclub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class relativeLocators {
    private WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterAll
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testRelativeLocators() {
        driver.get("https://qaclub.online/products");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        String id = driver.findElement(RelativeLocator.with(By.tagName("div"))
            .toLeftOf(By.id("p6")).below(By.id("p2")))
            .getAttribute("id");
        System.out.println("ID= " + id);

        assertEquals(id, "p5");
    }
}
