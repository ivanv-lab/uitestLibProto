package tests.ui.adm;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import testlib.base.adm.AdminBaseTest;

@DisplayName("Тестирование страницы логина ADM")
@Tag("login-tests-class")
@Execution(ExecutionMode.CONCURRENT)
public class LoginPageTests extends AdminBaseTest {

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только логин")
    void loginOnly() {
        ui
                .loginAcui("admin@admin.com", " ")
                .alertTextEquals("Невозможно авторизоваться.\n" +
                        "Неправильный логин/пароль.");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Только пароль")
    void passwordOnly() {
        ui
                .loginAcui(" ", "Admin")
                .alertTextEquals("Невозможно авторизоваться.\n" +
                        "Неправильный логин/пароль.");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный логин")
    void invalidLogin() {
        ui
                .loginAcui("qweqwe", "Admin")
                .alertTextEquals("Невозможно авторизоваться.\n" +
                        "Неправильный логин/пароль.");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Неверный пароль")
    void invalidPassword() {
        ui
                .loginAcui("admin@admin.com","qweqwe")
                .alertTextEquals("Невозможно авторизоваться.\n" +
                        "Неправильный логин/пароль.");
    }

    @Test
    @Tag("login-tests")
    @Tag("no-login")
    @Description("Проверка успешной авторизации")
    void successLogin() {
        ui
                .loginAcui()
                .topUserTextEquals("admin@admin.com");
    }
}
