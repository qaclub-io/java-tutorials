package qaclub;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class TestBase implements AfterTestExecutionCallback {
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = new ChromeDriver();
        // driver.manage().window().maximize();
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("In after test execution!");
        if (context.getExecutionException().isPresent()) {
            captureScreenshot();
            System.out.println("Failure!");
        } else {
            System.out.println("Success!");
        }
    }

    private void captureScreenshot() {
        if (driver instanceof TakesScreenshot) {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(System.getProperty("user.dir") +
                    "/screenshots/currentWindow.png");
            try {
                FileHandler.copy(src, dest);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            System.out.println("Screenshot saved at: " + dest.getAbsolutePath());
        } else {
            System.out.println("No Screenshot Captured!");
        }
    }

    protected void implicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // @Override
    // public void beforeAll(ExtensionContext context) throws Exception {
    // // TODO Auto-generated method stub
    // throw new UnsupportedOperationException("Unimplemented method 'beforeAll'");
    // }
}
