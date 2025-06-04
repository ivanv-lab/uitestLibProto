package testlib.pages.pbui;

import org.openqa.selenium.By;
import testlib.base.BasePage;

public class EventsManagementPage extends BasePage {

    ///Общие элементы
    By createButton=By.xpath(".//button[@id='button_create']");
    By openFilterButton=By.xpath(".//button[@id='button_show_filter']");
    By syncButton=By.xpath(".//button[@id='button_export']");

    ///Фильтры
    By filterNameInput=By.xpath(".//input[@id='name']");
    By filterCodeInput=By.xpath(".//input[@id='code']");
    By filterTransactionalInput=By.xpath(".//label[text()='Транзакционность']/parent::div//input");
    By filterPriorityInput=By.xpath(".//label[text()='Приоритет']/parent::div//input");
    By buttonAppFilter=By.xpath(".//button[@id='button_apply_filter']");
    By clearFiltersButton=By.xpath(".//button[@id='button_clear_filter']");

    ///Страница создания\редактирования
    By nameInput=By.xpath(".//input[@id='name']");
    By codeInput=By.xpath(".//input[@id='code']");
    By priorityInput=By.xpath(".//label[text()='Приоритет']/parent::div//input");
    By typeInput=By.xpath(".//label[text()='Транзакционность']/parent::div//input");
    By descriptionInput=By.xpath(".//textarea[@id='description']");
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

    public void filterSetTransactional(String value){
        click(filterTransactionalInput);
        click(By.xpath(".//li/button[div/span[text()='"+value+"']]"));
    }

    public void filterSetPriority(String value){
        click(filterPriorityInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+value+"']]"));
    }

    public void filterApp(){
        click(buttonAppFilter);
    }

    public void setNameInput(String name){
        sendKeys(nameInput,name);
    }

    public void setCodeInput(String code){
        sendKeys(codeInput,code);
    }

    public void setPriorityInput(String value){
        click(priorityInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+value+"']]"));
    }

    public void setTypeInput(String type){
        click(typeInput);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+type+"']]"));
    }

    public void setDescInput(String desc){
        sendKeys(descriptionInput,desc);
    }

    public void clickSaveButton(){
        click(saveButton);
    }

    public void clickDeleteButton(){
        click(deleteButton);
    }

    public void clearFilters(){
        click(clearFiltersButton);
    }

    public String getValueFromNameInput(){
        return getValue(nameInput);
    }

    public String getValueFromCodeInput(){
        return getValue(codeInput);
    }

    public String getValueFromPriorityInput(){
        return getValue(priorityInput);
    }

    public String getValueFromTransactionalInput(){
        return getValue(typeInput);
    }

    public String getValueFromDescInput(){
        return getValue(descriptionInput);
    }
}
