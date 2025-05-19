package tests.ui.adm;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.By;
import testlib.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тестирование страницы логина ADM")
@Tag("login-tests-class")
public class LoginPageTests extends BasePage {

    By loginInput=By.xpath(".//input[@id='login']");
    By passwordInput=By.xpath(".//input[@id='password']");
    By submitButton=By.xpath(".//button[@id='button_auth']");
    By changeLanguageButton=By.xpath(".//div[label[@for='language']]//input");
    By topUserText=By.xpath(".//span[contains(@class,'top-user')]");

    @Test
    @Tag("login-tests")
    @Description("Только логин")
    void loginOnly(){

        sendKeys(loginInput,"admin@admin.com");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Только пароль")
    void passwordOnly(){

        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Неверный логин")
    void invalidLogin(){

        sendKeys(loginInput,"qweqwe");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Неверный пароль")
    void invalidPassword(){

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"qweqwe");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguage(){

        click(changeLanguageButton);
        click(By.xpath(".//button[div/span[contains(.,'English')]]"));
        click(submitButton);
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",getAlertText());

        click(changeLanguageButton);
        click(By.xpath(".//button[div/span[contains(.,'Русский')]]"));
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Проверка успешной авторизации")
    void successLogin() throws InterruptedException {

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        waitForElementVisible(topUserText,5);
        assertEquals(getText(topUserText),"admin@admin.com");
    }
}
