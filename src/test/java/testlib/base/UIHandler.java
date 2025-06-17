package testlib.base;

import com.codeborne.selenide.Selenide;
import testlib.pages.login.LoginPage;
import testlib.utils.handlers.PropertyHandler;

public class UIHandler extends BasePage{

    public UIHandler loginAcui(){
        LoginPage loginPage=new LoginPage();
        loginPage.login(PropertyHandler.getProperty("admin.login"),PropertyHandler.getProperty("admin.password"));
        return this;
    }

    public UIHandler loginLkui(){
        LoginPage loginPage=new LoginPage();
        loginPage.login(PropertyHandler.getProperty("lk.login"),PropertyHandler.getProperty("lk.password"));
        return this;
    }

    public UIHandler login(String address, String login,String password){
        Selenide.open(address);
        LoginPage loginPage=new LoginPage();
        loginPage.login(login,password);
        return this;
    }

    public UIHandler logout(){

    }

    public UIHandler sectionClick(String section){

    }

    public UIHandler subSectionClick(String section, String subSection){

    }

    public UIHandler buttonClick(){

    }

    public UIHandler inputClick(){

    }

    public UIHandler inputSet(){

    }

    public UIHandler dropDownListInputSet(){

    }

    public UIHandler dropDownListValuesContainsValue(String dropDownList, String value){

    }

    public UIHandler radioButtonOn(){

    }

    public UIHandler radioButtonOff(){

    }

    public UIHandler checkBoxOn(){

    }

    public UIHandler checkBoxOff(){

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
