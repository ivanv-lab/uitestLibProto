package testlib.base.adm;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import testlib.base.BaseTest;
import testlib.base.NavbarWorker;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class AdminBaseTest extends BaseTest {

    @BeforeEach
    void conditionalLogin(TestInfo testInfo) throws InterruptedException {

        LoginPage loginPage=new LoginPage();

        if(!testInfo.getTags().contains("no-login")){

            loginPage.login(PropertyHandler.getProperty("admin.login"),
                    PropertyHandler.getProperty("admin.password"));

            NavbarWorker navbar=new NavbarWorker();
            navbar.openSidebar();
        } else{

            Selenide.open( PropertyHandler.getProperty("base.URL") + "/acui/login");
            loginPage.changeLanguage();
        }
    }
}
