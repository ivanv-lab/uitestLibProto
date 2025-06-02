package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.adm.AdminBaseTest;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.EventsManagementPage;

import java.util.stream.Stream;

@DisplayName("Тестирование страницы Управление событиями PBUI")
@Tag("pb-ui")
@Execution(ExecutionMode.CONCURRENT)
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
}
