import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import testlib.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTests extends BasePage {

    By loginInput=By.xpath(".//input[@id='login']");
    By passwordInput=By.xpath(".//input[@id='password']");
    By submitButton=By.xpath(".//button[@id='button_auth']");
    By changeLanguageButton=By.xpath(".//div[label[@for='language']]//input");

    @BeforeAll
    static void setup(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://192.168.128.191/acui/login";
        Configuration.headless = false;
    }

    @BeforeEach
    void goToAddress(){
        open(Configuration.baseUrl);
    }

    @Test
    @Description("Только логин")
    void loginOnly(){
//        loginInput.sendKeys("admin@admin.com");
//        submitButton.click();
//        alert.shouldHave(text("Невозможно авторизоваться.\n" +
//                "Неправильный логин/пароль."));

        sendKeys(loginInput,"admin@admin.com");
        click(submitButton);
        assertEquals(getAlertText(),"Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.");
    }

    @Test
    @Description("Только пароль")
    void passwordOnly(){
//        passwordInput.sendKeys("Admin");
//        submitButton.click();
//        alert.shouldHave(text("Невозможно авторизоваться.\n" +
//                "Неправильный логин/пароль."));

        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals(getAlertText(),"Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.");
    }

    @Test
    @Description("Неверный логин")
    void invalidLogin(){
//        loginInput.sendKeys("qweqwe");
//        passwordInput.sendKeys("Admin");
//        submitButton.click();
//        alert.shouldHave(text("Невозможно авторизоваться.\n" +
//                "Неправильный логин/пароль."));

        sendKeys(loginInput,"qweqwe");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals(getAlertText(),"Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.");
    }

    @Test
    @Description("Неверный пароль")
    void invalidPassword(){
//        loginInput.sendKeys("admin@admin.com");
//        passwordInput.sendKeys("qweqwe");
//        submitButton.click();
//        alert.shouldHave(text("Невозможно авторизоваться.\n" +
//                "Неправильный логин/пароль."));

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"qweqwe");
        click(submitButton);
        assertEquals(getAlertText(),"Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.");
    }

    @Test
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguage(){
//        changeLanguageButton.click();
//        $x(".//button[div/span[contains(.,'English')]]").click();
//        submitButton.click();
//        alert.shouldHave(text("It is impossible to log in.\n" +
//                "Incorrect login / password."));
//
//        changeLanguageButton.click();
//        $x(".//button[div/span[contains(.,'Русский')]]").click();
//        submitButton.click();
//        alert.shouldHave(text("Невозможно авторизоваться.\n" +
//                "Неправильный логин/пароль."));

        click(changeLanguageButton);
        click(By.xpath(".//button[div/span[contains(.,'English')]]"));
        click(submitButton);
        assertEquals(getAlertText(),"It is impossible to log in.\n" +
                "Incorrect login / password.");

        click(changeLanguageButton);
        click(By.xpath(".//button[div/span[contains(.,'Русский')]]"));
        click(submitButton);
        assertEquals(getAlertText(),"Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.");
    }

    @Test
    @Description("Проверка успешной авторизации")
    void successLogin(){
//        loginInput.sendKeys("admin@admin.com");
//        passwordInput.sendKeys("Admin");
//        submitButton.click();
//        $x(".//span[@class='top-user-link']").shouldHave(text("admin@admin.com"));

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals(getCurrentUrl(),"http://192.168.128.195/acui/dashboard");
    }
}
