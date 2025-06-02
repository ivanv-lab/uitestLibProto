package testlib.pages.adm.settings.clients;

import org.openqa.selenium.By;
import testlib.base.BasePage;

import static com.codeborne.selenide.Condition.visible;

public class ClientCreatingPage extends BasePage {

    By nameInput=By.xpath(".//input[@id='name']");

    By statusStatusOn=By.xpath(".//div[div/div[contains(@class,'md-switch') and contains(@class,'md-checked')]]/label[contains(.,'Статус')]/parent::div//input");
    By statusStatusOff=By.xpath(".//div[div/div[contains(@class,'md-switch') and not(contains(@class,'md-checked'))]]/label[contains(.,'Статус')]/parent::div//input");

    By avanceStatusOn=By.xpath(".//div[div/div[contains(@class,'md-switch') and contains(@class,'md-checked')]]/label[contains(.,'Авансовая схема взаиморасчетов')]/parent::div//input");
    By avanceStatusOff=By.xpath(".//div[div/div[contains(@class,'md-switch') and not(contains(@class,'md-checked'))]]/label[contains(.,'Авансовая схема взаиморасчетов')]/parent::div//input");

    By backButton=By.xpath(".//button[@id='button_back']");
    By saveButton=By.xpath(".//button[@id='button_save']");

    public void setName(String name){
        sendKeys(nameInput,name);
    }

    public void statusOn(){
        if(find(statusStatusOff).is(visible)){
            click(statusStatusOn);
        }
    }

    public void statusOff(){
        if(find(statusStatusOn).is(visible)){
            click(statusStatusOff);
        }
    }

    public void avanceOn(){
        if(find(avanceStatusOff).is(visible)){
            click(avanceStatusOn);
        }
    }

    public void avanceOff(){
        if(find(avanceStatusOn).is(visible)){
            click(avanceStatusOff);
        }
    }

    public void transportSettingOn(String transport, int buttonNumber){

    }

    public void transportSettingOff(String transport, int buttonNumber){

    }

    public void backToList(){
        click(backButton);
    }

    public void save(){
        click(saveButton);
    }
}
