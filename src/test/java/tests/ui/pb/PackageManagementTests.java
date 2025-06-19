package tests.ui.pb;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import testlib.base.UIHandler;
import testlib.base.pb.PBBaseTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.$$;

@DisplayName("Тестирование страницы Управление пакетами PBUI")
@Tag("pb-ui")
public class PackageManagementTests extends PBBaseTest {

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
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    @MethodSource("packageList")
    void packCreateTest(String name, String code, String subType, String status, String desc,String[] startDate,
                        String[] endDate,String tariff,String period,boolean nds){

        ui
                .sectionClick("Управление пакетами");

        List<String> rows=$$(By.xpath(".//table/tbody/tr"))
                .asFixedIterable()
                .stream().map(SelenideElement::getText)
                .collect(Collectors.toList());

        for(int i=0;i<rows.size();i++){
            if(rows.get(i).contains(name)){
                ui.tableRowCellClick(name,6)
                        .buttonClickById(UIHandler.ButtonId.delete.getId())
                        .confirmDelete();
            }
        }

        ui
                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование",name)
                .inputSet("Код",code)
                .inputSet("Тип подписки",subType)
                .inputSet("Статус",status)
                .inputSet("Описание",desc)
                .calendarSet("Действует с")
                .calendarSet("Действует по")
                .inputSet("Тариф пакета", tariff)
                .inputSet("Период тарификации",period)
                .switchCheckbox("Облагается НДС",nds)
                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists(name);
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Редактирование пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void packEditTest(){

        ui
                .sectionClick("Управление пакетами");

        List<String> rows=$$(By.xpath(".//table/tbody/tr"))
                .asFixedIterable()
                .stream().map(SelenideElement::getText)
                .collect(Collectors.toList());

        for(int i=0;i<rows.size();i++){
            if(rows.get(i).contains("packToEdit")){
                ui.tableRowCellClick("packToEdit",6)
                        .buttonClickById(UIHandler.ButtonId.delete.getId())
                        .confirmDelete();
            }
        }

        for(int i=0;i<rows.size();i++){
            if(rows.get(i).contains("editedPackToEdit")){
                ui.tableRowCellClick("editedPackToEdit",6)
                        .buttonClickById(UIHandler.ButtonId.delete.getId())
                        .confirmDelete();
            }
        }

        ui
                .buttonClickById(UIHandler.ButtonId.create.getId())
                .inputSet("Наименование","packToEdit")
                .inputSet("Код","12345")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с")
                .calendarSet("Действует по",10)
                .inputSet("Тариф пакета", "3")
                .inputSet("Период тарификации","4")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("packToEdit")

                .tableRowCellClick("packToEdit",6)

                .inputSet("Наименование","editedPackToEdit")
                .inputSet("Код","54321")
                .inputSet("Тип подписки","Бесплатный")
                .inputSet("Статус","Неактивный")
                .inputSet("Описание","ячсмит")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "5")
                .inputSet("Период тарификации","6")
                .switchCheckbox("Облагается НДС",false)

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("editedPackToEdit")

                .tableRowCellClick("editedPackToEdit",6)

                .inputContains("Наименование","editedPackToEdit")
                .inputContains("Код","54321")
                .inputContains("Тип подписки","Бесплатный")
                .inputContains("Статус","Неактивный")
                .inputContains("Описание","ячсмит")
                .inputContains("Тариф пакета", "5")
                .inputContains("Период тарификации","6")
                .switchCheckboxIs("Облагается НДС",false);
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Удаление пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void packDeleteTest(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packToDelete")
                .inputSet("Код","12345")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())

                .tableRowExists("packToDelete")

                .tableRowCellClick("packToDelete",6)

                .buttonClickById(UIHandler.ButtonId.delete.getId())
                .confirmDelete()
                .tableRowNotExists("packToDelete");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Наименования")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutName(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Код","12345")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .alertTextEquals("Поле Наименование обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Кода")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutCode(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoCode")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .alertTextEquals("Поле Код обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Типа подписки")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutSubType(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoCode")
                .inputSet("Код","1255")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .alertTextEquals("Поле Тип подписки обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Статуса")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutStatus(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoCode")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",+15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .alertTextEquals("Поле Статус обязательно для заполнения");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Даты начала")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutSTartDate(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoStartDate")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует по",15)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists("packNoStartDate");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Даты конца")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutEndDate(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoEndDate")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists("packNoEndDate");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Тарифа пакета")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutTariff(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoTariff")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",10)
                .inputSet("Период тарификации","8")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists("packNoTariff");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без Периода тарификации")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutPeriod(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoPeriod")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",10)
                .inputSet("Тариф пакета", "7")
                .switchCheckbox("Облагается НДС")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists("packNoPeriod");
    }

    @Test
    @Feature("pb-custom-1")
    @Tag("pb-custom-1")
    @Description("Создание пакета без НДС")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void createPackWithoutNDS(){

        ui
                .sectionClick("Управление пакетами")

                .buttonClickById(UIHandler.ButtonId.create.getId())

                .inputSet("Наименование","packNoNDS")
                .inputSet("Код","6547")
                .inputSet("Тип подписки","Платный")
                .inputSet("Статус","Активный")
                .inputSet("Описание","фывапро")
                .calendarSet("Действует с",-3)
                .calendarSet("Действует по",10)
                .inputSet("Тариф пакета", "7")
                .inputSet("Период тарификации","8")

                .buttonClickById(UIHandler.ButtonId.save.getId())
                .tableRowExists("packNoNDS");
    }

    @Test
    @Feature("pb-custom-2")
    @Tag("pb-custom-2")
    @Description("Фильтрация")
    @Link("https://jira.wsoft.ru/browse/LTB-1659")
    @Link("https://jira.wsoft.ru/browse/LTB-1657")
    void packFilterTest(){

        ui
                .sectionClick("Управление пакетами")
                .filterSet("Наименование","pack1")
                .tableRowExists("pack1")
                .tableRowNotExists("pack2")

                .buttonClickById("Очистить фильтры")

                .filterSet("Наименование","pack2")
                .tableRowExists("pack2")
                .tableRowNotExists("pack1")

                .buttonClickById("Очистить фильтры")

                .filterSet("Код","111")
                .tableRowExists("111")
                .tableRowNotExists("222")

                .buttonClickById("Очистить фильтры")

                .filterSet("Код","222")
                .tableRowExists("222")
                .tableRowNotExists("111")

                .buttonClickById("Очистить фильтры")

                .filterSet("Тип подписки","Платный")
                .tableRowExists("pack2")
                .tableRowNotExists("pack3")

                .buttonClickById("Очистить фильтры")

                .filterSet("Тип подписки","Бесплатный")
                .tableRowExists("pack3")
                .tableRowNotExists("pack2")

                .buttonClickById("Очистить фильтры")

                .filterSet("Статус","Активный")
                .tableRowExists("pack2")
                .tableRowNotExists("pack3")

                .buttonClickById("Очистить фильтры")

                .filterSet("Статус","Неактивный")
                .tableRowExists("pack3")
                .tableRowNotExists("pack2");
    }
}
