package testlib.base.pb;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import testlib.base.BaseTest;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class PBBaseTest extends BaseTest {

    @BeforeEach
    void setup(){

        LoginPage loginPage=new LoginPage();

        Selenide.open( PropertyHandler.getProperty("base.URL") + "/pbui/login");
        loginPage.login(PropertyHandler.getProperty("admin.login"),
                PropertyHandler.getProperty("admin.password"));
    }
}
