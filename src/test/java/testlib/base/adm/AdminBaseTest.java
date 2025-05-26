package testlib.base.adm;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class AdminBaseTest extends BaseTest {

    @BeforeEach
    void conditionalLogin(TestInfo testInfo) throws InterruptedException {
        if(!testInfo.getTags().contains("no-login")){

            LoginPage loginPage=new LoginPage();
            loginPage.login(PropertyHandler.getProperty("admin.login"),
                    PropertyHandler.getProperty("admin.password"));

            Navbar navbar=new Navbar();
            navbar.openSidebar();
        } else{
            Selenide.open("http://" + PropertyHandler.getProperty("base.URL") + "/acui/login");
        }
    }
}
