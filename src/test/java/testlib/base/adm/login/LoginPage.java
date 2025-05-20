package testlib.base.adm.login;

import org.openqa.selenium.By;
import testlib.base.adm.BasePage;

public class LoginPage extends BasePage {

    private By loginInput = By.xpath(".//input[@id='login']");
    private By passwordInput = By.xpath(".//input[@id='password']");
    private By submitButton = By.xpath(".//button[@id='button_auth']");
    private By changeLanguageButton = By.xpath(".//div[label[@for='language']]//input");
    private By selectedLanguage=By.xpath(".//div[label[@for='language']]/div/input");

    private By topUserText=By.xpath(".//span[@class='top-user-link']");

    private enum LANGUAGES {
        English(By.xpath("//span[contains(.,'Русский')]")),
        Русский(By.xpath("//span[contains(.,'Русский')]"));

        private By xpath;
        LANGUAGES(By xpath){
            this.xpath=xpath;
        }
        public By getXpath(){return xpath;}
    }

    public void setLogin(String login){
        sendKeys(loginInput,login);
    }

    public void setPassword(String password){
        sendKeys(passwordInput,password);
    }

    public void clickSubmitButton(){
        click(submitButton);
    }

    public String getTopUserText(){
        return getText(topUserText);
    }

    public void changeLanguage(){
        if(getValue(selectedLanguage).equals(LANGUAGES.Русский)) {
            click(changeLanguageButton);
            click(LANGUAGES.Русский.getXpath());
        } else if(getValue(selectedLanguage).equals(LANGUAGES.English)){
            click(changeLanguageButton);
            click(LANGUAGES.English.getXpath());
        }
    }
}
