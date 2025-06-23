package tests.ui.adm.settings;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.UIHandler;
import testlib.base.adm.AdminBaseTest;
import testlib.utils.CsvLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@DisplayName("Тестирование страницы Настройки->Клиенты")
@Tag("client")
@Execution(ExecutionMode.CONCURRENT)
public class ClientTests extends AdminBaseTest {

    static List<Map<String, String>> clientDataProvider() {
        return CsvLoader.csvRows("testdata/Clients.csv");
    }

    @ParameterizedTest
    @MethodSource("clientDataProvider")
    @Tag("client")
    @Description("Создание клиента")
    void clientPageAddClientTest(Map<String, String> clientData) {

        String name = clientData.get("Клиент"),
                transports = clientData.get("Транспорт");
        Boolean status = Boolean.valueOf(clientData.get("Статус")),
                avance = Boolean.valueOf(clientData.get("Авансовая схема взаиморасчетов")),
                multisignature = Boolean.valueOf(clientData.get("Использовать мультиподпись?")),
                templateOnly = Boolean.valueOf(clientData.get("Только шаблон")),
                moderation = Boolean.valueOf(clientData.get("Модерация"));

        ui
                .subSectionClick("Настройки", "Клиенты")
                .deleteFromTableIfExists(name)
                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Название", name)
                .switchCheckbox("Статус", status)
                .switchCheckbox("Авансовая схема взаиморасчетов", avance);

        Arrays.stream(transports.split(",")).forEach(s -> {
            ui.switchCheckbox(s, true)
                    .switchCheckboxInRow(s, "Мультиподпись", multisignature)
                    .switchCheckboxInRow(s, "Только шаблон", templateOnly)
                    .switchCheckboxInRow(s, "Модерация", moderation);
        });

        ui
                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists(name);
    }
}
