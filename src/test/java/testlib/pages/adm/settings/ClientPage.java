package testlib.pages.adm.settings;

import org.openqa.selenium.By;
import testlib.base.BasePage;
import testlib.base.TableWorker;

import java.lang.management.ManagementFactory;

import static com.codeborne.selenide.Condition.visible;

public class ClientPage extends BasePage {

    By createClientButton=By.xpath(".//button[@id='button_create']");
    By filterOpenButton=By.xpath(".//button[@id='button_show_filter']");
    By clientNameInput=By.xpath(".//input[@id='name']");
    By activeStatusInput=By.xpath(".//input[@id='status']");
    By avanceStatusInput=By.xpath(".//input[@id='prepaid']");


    public void createClient(){
        click(createClientButton);
    }

    public void openFilters(){
        click(filterOpenButton);
    }

    public boolean checkClientExists(String clientName){
        TableWorker tableWorker=new TableWorker();

        return tableWorker.tableRowExists(clientName);
    }

    public void setClientNameInput(String clientName){
        sendKeys(clientNameInput,clientName);
    }

    public void activeClientStatusOn(){
        if(find(By.xpath(".//input[@id='status']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(activeStatusInput);
        }
    }

    public void activeClientStatusOff(){
        if(find(By.xpath(".//input[@id='status']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                .is(visible)){

            click(activeStatusInput);
        }
    }

    public void avanceStatusOn(){
        if(find(By.xpath(".//input[@id='prepaid']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(avanceStatusInput);
        }
    }

    public void avanceStatusOff(){
        if(find(By.xpath(".//input[@id='prepaid']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                .is(visible)){

            click(avanceStatusInput);
        }
    }

    public void setClientTransportOptionOn(String transport, int checkBoxNumber){

        transport=transport.toLowerCase();
        transport=transport.replace(' ','_');

        if(find(By.xpath(".//input[contains(@id,'transport."+transport+"')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .is(visible)){

            click(By.xpath(".//input[@id='transport."+transport+"']"));
        }
    }
}
