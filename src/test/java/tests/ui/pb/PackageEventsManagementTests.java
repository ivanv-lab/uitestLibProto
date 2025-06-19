package tests.ui.pb;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.UIHandler;
import testlib.base.pb.PBBaseTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тестирование страницы Управление событиями пакета PBUI")
@Tag("pb-ui")
public class PackageEventsManagementTests extends PBBaseTest {

    record CacheValues(String free, String name, String status, String important,
                       String packageCode, String templateName, String translit, String imsiCheck,
                       String imsiTemplateName, String imsiTranslit, String sendEmail, String sendMSISDN) {

        public Map<String, String> returnValues() {
            Map<String, String> values = new HashMap<>();

            values.put("name", this.name);
            values.put("status", this.status);
            values.put("important", this.important);
            values.put("package-code", this.packageCode);
            values.put("template-name", this.templateName);
            values.put("translit", this.translit);
            values.put("imsi-check", this.imsiCheck);
            values.put("imsi-template-name", this.imsiTemplateName);
            values.put("imsi-translit", this.imsiTranslit);
            values.put("send-unconfirmed-email", this.sendEmail);
            values.put("send-unconfirmed-msisdn", this.sendMSISDN);

            return values;
        }

    }

    static Stream<Arguments> packageEventList() {
        return Stream.of(
                Arguments.of("pack1", "event1LowTrue", "111", "Push", "tempPush", true, true, true, true, true, "SMS", "tempSMS", true, true, true,
                        Map.of("pack1", new CacheValues("true", "pack1", "true", "1", "111", "tempPush", "1", "1", "tempSMS", "1", "1", "1"),
                                "pack2", new CacheValues("false", "pack2", "true", "", "222", "tempPush", "1", "1", "tempViber", "", "1", ""),
                                "pack3", new CacheValues("false", "pack4", "false", "1", "444", "tempViber", "1", "1", "tempPush", "1", "1", "1"))),

                Arguments.of("pack1", "event3HighTrue", "333", "SMS", "tempSMS", false, true, false, true, false, "", "", false, false, false,
                        Map.of("pack1", new CacheValues("true", "pack1", "true", "1", "111", "tempSMS", "", "", "", "", "", "1"),
                                "packNoTariff", new CacheValues("false", "packNoTariff", "true", "1", "6547", "tempViber2", "1", "1", "tempWA", "1", "1", "1"))),

                Arguments.of("pack2", "event1LowTrue", "111", "Push", "tempPush", true, false, true, false, true, "Viber", "tempViber", false, true, false,
                        Map.of("pack1", new CacheValues("true", "pack1", "true", "1", "111", "tempPush", "1", "1", "tempSMS", "1", "1", "1"),
                                "pack2", new CacheValues("false", "pack2", "true", "", "222", "tempPush", "1", "1", "tempViber", "", "1", ""),
                                "pack3", new CacheValues("false", "pack4", "false", "1", "444", "tempViber", "1", "1", "tempPush", "1", "1", "1"))),

                Arguments.of("pack3", "event4RealtimeFalse", "444", "Email", "tempEmail", false, false, false, false, false, "", "", false, false, true,
                        Map.of("pack3", new CacheValues("true", "pack3", "false", "", "333", "tempEmail", "", "", "", "", "", ""),
                                "packNoStartDate", new CacheValues("false", "packNoStartDate", "true", "1", "6547", "tempWA", "", "", "", "", "", "1"))),

                Arguments.of("pack4", "event1LowTrue", "111", "Viber", "tempViber", true, true, true, true, true, "Push", "tempPush", true, false, false,
                        Map.of("pack1", new CacheValues("true", "pack1", "true", "1", "111", "tempPush", "1", "1", "tempSMS", "1", "1", "1"),
                                "pack2", new CacheValues("false", "pack2", "true", "", "222", "tempPush", "1", "1", "tempViber", "", "1", ""),
                                "pack3", new CacheValues("false", "pack4", "false", "1", "444", "tempViber", "1", "1", "tempPush", "1", "1", "1"))),

                Arguments.of("packNoStartDate", "event4RealtimeFalse", "444", "WhatsApp", "tempWA", false, true, false, true, false, "", "", false, false, false,
                        Map.of("pack3", new CacheValues("true", "pack3", "false", "", "333", "tempEmail", "", "", "", "", "", ""),
                                "packNoStartDate", new CacheValues("false", "packNoStartDate", "true", "1", "6547", "tempWA", "", "", "", "", "", "1"))),

                Arguments.of("packNoNDS", "eventNoPriority", "7810", "Mail Notify", "tempMN", true, false, true, false, true, "Email", "tempEmail", true, true, false,
                        Map.of("packNoNDS", new CacheValues("false", "packNoNDS", "true", "", "6547", "tempMN", "1", "1", "tempEmail", "1", "1", ""),
                                "packNoEndDate", new CacheValues("false", "packNoEndDate", "true", "1", "6547", "tempWA2", "", "", "", "", "", "1"))),

                Arguments.of("packNoPeriod", "event2NormalFalse", "222", "SMS", "tempSMS2", false, false, false, false, false, "", "", false, false, true,
                        Map.of("packNoPeriod", new CacheValues("false", "packNoPeriod", "true", "", "6547", "tempSMS2", "", "", "", "", "", ""))),

                Arguments.of("packNoTariff", "event3HighTrue", "333", "Viber", "tempViber2", true, true, true, true, true, "WhatsApp", "tempWA", true, false, false,
                        Map.of("pack1", new CacheValues("true", "pack1", "true", "1", "111", "tempSMS", "", "", "", "", "", "1"),
                                "packNoTariff", new CacheValues("false", "packNoTariff", "true", "1", "6547", "tempViber2", "1", "1", "tempWA", "1", "1", "1"))),

                Arguments.of("packNoEndDate", "eventNoPriority", "7810", "WhatsApp", "tempWA2", false, true, false, true, false, "", "", false, false, false,
                        Map.of("packNoNDS", new CacheValues("false", "packNoNDS", "true", "", "6547", "tempMN", "1", "1", "tempEmail", "1", "1", ""),
                                "packNoEndDate", new CacheValues("false", "packNoEndDate", "true", "1", "6547", "tempWA2", "", "", "", "", "", "1")))
        );
    }

    @ParameterizedTest
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Создание событий пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    @MethodSource("packageEventList")
    void createPackEventsTest(String pack, String event, String eventCode, String channel, String template, boolean transliterate, boolean important, boolean emailSending,
                              boolean msisdnSending, boolean imsiOn, String imsiChannel, String imsiTemplate, boolean imsiTransliterate,
                              boolean clientSending, boolean trustedSending) throws SQLException {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick(pack)
                .deleteFromTableIfExists(event)
                .buttonClick("Создать")
                .inputSet("Событие", event)
                .inputSet("Канал", channel)
                .inputSet("Шаблон", template)

                .switchCheckbox("Транслитерация", transliterate)
                .switchCheckbox("Важность", important)
                .switchCheckbox("Отправка на неподтвержденные email", emailSending)
                .switchCheckbox("Отправка на неподтвержденные msisdn", msisdnSending);

        if (imsiOn) {
            ui
                    .switchCheckbox("Проверка IMSI", imsiOn)
                    .cardInputSet("Канал", imsiChannel, "Настройки IMSI")
                    .cardInputSet("Шаблон", imsiTemplate, "Настройки IMSI")
                    .cardSwitchCheckbox("Транслитерация", imsiTransliterate, "Настройки IMSI");
        }

        ui
                .switchCheckbox("Отправка клиенту", clientSending)
                .switchCheckbox("Отправка доверительному лицу", trustedSending)
                .buttonClick("Сохранить")
                .tableRowExists(event);
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Редактирование событий пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void updatePackageEventTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack1")
                .deleteFromTableIfExists("eventNoPriority")
                .deleteFromTableIfExists("event4RealtimeFalse")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")

                .buttonClick("Сохранить");

//        cache
//                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
//                .openCache()
//                .get("cdp-event-profiles", "7810")
//                .xmlContains("CASE_TYPE_ID", "7810")
//                .xmlContains("NAME", "eventNoPriority")
//                .xmlContains("name", "pack1");

        ui
                .tableCellHrefClick("eventNoPriority")
                .inputSet("Событие", "event4RealtimeFalse")
                .inputSet("Канал", "Push")
                .inputSet("Шаблон", "tempPush")

                .switchCheckbox("Транслитерация")
                .switchCheckbox("Важность")
                .switchCheckbox("Отправка на неподтвержденные email")
                .switchCheckbox("Отправка на неподтвержденные msisdn")

                .switchCheckbox("Проверка IMSI")
                .cardInputSet( "Канал", "SMS","Настройки IMSI")
                .cardInputSet( "Шаблон", "tempSMS2","Настройки IMSI")
                .cardSwitchCheckbox("Транслитерация", true,"Настройки IMSI")

                .switchCheckbox("Отправка клиенту")
                .switchCheckbox("Отправка доверительному лицу")

                .buttonClick("Сохранить")

                .tableRowExists("event4RealtimeFalse")

                .tableCellHrefClick("event4RealtimeFalse")

                .inputContains("Событие", "event4RealtimeFalse")
                .inputContains("Канал", "Push")
                .inputContains("Шаблон", "tempPush")

                .isRadioButtonOn("Транслитерация")
                .isRadioButtonOn("Важность")
                .isRadioButtonOn("Отправка на неподтвержденные email")
                .isRadioButtonOn("Отправка на неподтвержденные msisdn")

                .isRadioButtonOn("Проверка IMSI")
                .cardInputContains("Настройки IMSI", "Канал", "SMS")
                .cardInputContains("Настройки IMSI", "Шаблон", "tempSMS2")
                .isRadioButtonOn("Транслитерация", 1)

                .isRadioButtonOn("Отправка клиенту")
                .isRadioButtonOn("Отправка доверительному лицу");

        cache
                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                .openCache()
                .get("cdp-event-profiles", "444")
                .xmlContains("CASE_TYPE_ID", "444")
                .xmlContains("NAME", "event4RealtimeFalse")
                .xmlContains("name", "pack1");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Удаление события из пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void deletePackageEventTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack1")
                .buttonClick("Создать")
                .inputSet("Событие", "event2NormalFalse")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")
                .buttonClick("Сохранить")
                .tableRowExists("event2NormalFalse");

        cache
                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                .openCache()
                .get("cdp-event-profiles", "222")
                .xmlContains("CASE_TYPE_ID", "222")
                .xmlContains("NAME", "event2NormalFalse")
                .xmlContains("name", "pack1");

        ui
                .tableCellHrefClick("event2NormalFalse")
                .buttonClick("Удалить")
                .confirm()
                .tableRowNotExists("event2NormalFalse");

        cache
                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                .openCache()
                .get("cdp-event-profiles", "222")
                .xmlContains("CASE_TYPE_ID", "222")
                .xmlContains("NAME", "event2NormalFalse")
                .xmlNotContains("name", "pack1");

    }

    @Test
    @Feature("pb-custom-4")
    @Tag("pb-custom-4")
    @Description("Редактирование события, которое находится в пакете, со страницы Управление событиями")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void updatePackageEventFromEventsTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack1")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")

                .buttonClick("Сохранить")

                .sectionClick("Управление событиями")
                .tableCellHrefClick("eventNoPriority")
                .inputSet("Наименование", "eventNoPriorityEdited")
                .inputSet("Транзакционность", "Нет")
                .buttonClick("Сохранить");

        cache
                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                .openCache()
                .get("cdp-event-profiles", "7810")
                .xmlContains("CASE_TYPE_ID", "7810")
                .xmlContains("NAME", "eventNoPriorityEdited")
                .xmlContains("IS_TRANS", "false");
    }

    @Test
    @Feature("pb-custom-4")
    @Tag("pb-custom-4")
    @Description("Удаление события, которое находится в пакете, со страницы Управление событиями")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void deletePackageEventFromEventsTest() {

        ui
                .sectionClick("Управление событиями")
                .tableCellHrefClick("event4RealtimeFalse")
                .buttonClick("Удалить")
                .confirm()
                .alertTextShouldBe("Удаление невозможно, пока есть связь с пакетом или событием");
    }

    @Test
    @Feature("pb-custom-4")
    @Tag("pb-custom-4")
    @Description("Редактирование пакета, в котором есть события")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void updatePackageEventsFromPackagesTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellClick(6, "pack1")
                .inputSet("Наименование", "pack1Edited")
                .inputSet("Код", "121")
                .inputSet("Статус", "Неактивный")

                .buttonClick("Сохранить")
                .waiting(3);

        cache
                .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                .openCache()
                .get("cdp-event-profiles", "111")
                .xmlContains("CASE_TYPE_ID", "111")
                .xmlContains("name", "pack1Edited");
    }

    @Test
    @Feature("pb-custom-4")
    @Tag("pb-custom-4")
    @Description("Удаление пакета, в котором есть события")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void deletePackageWhereEventsExists() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellClick(6, "pack2")
                .buttonClick("Удалить")
                .confirm()
                .alertTextShouldBe("Удаление невозможно, пока есть связь с пакетом или событием");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Добавление события в пакет без События")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackageEventWithoutEventTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack2")
                .buttonClick("Создать")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")
                .buttonClick("Сохранить")
                .alertTextShouldBe("Поле Событие обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Добавление события в пакет без Канала")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackageEventWithoutChannelTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack2")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .buttonClick("Сохранить")
                .alertTextShouldBe("Поле Канал обязательно для заполнения\n" +
                        "Поле Шаблон обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Добавление события в пакет без Шаблона")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackageEventWithoutTemplateTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack2")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .inputSet("Канал", "SMS")
                .buttonClick("Сохранить")
                .alertTextShouldBe("Поле Шаблон обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Добавление события в пакет без Канала IMSI")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackageEventWithoutIMSIChannelTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack2")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")

                .radioButtonOn("Проверка IMSI")
                .buttonClick("Сохранить")
                .alertTextShouldBe("Поле Канал обязательно для заполнения\n" +
                        "Поле Шаблон обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Добавление события в пакет без Шаблона IMSI")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackageEventWithoutIMSITemplateTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack2")
                .buttonClick("Создать")
                .inputSet("Событие", "eventNoPriority")
                .inputSet("Канал", "SMS")
                .inputSet("Шаблон", "tempSMS")

                .radioButtonOn("Проверка IMSI")
                .cardInputSet("Настройки IMSI", "Канал", "Push")
                .buttonClick("Сохранить")
                .alertTextShouldBe("Поле Шаблон обязательно для заполнения");
    }

    @ParameterizedTest
    @Feature("pb-custom-3")
    @Tag("pb-custom-3")
    @Description("Синхронизация")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    @MethodSource("packageEventList")
    void syncTest(String pack, String event, String eventCode, String channel, String template, boolean transliterate, boolean important, boolean emailSending,
                  boolean msisdnSending, boolean imsiOn, String imsiChannel, String imsiTemplate, boolean imsiTransliterate,
                  boolean clientSending, boolean trustedSending, Map<String, CacheValues> cacheValuesMap) {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick(pack)
                .buttonClick("Синхронизировать");

        cacheValuesMap.forEach((key, values) -> {
            Map<String, String> cacheValues = values.returnValues();
            cacheValues.forEach((cacheKey, cacheValue) -> {
                if (!cacheValue.equals("")) {
                    cache
                            .cacheService("WCS:group=Services,instance-type=Cache,name=cdp-cache-service")
                            .openCache()
                            .get("cdp-event-profiles", eventCode)
                            .xmlContains("CASE_TYPE_ID", eventCode)
                            .xmlContains("NAME", event)
                            .xmlContains(cacheKey, cacheValue);
                }
            });
        });
    }

    @Test
    @Feature("pb-custom-3")
    @Tag("pb-custom-3")
    @Description("Фильтрация")
    @Link("https://jira.wsoft.ru/browse/LTB-1660")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void packageEventsFilterTest() {

        ui
                .sectionClick("Управление пакетами")
                .tableCellHrefClick("pack1")

                .filterSet("Событие", "event1LowTrue")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Событие", "event3HighTrue")
                .tableRowExists("event3HighTrue")
                .tableRowNotExists("event1LowTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Канал", "SMS")
                .filterSet("Шаблон", "tempSMS")
                .tableRowExists("event3HighTrue")
                .tableRowNotExists("event1LowTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Канал", "SMS")
                .filterSet("Шаблон", "tempSMS2")
                .tableRowNotExists("event3HighTrue")
                .tableRowNotExists("event1LowTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Канал", "Push")
                .filterSet("Шаблон", "tempPush")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Проверка IMSI", "Включено")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Проверка IMSI", "Выключено")
                .tableRowExists("event3HighTrue")
                .tableRowNotExists("event1LowTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Важность", "Да")
                .tableRowExists("event1LowTrue")
                .tableRowExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Важность", "Нет")
                .tableRowNotExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Транслитерация", "Да")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")
                .buttonClick("Очистить фильтры")

                .filterSet("Транслитерация", "Нет")
                .tableRowExists("event3HighTrue")
                .tableRowNotExists("event1LowTrue")
                .buttonClick("Очистить фильтры");
    }
}
