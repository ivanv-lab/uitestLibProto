package testlib.pages.pbui;

import org.openqa.selenium.By;
import testlib.base.BasePage;

import java.util.Map;

public class PackageManagementPage extends BasePage {

    ///Общие элементы
    By createButton=By.xpath(".//button[@id='button_create']");
    By openFilterButton=By.xpath(".//button[@id='button_show_filter']");
    By syncButton=By.xpath(".//button[@id='button_export']");

    ///Фильтры
    By filterNameInput=By.xpath(".//input[@id='name']");
    By filterCodeInput=By.xpath(".//input[@id='code']");
    By filterSubTypeInput=By.xpath(".//select[@id='free']");
    By filterStatusInput=By.xpath(".//select[@id='active']");
    By buttonAppFilter=By.xpath(".//button[@id='button_apply_filter']");
    By clearFiltersButton=By.xpath(".//button[@id='button_clear_filter']");

    ///Страница создания\редактирования
    By nameInput=By.xpath(".//input[@id='name']");
    By codeInput=By.xpath(".//input[@id='code']");
    By subTypeInput=By.xpath(".//select[@id='free']");
    By statusInput=By.xpath(".//select[@id='active']");
    By descriptionInput=By.xpath(".//textarea[@id='description']");
    By startDateInput=By.xpath(".//input[@class='vdatetime-input md-input']/ancestor::div/label[text()='Действует с']");
    By endDateInput=By.xpath(".//input[@class='vdatetime-input md-input']/ancestor::div/label[text()='Действует по']");

    By tariffInput=By.xpath(".//input[@id='charge_rate']");
    By periodInput=By.xpath(".//input[@id='charge_period_id']");
    By ndsInput=By.xpath(".//input[contains(@id,'md-switch')]");

    By backButton=By.xpath(".//button[@id='button_back']");
    By saveButton=By.xpath(".//button[@id='button_save']");
    By deleteButton=By.xpath(".//button[@id='button_delete']");

    public void createEvent(){
        click(createButton);
    }

    public void openFilters(){
        click(openFilterButton);
    }

    public void syncEvents(){
        click(syncButton);
    }

    public void filterSetName(String name){
        sendKeys(filterNameInput,name);
    }

    public void filterSetCode(String code){
        sendKeys(filterCodeInput,code);
    }

    public void filterSetStatus(String status){
        click(filterStatusInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+status+"']]"));
    }

    public void filterSetSubType(String subType){
        click(filterSubTypeInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+subType+"']]"));
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

    public void setStartDate(int year, String month, int date){

        setCalendar(startDateInput,year,month,date);
    }

    public void setEndDate(int year,String month, int date){

        setCalendar(endDateInput,year,month,date);
    }

    public void setTariffInput(String tariff){
        sendKeys(tariffInput, tariff);
    }
}
