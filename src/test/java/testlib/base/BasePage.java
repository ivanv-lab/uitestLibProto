package testlib.base;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;

public class BasePage {

    public void open(String url){
        Selenide.open(url);
    }

    public SelenideElement $(By locator){
        return $(locator);
    }

    public void click(By locator){
        $(locator).click(ClickOptions.usingJavaScript());
    }

    public void sendKeys(By locator, String text){
        $(locator).clear();
        $(locator).sendKeys(text);
    }

    public String getText(By locator){
        return $(locator).getValue();
    }

    public String getCurrentUrl(){
        return url();
    }

    public String getTitle(){
        return title();
    }

    private long waiting(int timeout){
        return timeout*1000;
    }

    public void waitForElementVisible(By locator, int timeout) throws InterruptedException {
        $(locator).shouldBe(visible).wait(waiting(timeout));
    }

    public void waitForElementClickable(By locator, int timeout) throws InterruptedException {
        $(locator).shouldBe(clickable).wait(waiting(timeout));
    }

    public void waitForElementPresent(By locator, int timeout) throws InterruptedException {
        $(locator).shouldBe(checked).wait(waiting(timeout));
    }

    public String getAlertText(){
       return $(By.xpath(".//div[contains(@class,'alert')]/span")).getValue();
    }

    public void acceptAlert(){
        $(By.xpath(".//div[contains(@class,'alert')]/button[@id='ok-button']"))
                .click();
    }

    public void dismissAlert(){
        $(By.xpath(".//div[contains(@class,'alert')]/button[@id='no-button']"))
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
