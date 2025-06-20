package tests.ui.pb;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.UIHandler;
import testlib.base.pb.PBBaseTest;
import java.util.stream.Stream;

@DisplayName("Тестирование страницы Управление событиями PBUI")
@Tag("pb-ui")
@Execution(ExecutionMode.CONCURRENT)
public class EventsManagementTests extends PBBaseTest {

    String cdpCacheService="WCS:group=Services,instance-type=Cache,name=cdp-cache-service";

    static Stream<Arguments> eventsList(){
        return Stream.of(
                Arguments.of("event1LowTrue","111","Low","Да","qweqwe"),
                Arguments.of("event2NormalFalse","222","Normal","Нет","erbetbrtb"),
                Arguments.of("event3HighTrue","333","High","Да","g54g4g45g45g"),
                Arguments.of("event4RealtimeFalse","444","Realtime","Нет","f430f0-fooc434jv3ojv")
        );
    }

    @ParameterizedTest
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    @MethodSource("eventsList")
    void eventsCreateTests(String name,String code,String priority,String transact, String desc){

        ui
                .sectionClick("Управление событиями")
                .deleteFromTableIfExists(name)
                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование",name)
                .inputSet("Код",code)
                .inputSet("Приоритет",priority)
                .inputSet("Транзакционность",transact)
                .inputSet("Описание",desc)

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists(name);

        jmxHandler.invoke(cdpCacheService,"get",
                "cdp-event-profiles",code)
                .cacheValueContains("CASE_TYPE_ID",code)
                .cacheValueContains("NAME",name)
                .cacheValueContains("IS_TRANS",transact.equals("Да")?"true":"false");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Редактирование события")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void eventUpdateTests(){
        ui
                .sectionClick("Управление событиями")

                .deleteFromTableIfExists("eventToEdit")
                .deleteFromTableIfExists("editedEvent")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventToEdit")
                .inputSet("Код","555")
                .inputSet("Приоритет","Low")
                .inputSet("Транзакционность","Нет")
                .inputSet("Описание","qweqwczcdscsdc")

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("eventToEdit");

        jmxHandler.invoke(cdpCacheService,"get",
                "cdp-event-profiles","555")
                        .cacheValueContains("CASE_TYPE_ID","555")
                                .cacheValueContains("NAME","eventToEdit")
                                        .cacheValueContains("IS_TRANS","false");

        ui
                .tableCellHrefClick("eventToEdit")
                .inputSet("Наименование","editedEvent")
                .inputSet("Код","559")
                .inputSet("Приоритет","High")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","zxcvbn")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("editedEvent")
                .buttonClick("Синхронизировать");

        jmxHandler.invoke(cdpCacheService,"get",
                        "cdp-event-profiles","559")
                .cacheValueContains("CASE_TYPE_ID","559")
                .cacheValueContains("NAME","editedEvent")
                .cacheValueContains("IS_TRANS","true");

        ui
                .tableCellHrefClick("editedEvent")
                .buttonClickById(UIHandler.ButtonId.delete.getId())
                .confirmDelete();
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Удаление события")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void eventDeleteTest(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventToDelete")
                .inputSet("Код","666")
                .inputSet("Приоритет","Realtime")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","qweqwczcdscsdc")

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("eventToDelete");

        jmxHandler.invoke(cdpCacheService,"get",
                        "cdp-event-profiles","666")
                .cacheValueContains("CASE_TYPE_ID","666")
                .cacheValueContains("NAME","eventToDelete")
                .cacheValueContains("IS_TRANS","true");

        ui
                .tableCellHrefClick("eventToDelete")
                .buttonClickById(UIHandler.ButtonId.delete.getId())
                .confirmDelete()
                .tableRowNotExists("eventToDelete");

        jmxHandler.invoke(cdpCacheService,"get",
                        "cdp-event-profiles","666")
                .cacheValueIsNull();
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события с нечисловым кодом")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createEventWithNoDigitalCode(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventCode")
                .inputSet("Код","666fghj")
                .inputSet("Приоритет","Realtime")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","qweqwczcdscsdc")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("eventCode");

        jmxHandler.invoke(cdpCacheService,"get",
                        "cdp-event-profiles","666")
                .cacheValueContains("CASE_TYPE_ID","666")
                .cacheValueContains("NAME","eventCode")
                .cacheValueContains("IS_TRANS","true");

        ui
                .tableCellHrefClick("eventCode")
                .buttonClickById(UIHandler.ButtonId.delete.getId())
                .confirmDelete()
                .tableRowNotExists("eventCode");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события без заполнения Наименования")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createEventWithoutName(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Код","789")
                .inputSet("Приоритет","Realtime")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","qweqwczcdscsdc")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .alertTextEquals("Поле Наименование обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события без заполнения Кода")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createEventWithoutCode(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventNoCode")
                .inputSet("Приоритет","Realtime")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","qweqwczcdscsdc")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .alertTextEquals("Поле Код обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события без заполнения Приоритета")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createEventWithoutPriority(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventNoPriority")
                .inputSet("Код","7810")
                .inputSet("Транзакционность","Да")
                .inputSet("Описание","qweqwczcdscsdc")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("eventNoPriority");

        jmxHandler.invoke(cdpCacheService,"get",
                        "cdp-event-profiles","7810")
                .cacheValueContains("CASE_TYPE_ID","7810")
                .cacheValueContains("NAME","eventNoPriority")
                .cacheValueContains("IS_TRANS","true");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание события без заполнения Транзакционности")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createEventWithoutTransactional(){

        ui
                .sectionClick("Управление событиями")

                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","eventNoTransact")
                .inputSet("Код","789")
                .inputSet("Приоритет","Realtime")
                .inputSet("Описание","qweqwczcdscsdc")
                .buttonClickById(UIHandler.ButtonId.save.getId())

                .alertTextEquals("Поле Транзакционность обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Синхронизация событий")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void eventsSynchronizeTest(){

        ui
                .sectionClick("Управление событиями")
                .buttonClick("Синхронизировать");

        jmxHandler
                .invoke(cdpCacheService,
                "get","cdp-event-profiles","111")
                        .cacheValueContains("CASE_TYPE_ID","111")
                                .cacheValueNotContains("NAME","event1LowTrue")
                .cacheValueNotContains("IS_TRANS","true")

                .invoke(cdpCacheService,"get","cdp-event-profiles","222")
                .cacheValueNotContains("CASE_TYPE_ID","222")
                .cacheValueNotContains("NAME","event2NormalFalse")
                .cacheValueNotContains("IS_TRANS","false")

                .invoke(cdpCacheService,"get","cdp-event-profiles","333")
                .cacheValueNotContains("CASE_TYPE_ID","333")
                .cacheValueNotContains("NAME","event3HighTrue")
                .cacheValueNotContains("IS_TRANS","true")

                .invoke(cdpCacheService,"get","cdp-event-profiles","444")
                .cacheValueNotContains("CASE_TYPE_ID","444")
                .cacheValueNotContains("NAME","event4RealtimeFalse")
                .cacheValueNotContains("IS_TRANS","false")

                .invoke(cdpCacheService,"get","cdp-event-profiles","7810")
                .cacheValueNotContains("CASE_TYPE_ID","7810")
                .cacheValueNotContains("NAME","eventNoPriority")
                .cacheValueNotContains("IS_TRANS","true");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Фильтрация")
    @Link("https://jira.wsoft.ru/browse/LTB-1658")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void eventFilterTest(){

        ui
                .sectionClick("Управление событиями")
                .filterSet("Наименование","event1LowTrue")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Код","444")
                .tableRowExists("444")
                .tableRowNotExists("333")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Транзакционность","Нет")
                .tableRowExists("Нет")
                .tableRowNotExists("Да")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Транзакционность","Да")
                .tableRowExists("Да")
                .tableRowNotExists("Нет")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Приоритет","Low")
                .tableRowExists("event1LowTrue")
                .tableRowNotExists("event3HighTrue")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Приоритет","Normal")
                .tableRowExists("event2NormalFalse")
                .tableRowNotExists("event3HighTrue")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Приоритет","High")
                .tableRowExists("event3HighTrue")
                .tableRowNotExists("event4RealtimeFalse")

                .buttonClickById(UIHandler.ButtonId.filterClean.getId())

                .filterSet("Приоритет","Realtime")
                .tableRowExists("event4RealtimeFalse")
                .tableRowNotExists("event3HighTrue");
    }
}
