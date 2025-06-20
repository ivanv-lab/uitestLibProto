package testlib.base.pb;

import org.junit.jupiter.api.BeforeEach;
import testlib.base.BaseTest;
import testlib.utils.handlers.PropertyHandler;

public class PBBaseTest extends BaseTest {

    @BeforeEach
    void setup(){

        ui.login(PropertyHandler.getProperty("base.URL") + "/pbui/login",
                PropertyHandler.getProperty("admin.login"),
                PropertyHandler.getProperty("admin.password"))
                .openSidebar();
    }
}
