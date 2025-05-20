package testlib.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import testlib.utils.handlers.PropertyHandler;

import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @BeforeAll
    void setupSelenide(){
        Configuration.browser=PropertyHandler.getProperty("browser");
        Configuration.baseUrl=PropertyHandler.getProperty("base.URL");
        Configuration.headless=Boolean.parseBoolean(PropertyHandler.getProperty("headless"));
    }

    @BeforeEach
    void goToAddress(){
        open(Configuration.baseUrl+"/acui/login");
    }

    @AfterEach
    void afterTest(){
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    void endTests(){
        Selenide.closeWebDriver();
    }
}
