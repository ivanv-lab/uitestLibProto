package testlib.base;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

import static com.codeborne.selenide.Selenide.$;

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
        buttonClickById("button_logout");
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
        click(By.xpath(""));
        return this;
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
            if(find(By.xpath(".//label[text()='"+checkboxName+"']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//label[text()='"+checkboxName+"']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"));
        }

        if(!switchPosition){
            if(find(By.xpath(".//label[text()='"+checkboxName+"']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//label[text()='"+checkboxName+"']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"));
        }
        return this;
    }

    public UIHandler setInputValueFromTableValues(String inputName){
        return this;
    }

    public UIHandler filterSet(String filterInput, String value){
        click(By.xpath(".//label[text()='"+filterInput+"']/parent::div//input"));
        if($(By.xpath(".//li/button[div/span[normalize-space(text())='"+value+"']]"))
                .exists())
            click(By.xpath(".//li/button[div/span[normalize-space(text())='"+value+"']]"));
        else
            sendKeys(By.xpath(".//label[text()='"+filterInput+"']/parent::div//input"),value);

        buttonClickById("button_apply_filter");
        return this;
    }

    public UIHandler settingsOff(String... settingsToOff){
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for(String setting:settingsToOff){
            if($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='"+setting+"']]/div[contains(@class,'checked')]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='"+setting+"']]/div[contains(@class,'checked')]//input"));
        }
        return this;
    }

    public UIHandler settingsOn(String... settingsToOn){
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for(String setting:settingsToOn){
            if($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='"+setting+"']]/div[not(contains(@class,'checked'))]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='"+setting+"']]/div[not(contains(@class,'checked'))]//input"));
        }
        return this;
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
