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

    static Stream<Arguments> clientDataProvider(){
        return Stream.of(
                Arguments.of("Wings","SMS,Call,Email,WhatsApp,Viber,Push,Mail Notify,Custom",true,true,true,true,true)
        );
    }

    @ParameterizedTest
    @MethodSource("clientDataProvider")
    @Tag("client")
    @Description("Создание клиента")
    void clientPageAddClientTest(String name,String transports,Boolean status,Boolean avance,
                                 Boolean multisignature,Boolean templateOnly,Boolean moderation){

        ui
                .subSectionClick("Настройки","Клиенты")
                .deleteFromTableIfExists(name)
                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Название",name)
                .switchCheckbox("Статус",status)
                .switchCheckbox("Авансовая схема взаиморасчетов",avance);

        Arrays.stream(transports.split(",")).forEach(s->{
            ui.switchCheckbox(s,true)
            .switchCheckboxInRow(s,"Мультиподпись",multisignature)
                    .switchCheckboxInRow(s,"Только шаблон",templateOnly)
                    .switchCheckboxInRow(s,"Модерация",moderation);
        });
    }
}
