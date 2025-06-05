package tests.ui.pb;

import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;
import testlib.base.pb.PBBaseTest;
import testlib.pages.pbui.PackageManagementPage;
import testlib.utils.TagOrderer;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(TagOrderer.class)
@DisplayName("Тестирование страницы Управление пакетами PBUI")
@Tag("pb-ui")
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
        packageManagementPage.openFilters();

        assertTrue(packageManagementPage.deleteIfExistsInTable(name));

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

        assertTrue(packageManagementPage.deleteIfExistsInTable("editedPackToEdit"));

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

        tableWorker.tableRowCellClick("packToEdit",6);

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

        tableWorker.tableRowCellClick("editedPackToEdit",6);

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

        tableWorker.tableRowCellClick("packToDelete",6);

        packageManagementPage.clickDeleteButton();
        packageManagementPage.confirmDelete();

        assertEquals(tableWorker.tableRowExists("packToDelete"),false);
    }

    @Test
    @Description("Создание пакета без Наименования")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutName(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

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

        assertEquals(packageManagementPage.getAlertText(),"Поле Наименование обязательно для заполнения");
    }

    @Test
    @Description("Создание пакета без Кода")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutCode(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoCode");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(packageManagementPage.getAlertText(),"Поле Код обязательно для заполнения");
    }

    @Test
    @Description("Создание пакета без Типа подписки")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutSubType(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoSubType");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(packageManagementPage.getAlertText(),"Поле Тип подписки обязательно для заполнения");
    }

    @Test
    @Description("Создание пакета без Статуса")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutStatus(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoStatus");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(packageManagementPage.getAlertText(),"Поле Статус обязательно для заполнения");
    }

    @Test
    @Description("Создание пакета без Даты начала")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutStartDate(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoStartDate");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertEquals(packageManagementPage.getAlertText(),"Поле Дата начала обязательно для заполнения");
    }

    @Test
    @Description("Создание пакета без Даты конца")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutEndDate(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoEndDate");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("packNoEndDate"));
    }

    @Test
    @Description("Создание пакета без Тарифа пакета")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutTariff(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoTariff");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setPeriodInput("8");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("packNoTariff"));
    }

    @Test
    @Description("Создание пакета без Периода тарификации")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutPeriod(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoPeriod");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.ndsOn();

        packageManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("packNoPeriod"));
    }

    @Test
    @Description("Создание пакета без НДС")
    @Tag("pb-ui-1")
    @Tag("pb-packs-1")
    void createPackWithoutNDS(){

        navbarWorker.sectionClick("Управление пакетами");

        packageManagementPage.waitTitle();

        packageManagementPage.createPack();

        packageManagementPage.setNameInput("packNoNDS");
        packageManagementPage.setCodeInput("12345");
        packageManagementPage.setSubTypeInput("Платный");
        packageManagementPage.setStatusInput("Активный");
        packageManagementPage.setDescriptionInput("фывапро");
        packageManagementPage.setStartDate("2025","Июль","12");
        packageManagementPage.setEndDate("2026","Август","25");
        packageManagementPage.setTariffInput("7");
        packageManagementPage.setPeriodInput("8");

        packageManagementPage.clickSaveButton();

        assertTrue(tableWorker.tableRowExists("packNoNDS"));
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
