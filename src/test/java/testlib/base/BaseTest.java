package testlib.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import testlib.utils.TestStatus;
import testlib.utils.handlers.AllureTestHandler;
import testlib.utils.handlers.PropertyHandler;
import testlib.utils.handlers.SQLHandler;
import testlib.utils.handlers.jmx.JmxHandler;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(AllureTestHandler.class)
public abstract class BaseTest{

    protected final Logger log= LoggerFactory.getLogger(getClass());

    protected final UIOperationsInterface ui=new UIHandler();
    protected JmxHandler jmxHandler=new JmxHandler();

    public static final SQLHandler msg=new SQLHandler("msg");
    public static final SQLHandler stat=new SQLHandler("stat");
    public static final SQLHandler cdp=new SQLHandler("cdp");

    @BeforeAll
    void setupSelenide() throws IOException {

        String selenoidHost = PropertyHandler.getProperty("selenoid.host");
        String selenoidPort = PropertyHandler.getProperty("selenoid.port");

        if (selenoidHost != null && selenoidPort != null) {
            Configuration.remote = "http://" + selenoidHost + ":" + selenoidPort + "/wd/hub";
            Configuration.browser = PropertyHandler.getProperty("browser");
            Configuration.headless = Boolean.parseBoolean(PropertyHandler.getProperty("headless"));
            Configuration.screenshots=true;
            System.out.println("Selenoid URL: " + Configuration.remote);
        } else {
            System.out.println("Selenoid не настроен. Запуск локально.");
            Configuration.browser= PropertyHandler.getProperty("browser");
            Configuration.baseUrl=PropertyHandler.getProperty("base.URL");
            Configuration.headless=Boolean.parseBoolean(PropertyHandler.getProperty("headless"));
        }

        jmxHandler.connect();
    }

    @BeforeEach
    void logTestStart(TestInfo testInfo){
        log.info("Начало тестирования: {}",testInfo.getDisplayName());
    }

    @AfterEach
    void afterTest(TestInfo testInfo){
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }

    @AfterAll
    void endTests(){
        Selenide.closeWebDriver();
    }
}
