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
    By filterTransactionalInput=By.xpath(".//select[@id='transactional']");
    By filterPriorityInput=By.xpath(".//select[@id='filter_partner']");
    By buttonAppFilter=By.xpath(".//button[@id='button_apply_filter']");

    ///Страница создания\редактирования
    By nameInput=By.xpath(".//input[@id='name']");
    By codeInput=By.xpath(".//input[@id='code']");
    By priorityInput=By.xpath(".//select[@id='priority']");
    By typeInput=By.xpath(".//select[@id='transactional']");
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
}
