package testlib.pages.pbui;

import org.openqa.selenium.By;
import testlib.base.BasePage;
import testlib.base.TableWorker;

import java.util.Map;
import java.util.NoSuchElementException;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class PackageManagementPage extends BasePage {

    private TableWorker tableWorker=new TableWorker();

    ///Общие элементы
    By createButton=By.xpath(".//button[@id='button_create']");
    By openFilterButton=By.xpath(".//button[@id='button_show_filter']");
    By syncButton=By.xpath(".//button[@id='button_export']");
    By title=By.xpath(".//h3[normalize-space(text())='Управление пакетами']");

    ///Фильтры
    By buttonAppFilter=By.xpath(".//button[@id='button_apply_filter']");
    By clearFiltersButton=By.xpath(".//button[@id='button_clear_filter']");

    ///Страница создания\редактирования
    By nameInput=By.xpath(".//input[@id='name']");
    By codeInput=By.xpath(".//input[@id='code']");
    By subTypeInput=By.xpath(".//label[text()='Тип подписки']/parent::div//input");
    By statusInput=By.xpath(".//label[text()='Статус']/parent::div//input");
    By descriptionInput=By.xpath(".//textarea[@id='description']");
    By startDateInput=By.xpath(".//label[text()='Действует с']/parent::div//input");
    By endDateInput=By.xpath(".//label[text()='Действует по']/parent::div//input");

    By tariffInput=By.xpath(".//input[@id='charge_rate']");
    By periodInput=By.xpath(".//input[@id='charge_period_id']");
    By ndsInput=By.xpath(".//div[contains(@class,'md-switch-container')]");

    By backButton=By.xpath(".//button[@id='button_back']");
    By saveButton=By.xpath(".//button[@id='button_save']");
    By deleteButton=By.xpath(".//button[@id='button_delete']");

    String hrefTitle="packages";

    public void waitTitle(){
        waitTitle(title,"Управление пакетами",hrefTitle);
        find(createButton).shouldBe(visible).shouldBe(enabled)
                .shouldBe(interactable);
    }

    public void createPack(){
        click(createButton);
    }

    public void openFilters(){
        click(openFilterButton);
    }

    public void syncPacks(){
        click(syncButton);
    }

    public void setFilter(String filterName, String filterValue){
        click(By.xpath(".//label[text()='"+filterName+"']/parent::div//input"));
        if($(By.xpath(".//li/button[div/span[normalize-space(text())='"+filterValue+"']]"))
                .exists())
            click(By.xpath(".//li/button[div/span[normalize-space(text())='"+filterValue+"']]"));
        else
            sendKeys(By.xpath(".//label[text()='"+filterName+"']/parent::div//input"),filterValue);
    }

    public void filterApp(){
        click(buttonAppFilter);
    }

    public void clearFilters(){
        click(clearFiltersButton);
    }

    public void setNameInput(String name){
        sendKeys(nameInput,name);
    }

    public void setCodeInput(String code){
        sendKeys(codeInput,code);
    }

    public void setSubTypeInput(String subType){
        click(subTypeInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+subType+"']]"));
    }

    public void setStatusInput(String status){
        click(statusInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+status+"']]"));
    }

    public void setDescriptionInput(String desc){
        sendKeys(descriptionInput,desc);
    }

    public void setStartDate(String year, String month, String date){

        setCalendar(startDateInput,year,month,date);
    }

    public void setEndDate(String year,String month, String date){

        setCalendar(endDateInput,year,month,date);
    }

    public void setTariffInput(String tariff){
        sendKeys(tariffInput, tariff);
    }

    public void setPeriodInput(String period){
        sendKeys(periodInput,period);
    }

    public void ndsOn(){
        if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(ndsInput);
        }
    }

    public void ndsOff(){
        if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                .is(visible)){

            click(ndsInput);
        }
    }

    public void clickSaveButton(){
        click(saveButton);
    }

    public void clickDeleteButton(){
        click(deleteButton);
    }

    public String getValueFromNameInput(){
        return getValue(nameInput);
    }

    public String getValueFromCodeInput(){
        return getValue(codeInput);
    }

    public String getValueFromSubTypeInput(){
        return getValue(subTypeInput);
    }

    public String getValueFromStatusInput(){
        return getValue(statusInput);
    }

    public String getValueFromDescInput(){
        return getValue(descriptionInput);
    }

    public String getValueFromStartDateInput(){
        return getValue(startDateInput);
    }

    public String getValueFromEndDateInput(){
        return getValue(endDateInput);
    }

    public String getValueFromTariffInput(){
        return getValue(tariffInput);
    }

    public String getValueFromPeriodInput(){
        return getValue(periodInput);
    }

    public boolean deleteIfExistsInTable(String rowValue){

            if(!$(By.xpath(".//table/tbody/tr")).exists())
                return true;

            if (tableWorker.tableRowExists(rowValue)) {
                tableWorker.tableRowCellClick(rowValue,6);
                click(deleteButton);
                confirmDelete();
            }

            return true;
    }

    @Override
    public String getAlertText(){
        return find(By.xpath(".//div[@id='swal2-content']")).getText();
    }
}
