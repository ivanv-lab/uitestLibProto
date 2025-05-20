package tests.ui.adm;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import testlib.base.adm.login.LoginPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование страницы логина ADM")
@Tag("login-tests-class")
public class LoginPageTests extends LoginPage {

    LoginPage loginPage=new LoginPage();

    @Test
    @Tag("login-tests")
    @Description("Только логин")
    void loginOnly(){

        setLogin("admin@admin.com");
        clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Только пароль")
    void passwordOnly(){

        setPassword("Admin");
        clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Неверный логин")
    void invalidLogin(){

        setLogin("qweqwe");
        setPassword("Admin");
        clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Неверный пароль")
    void invalidPassword(){

        setLogin("admin@admin.com");
        setPassword("qweqwe");
        clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest(){

        loginPage.changeLanguage();
        clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",getAlertText());

        loginPage.changeLanguage();
        clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Description("Проверка успешной авторизации")
    void successLogin() throws InterruptedException {

        setLogin("admin@admin.com");
        setPassword("Admin");
        clickSubmitButton();
        waitForElementVisible(By.xpath(".//span[@class='top-user-link']"),5);
        assertEquals(getTopUserText(),"admin@admin.com");
    }
}
