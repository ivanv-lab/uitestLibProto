package testlib.pages.login;

import org.openqa.selenium.By;
import testlib.base.BasePage;

public class LoginPage extends BasePage {

    By loginInput=By.xpath(".//input[@id='login']");
    By passwordInput=By.xpath(".//input[@id='password']");
    By submitButton=By.xpath(".//button[@id='button_auth']");
    By changeLanguageButton=By.xpath(".//div[label[@for='language']]//input");
    By currentLang=By.xpath(".//div[select[@id='language']]/input");
    By topUserText=By.xpath(".//span[contains(@class,'top-user')]");

    public void setLogin(String login){
        sendKeys(loginInput,login);
    }

    public void setPassword(String password){
        sendKeys(passwordInput,password);
    }

    public void clickSubmitButton(){
        click(submitButton);
    }

    public void login(String login, String password){
        setLogin(login);
        setPassword(password);
        clickSubmitButton();
    }

    public void changeLanguage(){
        click(changeLanguageButton);
        if(getValue(currentLang).equals("Русский")) {
            click(By.xpath(".//button[div/span[contains(.,'English')]]"));
        } else if(getValue(currentLang).equals("English")) {
            click(By.xpath(".//button[div/span[contains(.,'Русский')]]"));
        }
    }

    public String returnTopUserText(){
        return getText(topUserText);
    }
}
