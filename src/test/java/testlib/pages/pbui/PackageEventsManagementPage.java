package testlib.pages.pbui;

import org.openqa.selenium.By;
import testlib.base.BasePage;
import testlib.base.TableWorker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class PackageEventsManagementPage extends BasePage {

    private TableWorker tableWorker=new TableWorker();

    ///Основные элементы страницы
    private final Map<String, By> basePageLocators=Map.of(
            "createButton",By.xpath("."),
            "openFilterButton",By.xpath("."),
            "syncButton",By.xpath("."),
            "title",By.xpath("."));

    ///Фильтры
    private final Map<String,By> filterLocators=Map.of(
            "filterEventInput",By.xpath("."),
            "filterPackageInput",By.xpath("."),
            "filterChannelInput",By.xpath("."),
            "filterTemplateInput",By.xpath("."),
            "filterIMSIInput",By.xpath("."),
            "filterImportantInput",By.xpath("."),
            "filterTransliterateInput",By.xpath("."),
            "filterAppButton",By.xpath("."));

    ///Страница создания\редактирования\удаления
    private final Map<String,By> cudPageLocators=Map.of(
            "eventInput",By.xpath("."),
            "channelInput",By.xpath("."),
            "templateInput",By.xpath("."),
            "transliterateInput",By.xpath("."),
            "importantInput",By.xpath("."),
            "sendingEmailInput",By.xpath("."),
            "sendingMSISDNInput",By.xpath("."),
            "emailDraftIdInput",By.xpath("."),
            "clientSendingInput",By.xpath("."),
            "trustedPersonSendingInput",By.xpath("."));

    ///Элементы управления IMSI
    private final Map<String,By> IMSILocators=Map.of(
            "IMSIInput",By.xpath("."),
            "IMSIChannelInput",By.xpath("."),
            "IMSITemplateInput",By.xpath("."),
            "IMSITransliterateInput",By.xpath("."));

    ///Кнопки страницы создания\редактирования\удаления
    private final Map<String,By> cudPageButtonsLocators=Map.of(
            "saveButton",By.xpath("."),
            "backButton",By.xpath("."),
            "deleteButton",By.xpath("."));

    ///Заголовок ссылки
    String[] hrefTitle= {"events","packages"};

    public void clickPackage(String packageName){
        tableWorker.tableHrefClick(packageName);
    }

    public void waitTitle(){
        waitTitle(basePageLocators.get("packName"),"Управление событиями пакета",hrefTitle[0]);
        waitTitle(basePageLocators.get("packName"),"Управление событиями пакета",hrefTitle[1]);
    }
}
