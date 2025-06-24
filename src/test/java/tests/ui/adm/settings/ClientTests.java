package tests.ui.adm.settings;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.UIHandler;
import testlib.base.adm.AdminBaseTest;
import testlib.utils.CsvLoader;
import testlib.utils.handlers.PropertyHandler;

import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static testlib.utils.handlers.ApiHandler.getToken;

@DisplayName("Тестирование страницы Настройки->Клиенты")
@Tag("client")
@Execution(ExecutionMode.CONCURRENT)
public class ClientTests extends AdminBaseTest {

    static List<Map<String, String>> clientDataProvider() {
        return CsvLoader.csvRows("testdata/Clients.csv");
    }

    @Feature("Init-0")
    @Tag("Init-0")
    @ParameterizedTest
    @MethodSource("clientDataProvider")
    @Description("Клиенты. Создание клиента")
    void clientPageAddClientTest(Map<String, String> clientData) {

        String name = clientData.get("Клиент"),
                transports = clientData.get("Транспорт");
        Boolean status = Boolean.valueOf(clientData.get("Статус")),
                avance = Boolean.valueOf(clientData.get("Авансовая схема взаиморасчетов")),
                multisignature = Boolean.valueOf(clientData.get("Использовать мультиподпись?")),
                templateOnly = Boolean.valueOf(clientData.get("Только шаблон")),
                moderation = Boolean.valueOf(clientData.get("Модерация"));

        if(testsInitMode.equals("ui")) {
            ui
                    .subSectionClick("Настройки", "Клиенты")
                    .filterSet("Название", name)
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
                    .filterSet("Название", name)
                    .tableRowExists(name);

        } else if(testsInitMode.equals("api")){

            StringBuilder body= new StringBuilder(String.format("""
                    {
                        "name" : \"%s\",
                        "transports" : [
                    """,name));

            Arrays.stream(transports.split(",")).forEach(s->{
                ResultSet resultSet= null;
                try {
                    resultSet = msg.query("SELECT id from transports where name='" + s + "'");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    assertTrue(resultSet.next());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                String transportId=null;
                try {
                    transportId = resultSet.getString(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                body.append(String.format("""
                        {
                            "id" : %s,
                            "multisignature" : %s,
                            "on_moderation" : %s,
                            "template_only" : %s
                        },
                        """,transportId,multisignature,moderation,templateOnly));
            });

            body.deleteCharAt(body.length()-1);
            body.deleteCharAt(body.length()-1);// Удаляем последнюю запятую, если есть транспорты

            body.append(String.format("""
                        ],
                        "prepaid" : %s,
                        "status" : %s
                        }""",avance,status.equals(true)?1:0));

            String authToken=getToken("acapi","admin@admin.com","Admin");

            Response response=given()
                    .header("Authorization",authToken)
                    .contentType("application/json")
                    .and()
                    .body(body.toString())
                    .when()
                    .post(PropertyHandler.getProperty("base.URL")+"/acapi/partners")
                    .then().extract().response();
            response.body().print();

            assertTrue(response.statusCode()==200);
        }
    }
}
