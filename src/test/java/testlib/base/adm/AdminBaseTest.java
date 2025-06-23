package testlib.base.adm;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import testlib.base.BaseTest;
import testlib.utils.AuthKeeper;
import testlib.utils.handlers.PropertyHandler;

public class AdminBaseTest extends BaseTest {

    @BeforeEach
    void conditionalLogin(TestInfo testInfo){

        if(!testInfo.getTags().contains("no-login")){

//            ui.loginAcui(PropertyHandler.getProperty("admin.login"),
//                    PropertyHandler.getProperty("admin.password"))
//
//            .openSidebar();

            AuthKeeper.touchSession(PropertyHandler.getProperty("base.URL") + "/acui/login",
                    PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        } else{
            Selenide.open( PropertyHandler.getProperty("base.URL") + "/acui/login");
        }
    }
}
