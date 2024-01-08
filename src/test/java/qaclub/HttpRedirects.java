package qaclub;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestBase.class)
public class HttpRedirects extends TestBase {

    @Test
    public void testRedirects() {
        String testURL = "https://qaclub.online/products";
        driver.get(testURL);
        implicitWait();
        assertEquals(testURL, driver.getCurrentUrl());
        System.out.println(driver.getCurrentUrl());
    }

}
