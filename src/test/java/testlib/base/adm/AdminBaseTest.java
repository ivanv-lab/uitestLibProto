package testlib.base.adm;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import testlib.base.BaseTest;
import testlib.base.NavbarWorker;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class AdminBaseTest extends BaseTest {

    private LoginPage loginPage=new LoginPage();
    private NavbarWorker navbarWorker=new NavbarWorker();

    @BeforeEach
    void conditionalLogin(TestInfo testInfo) throws InterruptedException {

        if(!testInfo.getTags().contains("no-login")){

            loginPage.login(PropertyHandler.getProperty("admin.login"),
                    PropertyHandler.getProperty("admin.password"));

            navbarWorker.openSidebar();
        } else{

            Selenide.open( PropertyHandler.getProperty("base.URL") + "/acui/login");
        }
    }
}
