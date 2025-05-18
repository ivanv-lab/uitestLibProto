import com.codeborne.selenide.*;
import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageTests {

    SelenideElement loginInput=$x(".//input[@id='login']");
    SelenideElement passwordInput=$x(".//input[@id='password']");
    SelenideElement submitButton=$x(".//button[@id='button_auth']");
    SelenideElement alert=$x(".//div[contains(@class,'alert')]/span");
    SelenideElement changeLanguageButton=$x(".//div[label[@for='language']]//input");

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
        loginInput.sendKeys("admin@admin.com");
        submitButton.click();
        alert.shouldHave(text("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль."));
    }

    @Test
    @Description("Только пароль")
    void passwordOnly(){
        passwordInput.sendKeys("Admin");
        submitButton.click();
        alert.shouldHave(text("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль."));
    }

    @Test
    @Description("Неверный логин")
    void invalidLogin(){
        loginInput.sendKeys("qweqwe");
        passwordInput.sendKeys("Admin");
        submitButton.click();
        alert.shouldHave(text("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль."));
    }

    @Test
    @Description("Неверный пароль")
    void invalidPassword(){
        loginInput.sendKeys("admin@admin.com");
        passwordInput.sendKeys("qweqwe");
        submitButton.click();
        alert.shouldHave(text("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль."));
    }

    @Test
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguage(){
        changeLanguageButton.click();
        $x(".//button[div/span[contains(.,'English')]]").click();
        submitButton.click();
        alert.shouldHave(text("It is impossible to log in.\n" +
                "Incorrect login / password."));

        changeLanguageButton.click();
        $x(".//button[div/span[contains(.,'Русский')]]").click();
        submitButton.click();
        alert.shouldHave(text("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль."));
    }

    @Test
    @Description("Проверка успешной авторизации")
    void successLogin(){
        loginInput.sendKeys("admin@admin.com");
        passwordInput.sendKeys("Admin");
        submitButton.click();
        $x(".//span[@class='top-user-link']").shouldHave(text("admin@admin.com"));
    }
}
