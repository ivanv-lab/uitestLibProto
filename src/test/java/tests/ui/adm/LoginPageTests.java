package tests.ui.adm;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import testlib.base.adm.AdminBaseTest;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тестирование страницы логина ADM")
@Tag("login-tests-class")
@Execution(ExecutionMode.CONCURRENT)
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
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly8(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly8(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin8(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword8(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest8(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin8() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly7(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly7(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin7(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword7(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest7(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin7() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly6(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly6(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin6(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword6(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest6(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin6() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly5(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly5(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin5(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword5(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest5(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin5() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly4(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly4(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin4(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword4(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest4(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin4() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly3(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly3(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin3(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword3(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest3(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin3() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly2(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly2(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin2(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword2(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest2(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin2() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly1(){

        loginPage.setLogin("admin@admin.com");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.", loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly1(){

        loginPage.setPassword("Admin");
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin1(){

        loginPage.login("qweqwe","Admin");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword1(){

        loginPage.login("admin@admin.com","qweqwe");
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка смены языка на Английский и Русский")
    void changeLanguageTest1(){

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("It is impossible to log in.\n" +
                "Incorrect login / password.",loginPage.getAlertText());

        loginPage.changeLanguage();
        loginPage.clickSubmitButton();
        assertEquals("Невозможно авторизоваться.\n" +
                "Неправильный логин/пароль.",loginPage.getAlertText());
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin1() {

        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        assertEquals(loginPage.returnTopUserText(),"admin@admin.com");
    }
}
