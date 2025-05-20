package tests.ui.adm;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import testlib.base.AdminBaseTest;
import testlib.base.BaseTest;
import testlib.base.adm.BasePage;
import testlib.pages.login.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование страницы логина ADM")
@Tag("login-tests-class")
public class LoginPageTests extends AdminBaseTest {

    private LoginPage loginPage=new LoginPage();

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest(){

        loginPage.changeLanguage();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin() throws InterruptedException {

        loginPage.login("admin@admin.com","Admin");
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }
}
