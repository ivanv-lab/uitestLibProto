package testlib.base.adm;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import testlib.utils.handlers.PropertyHandler;

import static com.codeborne.selenide.Selenide.open;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    @BeforeAll
    void setupSelenide(){

        String selenoidUrl = System.getProperty("selenoid.url");
        if (selenoidUrl != null) {
            Configuration.remote = selenoidUrl;
            Configuration.browser = "chrome"; // Или другой браузер
            Configuration.headless = false;  // Или false
        } else {
            System.err.println("Selenoid URL is not set!");
        }

//        String selenoidHost = System.getenv("SELENOID_HOST");
//        String selenoidPort = System.getenv("SELENOID_PORT");
//
//        if (selenoidHost != null && selenoidPort != null) {
//            Configuration.remote = "http://" + selenoidHost + ":" + selenoidPort + "/wd/hub";
//            Configuration.browser = PropertyHandler.getProperty("browser"); // Или другой браузер
//            Configuration.headless = Boolean.parseBoolean(PropertyHandler.getProperty("headless")); // Или false
//            // Другие настройки Selenide
//            System.out.println("Selenoid URL: " + Configuration.remote);
//        } else {
//            System.out.println("Selenoid не настроен. Запуск локально.");
//        }
//        Configuration.browser= PropertyHandler.getProperty("browser");
//        Configuration.baseUrl=PropertyHandler.getProperty("base.URL");
//        Configuration.headless=Boolean.parseBoolean(PropertyHandler.getProperty("headless"));
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
