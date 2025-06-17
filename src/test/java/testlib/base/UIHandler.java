package testlib.base;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class UIHandler extends BasePage{

    public UIHandler loginAcui(){
        Selenide.open(PropertyHandler.getProperty("base.URL"+"/acui/login"));
        LoginPage loginPage=new LoginPage();
        loginPage.changeLanguageToRus();
        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        return this;
    }

    public UIHandler loginLkui(){
        Selenide.open(PropertyHandler.getProperty("base.URL"+"/lkui/login"));
        LoginPage loginPage=new LoginPage();
        loginPage.changeLanguageToRus();
        loginPage.login(PropertyHandler.getProperty("lk.login"),PropertyHandler.getProperty("lk.password"));
        return this;
    }

    public UIHandler login(String address, String login,String password){
        Selenide.open(address);
        LoginPage loginPage=new LoginPage();
        loginPage.changeLanguageToRus();
        loginPage.login(login,password);
        return this;
    }

    public UIHandler logout(){
        click(By.xpath(""));
        return this;
    }

    public UIHandler sectionClick(String section){
        click(By.xpath(".//div[contains(@class,'sidebar')]/ul/li[a/p[contains(.,'"+section+"')]]/a"));
        return this;
    }

    public UIHandler subSectionClick(String section, String subSection){
        click(By.xpath(section+"[a/p[contains(.,'"+section+"')]]/a"));
        click(By.xpath(subSection+"[span[@class='sidebar-normal' and text()='"+subSection+"']]"));
        return this;
    }

    public UIHandler buttonClick(String buttonText){
        click();
    }

    public UIHandler buttonClickById(String buttonId){
        click(By.xpath(".//button[@id='"+buttonId+"']"));
        return this;
    }

    public UIHandler inputClick(String inputName){
        click(By.xpath(".//label[text()='"+inputName+"']/parent::div//input"));
        return this;
    }

    public UIHandler inputClickById(String inputId){
        click(By.xpath(".//input[@id='"+inputId+"']"));
        return this;
    }

    public UIHandler inputSet(String inputName, String value){
        inputClick(inputName);
        sendKeys(By.xpath(".//label[text()='Приоритет']/parent::div//input"),
                value);
        return this;
    }

    public UIHandler inputSetById(String inputId,String value){
        inputClickById(inputId);
        sendKeys(By.xpath(".//input[@id='"+inputId+"']"),
                value);
        return this;
    }

    public UIHandler dropDownListInputSet(String dropDownInputName,
                                          String value){
        inputClick(dropDownInputName);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='"+value+"']]"));
        return this;
    }

    public UIHandler dropDownListValuesContainsValue(String dropDownList, String value){

    }

    public UIHandler radioButtonOn(){

    }

    public UIHandler radioButtonOff(){

    }

    public UIHandler switchCheckbox(String checkboxName,
                                    boolean switchPosition){
        if(switchPosition){

        }

        if(!switchPosition){

        }
    }

    public UIHandler setInputValueFromTableValues(String inputName){

    }

    public UIHandler filterSet(String filterInput, String value){

    }

    public UIHandler settingsOff(String... settingsToOff){

    }

    public UIHandler settingsOn(String... settingsToOn){

    }

    public UIHandler calendarSet(){

    }

    public UIHandler tableRowExists(){

    }

    public UIHandler tableRowNotExists(){

    }

    public UIHandler tableRowCellClick(){

    }

    public UIHandler tableCellHrefClick(){

    }

    public UIHandler tagExists(){

    }

    public UIHandler tagNotExists(){

    }

    public UIHandler deleteFromTableIfExists(){

    }
}
