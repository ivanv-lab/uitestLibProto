package testlib.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import testlib.utils.handlers.PropertyHandler;
import testlib.utils.handlers.SQLHandler;
import testlib.utils.handlers.jmx.JmxHandler;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    protected final UIHandler ui=new UIHandler();
    protected JmxHandler jmxHandler=new JmxHandler();

    public static final SQLHandler msg=new SQLHandler("msg");
    public static final SQLHandler stat=new SQLHandler("stat");
    public static final SQLHandler cdp=new SQLHandler("cdp");

    @BeforeAll
    void setupSelenide(){

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
