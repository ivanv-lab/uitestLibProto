package testlib.base.adm;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;

public abstract class BasePage {

    public void open(String url){
        Selenide.open(url);
    }

    public SelenideElement find(By locator){
        return $(locator);
    }

    public void click(By locator){
        $(locator).click(ClickOptions.usingJavaScript());
    }

    public void sendKeys(By locator, String text){
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getText(By locator){
        return find(locator).getText();
    }

    public String getValue(By locator){return find(locator).getValue();}

    public String getCurrentUrl(){
        return url();
    }

    public String getTitle(){
        return title();
    }

    public void waitForElementVisible(By locator, int timeout) throws InterruptedException {
        find(locator).shouldBe(visible,Duration.ofSeconds(timeout));
    }

    public void waitForElementClickable(By locator, int timeout) throws InterruptedException {
        find(locator).shouldBe(clickable,Duration.ofSeconds(timeout));
    }

    public void waitForElementPresent(By locator, int timeout) throws InterruptedException {
        find(locator).shouldBe(checked,Duration.ofSeconds(timeout));
    }

    public String getAlertText(){
       String alertText= find(By.xpath(".//div[contains(@class,'alert')]/span")).getText();
       return alertText;
    }

    public void acceptAlert(){
        find(By.xpath(".//div[contains(@class,'alert')]/button[@id='ok-button']"))
                .click();
    }

    public void dismissAlert(){
        find(By.xpath(".//div[contains(@class,'alert')]/button[@id='no-button']"))
                .click();
    }

    public void setCookie(String name, String value){
        WebDriverRunner.getWebDriver().manage()
                .addCookie(new Cookie(name,value));
    }

    public String getCookie(String name){
        return  WebDriverRunner.getWebDriver()
                .manage().getCookieNamed(name).getValue();
    }

    public void deleteCookie(String name){
        String value=getCookie(name);
        WebDriverRunner.getWebDriver().manage()
                .deleteCookie(new Cookie(name,value));
    }
}
