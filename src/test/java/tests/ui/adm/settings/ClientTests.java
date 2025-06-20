package tests.ui.adm.settings;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import testlib.base.adm.AdminBaseTest;

@DisplayName("Тестирование страницы Настройки->Клиенты")
@Tag("client")
@Execution(ExecutionMode.CONCURRENT)
public class ClientTests extends AdminBaseTest {

    @Test
    @Tag("client")
    @Description("Создание клиента")
    void clientPageAddClientTest(){


    }
}
