package testlib.pages.pbui;

import org.openqa.selenium.By;
import testlib.base.BasePage;
import testlib.base.NavbarWorker;
import testlib.base.TableWorker;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PackageEventsManagementPage extends BasePage {

    private TableWorker tableWorker=new TableWorker();
    private NavbarWorker navbarWorker=new NavbarWorker();

    ///Основные элементы страницы
    By createButton=By.xpath(".//button[@id='button_create']"),
            openFilterButton=By.xpath(".//button[@id='button_show_filter']"),
            syncButton=By.xpath(".//button[@id='button_export']"),
            title=By.xpath(".//h3[contains(normalize-space(text()),'Управление событиями пакета')]");

    ///Фильтры
    By filterEventInput=By.xpath(".//label[text()='Событие']/parent::div//input"),
        filterPackageInput=By.xpath(".//label[text()='Пакет']/parent::div//input"),
        filterChannelInput=By.xpath(".//label[text()='Канал']/parent::div//input"),
        filterTemplateInput=By.xpath(".//label[text()='Шаблон']/parent::div//input"),
        filterIMSIInput=By.xpath(".//label[text()='Тип подписки']/parent::div//input"),
        filterImportantInput=By.xpath(".//label[text()='Тип подписки']/parent::div//input"),
        filterTransliterateInput=By.xpath(".//label[text()='Тип подписки']/parent::div//input"),
        filterAppButton=By.xpath(".//label[text()='Тип подписки']/parent::div//input"),
    filterClearButton=By.xpath(".//button[@id='button_clear_filter']");

    ///Страница создания\редактирования\удаления
    By eventInput=By.xpath(".//label[text()='Событие']/parent::div//input"),
            channelInput=By.xpath(".//label[text()='Канал']/parent::div//input"),
            templateInput=By.xpath(".//label[text()='Шаблон']/parent::div//input"),
            transliterateInput=By.xpath(".//label[text()='Транслитерация']/parent::div//input"),
            importantInput=By.xpath(".//label[text()='Важность']/parent::div//input"),
            sendingEmailInput=By.xpath(".//label[text()='Отправка на неподтвержденные email']/parent::div//input"),
            sendingMSISDNInput=By.xpath(".//label[text()='Отправка на неподтвержденные msisdn']/parent::div//input"),
            emailDraftIdInput=By.xpath(".//label[text()='Email draft_id']/parent::div//input"),
            clientSendingInput=By.xpath(".//label[text()='Отправка клиенту']/parent::div//input"),
            trustedPersonSendingInput=By.xpath(".//label[text()='Отправка доверительному лицу']/parent::div//input"),
            ndsInput=By.xpath(".");

    ///Элементы управления IMSI
    By IMSIInput=By.xpath(".//label[text()='Проверка IMSI']/parent::div//input"),
        IMSIChannelInput=By.xpath(".//label[text()='Канал']/parent::div//input"),
        IMSITemplateInput=By.xpath(".//label[text()='Шаблон']/parent::div//input"),
        IMSITransliterateInput=By.xpath(".//label[text()='Транслитерация']/parent::div//input");

    ///Кнопки страницы создания\редактирования\удаления
    By saveButton=By.xpath(".//button[@id='button_save']"),
            backButton=By.xpath(".//button[@id='button_back']"),
            deleteButton=By.xpath(".//button[@id='button_delete']");

    String[] hrefTitle= {"events","packages"};

    public void clickPackage(String packageName){
        tableWorker.tableHrefClick(packageName);
    }

    public void waitTitle(){
        waitTitle(title,"Управление событиями пакета",hrefTitle[0]);
        waitTitle(title,"Управление событиями пакета",hrefTitle[1]);
        find(createButton).shouldBe(visible).shouldBe(enabled)
                .shouldBe(interactable);
    }

    public void createEvent(){
        click(createButton);
    }

    public void openFilters(){
        click(openFilterButton);
    }

    public void clickSyncEventsButton(){
        click(syncButton);
    }

    public void setFilterEventInput(String eventName){
        click(filterEventInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+eventName+"']]"));
    }

    public void setFilterPackageInput(String packName){
        click(filterPackageInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+packName+"']]"));
    }

    public void setFilterChannelInput(String channelName){
        click(filterChannelInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+channelName+"']]"));
    }

    public void setFilterTemplateInput(String templateName){
        click(filterTemplateInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+templateName+"']]"));
    }

    public void setFilterIMSIInput(String IMSIInputString){
        click(filterIMSIInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+IMSIInputString+"']]"));
    }

    public void setFilterImportantInput(String importantInputString){
        click(filterImportantInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+importantInputString+"']]"));
    }

    public void setFilterTransliterateInput(String transliterateInputString){
        click(filterTransliterateInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+transliterateInputString+"']]"));
    }

    public void filterApp(){
        click(filterAppButton);
    }

    public void filterClear(){
        click(filterClearButton);
    }

    public void setEventInput(String event){
        click(eventInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+event+"']]"));
    }

    public void setChannelInput(String channel){
        click(channelInput);
        click(By.xpath(".//div[@class='md-layout-item'][div/label[text()='Поиск']]/parent::ul//li/button[div/span[normalize-space(text())='"+channel+"']]"));
    }

    public void setTemplateInput(String template){
        click(templateInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+template+"']]"));
    }

    public void setTransliterateInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Транслитерация']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(transliterateInput);

        if(!switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Транслитерация']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                .is(visible)) click(transliterateInput);
    }

    public void setImportantInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Важность']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(importantInput);

        if(!switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Важность']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                .is(visible)) click(importantInput);
    }

    public void setSendingEmailInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка на неподтвержденные email']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(sendingEmailInput);

        if(!switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка на неподтвержденные msisdn']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
               .is(visible)) click(sendingEmailInput);
    }

    public void setSendingMSISDNInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка на неподтвержденные msisdn']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(sendingMSISDNInput);

        if(!switchPosition)
            if(find(By.xpath(".//h4[text()='Основные настройки']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка на неподтвержденные msisdn']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(sendingMSISDNInput);
    }

    public void setNDSInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(ndsInput);

        if(!switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .is(visible)) click(ndsInput);
    }

    public void setEmailDraftIdInput(String draftId){
        sendKeys(emailDraftIdInput,draftId);
    }

    public void setIMSIInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(IMSIInput);

        if(!switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .is(visible)) click(IMSIInput);
    }

    public void setIMSIChannelInput(String channel){
        click(IMSIChannelInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+channel+"']]"));
    }

    public void setIMSITemplateInput(String template){
        click(IMSITemplateInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+template+"']]"));
    }

    public void setIMSITransliterateInput(boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)) click(IMSITransliterateInput);

        if(!switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .is(visible)) click(IMSITransliterateInput);
    }

    public void setClientSendingInputOn(){
        if(find(By.xpath(".//h4[text()='Отправка по доверенности']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка клиенту']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(clientSendingInput);
        }
    }

    public void setClientSendingInput(boolean switchPosition){
        if(switchPosition)
            if()
    }

    public void setClientSendingInputOff(){
//        if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
//                .is(visible)){
//
//            click(ndsInput);
//        }
    }

    public void setTrustedPersonSendingInputOn(){
        if(find(By.xpath(".//h4[text()='Отправка по доверенности']/ancestor::div[contains(@class,'md-card')]//label[text()='Отправка доверительному лицу']/parent::div//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(trustedPersonSendingInput);
        }
    }

    public void setTrustedPersonSendingInputOff(){
//        if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
//                .is(visible)){
//
//            click(ndsInput);
//        }
    }

    public void clickSaveButton(){
        click(saveButton);
    }

    public void clickDeleteButton(){
        click(deleteButton);
    }

    public boolean deleteIfExistsInTable(String rowValue){

        if(!$(By.xpath(".//table/tbody/tr")).exists())
            return true;

        if (tableWorker.tableRowExists(rowValue)) {
            tableWorker.tableRowCellClick(rowValue,8);
            clickDeleteButton();
            confirmDelete();
        }

        return true;
    }

    @Override
    public String getAlertText(){
        waitForElementVisible(By.xpath(".//div[@id='swal2-content']"), 10);
        waitForElementClickable(By.xpath(".//div[@id='swal2-content']"), 10);
        String alertText = find(By.xpath(".//div[@id='swal2-content']")).getText();
        return alertText;
    }
}
