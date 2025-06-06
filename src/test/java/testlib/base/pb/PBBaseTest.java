package testlib.base.pb;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import testlib.base.BaseTest;
import testlib.base.NavbarWorker;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class PBBaseTest extends BaseTest {

    private LoginPage loginPage=new LoginPage();
    private NavbarWorker navbarWorker=new NavbarWorker();

    @BeforeEach
    void setup(){

        Selenide.open( PropertyHandler.getProperty("base.URL") + "/pbui/login");
        loginPage.login(PropertyHandler.getProperty("admin.login"),
                PropertyHandler.getProperty("admin.password"));
        navbarWorker.openSidebar();
    }
}
