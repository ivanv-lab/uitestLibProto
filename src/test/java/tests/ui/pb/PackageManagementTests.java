package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.PackageManagementPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class PackageManagementTests extends PBBaseTest {

    private PackageManagementPage packageManagementPage=new PackageManagementPage();
    private NavbarWorker navbarWorker=new NavbarWorker();
    private TableWorker tableWorker=new TableWorker();

    static Stream<Arguments> packageList(){
        return Stream.of(
                Arguments.of("pack1","111","Бесплатный","Активный","qweqwe",new String[]{"2025","Июнь","4"},
                        new String[]{"2026","Июнь","6"},"1","12",true),
                Arguments.of("pack2","222","Платный","Активный","qweqwe",new String[]{"2025","Июнь","4"},
                        new String[]{"2026","Июнь","6"},"2","12",true),
                Arguments.of("pack3","333","Бесплатный","Неактивный","qweqwe",new String[]{"2025","Июнь","5"},
                        new String[]{"2029","Август","2"},"3","12",true),
                Arguments.of("pack4","444","Платный","Неактивный","qweqwe",new String[]{"2025","Июнь","6"},
                        new String[]{"2028","Июнь","6"},"4","12",false)
        );
    }

    @ParameterizedTest
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    @Description("Создание пакета")
    @MethodSource("packageList")
    void packCreateTests(String name, String code, String subType, String status, String desc,String[] startDate,
                         String[] endDate,String tariff,String period,boolean nds){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();
        packageManagementPage.createPack();

        packageManagementPage.setNameInput(name);
        packageManagementPage.setCodeInput(code);
        packageManagementPage.setSubTypeInput(subType);
        packageManagementPage.setStatusInput(status);
        packageManagementPage.setDescriptionInput(desc);
        packageManagementPage.setStartDate(startDate[0],startDate[1],startDate[2]);
        packageManagementPage.setEndDate(endDate[0],endDate[1],endDate[2]);
        packageManagementPage.setTariffInput(tariff);
        packageManagementPage.setPeriodInput(period);
        if(nds)
            packageManagementPage.ndsOn();
        else packageManagementPage.ndsOff();

        packageManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists(name),true);
    }

    @Test
    @Description("Редактирование пакета")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void packEditTest(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packToEdit");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("3");
        packageManagementPage.setPeriodInput("4");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists("packToEdit"),true);

        tableWorker.tableHrefClick("packToEdit");

        packageManagementPage.setNameInput("editedPackToEdit");
        packageManagementPage.setCodeInput("54321");
        packageManagementPage.setSubTypeInput("Бесплатный");
        packageManagementPage.setStatusInput("Неактивный");
        packageManagementPage.setDescriptionInput("ячсмит");
        packageManagementPage.setStartDate("2023","Июль","12");
        packageManagementPage.setEndDate("2025","Август","25");
        packageManagementPage.setTariffInput("5");
        packageManagementPage.setPeriodInput("6");
        packageManagementPage.ndsOff();

        packageManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists("editedPackToEdit"),true);

        tableWorker.tableHrefClick("editedPackToEdit");

        assertEquals(packageManagementPage.getValueFromNameInput(),"editedPackToEdit");
        assertEquals(packageManagementPage.getValueFromCodeInput(),"54321");
        assertEquals(packageManagementPage.getValueFromSubTypeInput(),"Бесплатный");
        assertEquals(packageManagementPage.getValueFromStatusInput(),"Неактивный");
        assertEquals(packageManagementPage.getValueFromDescInput(),"ячсмит");
        assertEquals(packageManagementPage.getValueFromStartDateInput().contains("12 июл. 2023"),true);
        assertEquals(packageManagementPage.getValueFromEndDateInput().contains("25 авг. 2025"),true);
        assertEquals(packageManagementPage.getValueFromTariffInput(),"5");
        assertEquals(packageManagementPage.getValueFromPeriodInput(),"6");
    }

    @Test
    @Description("Удаление пакета")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void packDeleteTest(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packToDelete");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(tableWorker.tableRowExists("packToDelete"),true);

        tableWorker.tableHrefClick("packToDelete");

        packageManagementPage.clickDeleteButton();
        packageManagementPage.acceptAlert();

        assertEquals(tableWorker.tableRowExists("packToDelete"),false);
    }

    @Test
    @Description("Фильтрация пакетов")
    @Tag("pb-ui-2")
    @Tag("pb-packs-2")
    void packFiltersTest(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.openFilters();
        packageManagementPage.filterSetName("pack1");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("pack1"));
        assertFalse(tableWorker.tableRowExists("pack2"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetName("pack2");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("pack2"));
        assertFalse(tableWorker.tableRowExists("pack1"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetCode("111");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("111"));
        assertFalse(tableWorker.tableRowExists("222"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetCode("222");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("222"));
        assertFalse(tableWorker.tableRowExists("111"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetSubType("Платный");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("false"));
        assertFalse(tableWorker.tableRowExists("true"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetSubType("Бесплатный");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("true"));
        assertFalse(tableWorker.tableRowExists("false"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetStatus("Активный");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("true"));
        assertFalse(tableWorker.tableRowExists("false"));
        packageManagementPage.clearFilters();

        packageManagementPage.filterSetStatus("Неактивный");
        packageManagementPage.filterApp();

        assertTrue(tableWorker.tableRowExists("false"));
        assertFalse(tableWorker.tableRowExists("true"));
        packageManagementPage.clearFilters();
    }
}
