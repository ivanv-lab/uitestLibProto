package tests.ui.adm;

import com.codeborne.selenide.Configuration;
import jdk.jfr.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import testlib.base.BasePage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPageTests extends BasePage {

    By loginInput=By.xpath(".//input[@id='login']");
    By passwordInput=By.xpath(".//input[@id='password']");
    By submitButton=By.xpath(".//button[@id='button_auth']");
    By changeLanguageButton=By.xpath(".//div[label[@for='language']]//input");

    @BeforeAll
    static void setup(){
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://192.168.128.191";
        Configuration.headless = false;
    }

    @BeforeEach
    void goToAddress(){
        open(Configuration.baseUrl+"/acui/login");
    }

    @AfterEach
    void afterTest(){
        deleteAllCookies();
        deleteBrowserStorage();
    }

    @Test
    @Description("Только логин")
    void loginOnly(){

        sendKeys(loginInput,"admin@admin.com");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Description("Только пароль")
    void passwordOnly(){

        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Description("Неверный логин")
    void invalidLogin(){

        sendKeys(loginInput,"qweqwe");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Description("Неверный пароль")
    void invalidPassword(){

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"qweqwe");
        click(submitButton);
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
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
    @Description("Проверка успешной авторизации")
    void successLogin() throws InterruptedException {

        sendKeys(loginInput,"admin@admin.com");
        sendKeys(passwordInput,"Admin");
        click(submitButton);
        Thread.sleep(500);
        assertEquals("http://192.168.128.191/acui/dashboard",getCurrentUrl());
    }
}
