package testlib.base;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$x;

public class AdminBaseTest extends BaseTest {

    @BeforeEach
    void conditionalLogin(TestInfo testInfo){
        if(!testInfo.getTags().contains("no-login")){

            Selenide.open("http://"+baseUrl + "/acui/login");
            $x(".//input[@id='login']").sendKeys("admin@admin.com");
            $x(".//input[@id='password']").sendKeys("Admin");
            $x(".//button[@id='button_auth']").click();
        } else{
            Selenide.open("http://"+baseUrl + "/acui/login");
        }
    }
}
