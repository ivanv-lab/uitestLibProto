import com.codeborne.selenide.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.impl.Html.text;

public class LoginPageTests {

    SelenideDriver driver;
    SelenideElement loginInput=$x(".//input[@id='login']");
    SelenideElement passwordInput=$x(".//input[@id='password']");
    SelenideElement submitButton=$x(".//button[@id='button_auth']");
    SelenideElement alert=$x(".//div[contains(@class,'alert')]/span");

    @BeforeEach
    void goToAddress(){
        SelenideConfig config=new SelenideConfig();
        config.browser("chrome");
        config.baseUrl("http://192.168.128.191/acui/login");
        config.headless(false);
        driver=new SelenideDriver(config);
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

    }

    @Test
    @Description("Проверка успешной авторизации")
    void successLogin(){

    }
}
