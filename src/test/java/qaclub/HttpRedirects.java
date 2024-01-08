package qaclub;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestBase.class)
public class HttpRedirects extends TestBase {

    @Test
    public void testRedirects() {
        driver.get("https://www.google.com");
        implicitWait();
        System.out.println(driver.getCurrentUrl());

    }

}
