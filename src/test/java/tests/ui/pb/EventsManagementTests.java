package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.EventsManagementPage;
import testlib.utils.handlers.jmx.JMXClient;
import testlib.utils.handlers.jmx.JMXConnector;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Тестирование страницы Управление событиями PBUI")
@Tag("pb-ui")
//@Execution(ExecutionMode.CONCURRENT)
public class EventsManagementTests extends PBBaseTest {

    private EventsManagementPage eventsManagementPage=new EventsManagementPage();
    private NavbarWorker navbarWorker=new NavbarWorker();
    private TableWorker tableWorker=new TableWorker();

    static Stream<Arguments> eventsList(){
        return Stream.of(
                Arguments.of("event1LowTrue","111","Low","Да","qweqwe"),
                Arguments.of("event2NormalFalse","222","Normal","Нет","erbetbrtb"),
                Arguments.of("event3HighTrue","333","High","Да","g54g4g45g45g"),
                Arguments.of("event4RealtimeFalse","444","Realtime","Нет","f430f0-fooc434jv3ojv")
        );
    }

    @ParameterizedTest
    @Tag("pb-ui-1")
    @Description("Создание события")
    @MethodSource("eventsList")
    void eventCreateTest(String name,String code,String priority,String type, String desc) {

        navbarWorker.sectionClick("Управление событиями");

        eventsManagementPage.createEvent();
        eventsManagementPage.setNameInput(name);
        eventsManagementPage.setCodeInput(code);
        eventsManagementPage.setPriorityInput(priority);
        eventsManagementPage.setTypeInput(type);
        eventsManagementPage.setDescInput(desc);

        eventsManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists(name),true);
    }

    @Test
    @Tag("pb-ui-1")
    @Description("Редактирование события")
    void eventEditTest(){

        navbarWorker.sectionClick("Управление событиями");

        eventsManagementPage.createEvent();
        eventsManagementPage.setNameInput("eventToEdit");
        eventsManagementPage.setCodeInput("555");
        eventsManagementPage.setPriorityInput("Low");
        eventsManagementPage.setTypeInput("Нет");
        eventsManagementPage.setDescInput("qweqwczcdscsdc");

        eventsManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists("eventToEdit"),true);

        tableWorker.tableHrefClick("eventToEdit");

        eventsManagementPage.setNameInput("editedEvent");
        eventsManagementPage.setCodeInput("559");
        eventsManagementPage.setPriorityInput("High");
        eventsManagementPage.setTypeInput("Да");
        eventsManagementPage.setDescInput("zxcvbn");

        eventsManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("editedEvent"));

        tableWorker.tableHrefClick("editedEvent");

        assertEquals(eventsManagementPage.getValueFromNameInput(),"editedEvent");
        assertEquals(eventsManagementPage.getValueFromCodeInput(),"559");
        assertEquals(eventsManagementPage.getValueFromPriorityInput(), "High");
        assertEquals(eventsManagementPage.getValueFromTransactionalInput(),"Да");
        assertEquals(eventsManagementPage.getValueFromDescInput(),"zxcvbn");
    }

    @Test
    @Tag("pb-ui-1")
    @Description("Удаление события")
    void eventDeleteTest(){

        navbarWorker.sectionClick("Управление событиями");

        eventsManagementPage.createEvent();
        eventsManagementPage.setNameInput("eventToDelete");
        eventsManagementPage.setCodeInput("666");
        eventsManagementPage.setPriorityInput("Realtime");
        eventsManagementPage.setTypeInput("Да");
        eventsManagementPage.setDescInput("qweqwczcdscsdc");

        eventsManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("eventToDelete"));

        tableWorker.tableHrefClick("eventToDelete");

        eventsManagementPage.clickDeleteButton();

        eventsManagementPage.acceptAlert();

        assertEquals(false,tableWorker.tableRowExists("eventToDelete"));
    }

    @Test
    @Tag("pb-ui-1")
    @Description("Создание события с нечисловым кодом. Ожидается ошибка")
    void createEventWithNoDigitalCode(){

        navbarWorker.sectionClick("Управление событиями");

        eventsManagementPage.createEvent();
        eventsManagementPage.setNameInput("eventCode");
        eventsManagementPage.setCodeInput("666qwe");
        eventsManagementPage.setPriorityInput("Realtime");
        eventsManagementPage.setTypeInput("Да");
        eventsManagementPage.setDescInput("qweqwczcdscsdc");

        eventsManagementPage.clickSaveButton();
        eventsManagementPage.acceptAlert();

        assertEquals(eventsManagementPage.getAlertText(),"qweqweqwe");
    }

    @Test
    @Tag("pb-ui-2")
    @Description("Синхронизация событий")
    void eventsSyncTest() throws IOException {
        JMXConnector jmxConnector = new JMXConnector();
        jmxConnector.connect();
        JMXClient jmxClient=new JMXClient(jmxConnector.getMbsc());
        String cacheValue=jmxClient.getAttribute("WCS:group=Services,instance-type=Cache,name=cdp-cache-service",
                "Description of keySet").toString();
    }

    @Test
    @Tag("pb-ui-2")
    @Description("Фильтрация")
    void eventsFilterTest(){

        navbarWorker.sectionClick("Управление событиями");

        eventsManagementPage.openFilters();
        eventsManagementPage.filterSetName("event1LowTrue");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("event1LowTrue"),true);
        assertEquals(tableWorker.tableRowExists("event3HighTrue"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetCode("444");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("444"),true);
        assertEquals(tableWorker.tableRowExists("333"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetTransactional("Да");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("true"),true);
        assertEquals(tableWorker.tableRowExists("false"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetTransactional("Нет");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("false"),true);
        assertEquals(tableWorker.tableRowExists("true"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetPriority("Low");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("event1LowTrue"),true);
        assertEquals(tableWorker.tableRowExists("event2"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetPriority("Normal");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("event2Normal"),true);
        assertEquals(tableWorker.tableRowExists("event3HighTrue"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetPriority("High");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("event3High"),true);
        assertEquals(tableWorker.tableRowExists("event2"),false);
        eventsManagementPage.clearFilters();

        eventsManagementPage.filterSetPriority("Realtime");
        eventsManagementPage.filterApp();

        assertEquals(tableWorker.tableRowExists("event4Realtime"),true);
        assertEquals(tableWorker.tableRowExists("event3HighTrue"),false);
        eventsManagementPage.clearFilters();
    }
}
