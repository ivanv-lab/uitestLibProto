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
    @Tag("pb-ui")
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

        tableWorker.tableRowExists(name);
    }

    @Test
    @Tag("pb-ui")
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

        tableWorker.tableRowExists("eventToEdit");

        tableWorker.tableHrefClick("eventToEdit");

        eventsManagementPage.setNameInput("editedEvent");
        eventsManagementPage.setCodeInput("559");
        eventsManagementPage.setPriorityInput("High");
        eventsManagementPage.setTypeInput("Да");
        eventsManagementPage.setDescInput("zxcvbn");

        eventsManagementPage.clickSaveButton();

        tableWorker.tableRowExists("editedEvent");

        tableWorker.tableHrefClick("editedEvent");

        assertEquals(eventsManagementPage.getValueFromNameInput(),"editedEvent");
        assertEquals(eventsManagementPage.getValueFromCodeInput(),"559");
        assertEquals(eventsManagementPage.getValueFromPriorityInput(), "High");
        assertEquals(eventsManagementPage.getValueFromTransactionalInput(),"Да");
        assertEquals(eventsManagementPage.getValueFromDescInput(),"zxcvbn");
    }

    @Test
    @Tag("pb-ui")
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

        tableWorker.tableRowExists("eventToDelete");

        tableWorker.tableHrefClick("eventToDelete");

        eventsManagementPage.clickDeleteButton();

        assertEquals(false,tableWorker.tableRowExists("eventToDelete"));
    }

    @Test
    @Tag("pb-ui")
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
    @Tag("pb-ui")
    @Description("Синхронизация событий")
    void eventsSyncTest() throws IOException {
        JMXConnector jmxConnector = new JMXConnector();
        jmxConnector.connect();
        JMXClient jmxClient=new JMXClient(jmxConnector.getMbsc());
        String cacheValue=jmxClient.getAttribute("WCS:group=Services,instance-type=Cache,name=cdp-cache-service",
                "Description of keySet").toString();
    }

    @Test
    @Tag("pb-ui")
    @Description("Фильтрация")
    void eventsFilterTest(){


    }
}
