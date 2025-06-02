package testlib.base;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public abstract class BasePage extends BaseTest {

    public void open(String url){
        Selenide.open(url);
    }

    public SelenideElement find(By locator){
        waitForElementVisible(locator,10);
        return $(locator);
    }

    public List<SelenideElement> findCollection(By locator){

        List<SelenideElement> collection=new ArrayList<>();
        waitForElementVisible(locator,10);
        return collection = (List<SelenideElement>) $$(locator);
    }

    public void click(By locator) {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        find(locator).click(ClickOptions.usingJavaScript());
    }

    public void sendKeys(By locator, String text) {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getText(By locator)  {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        return find(locator).getText();
    }

    public String getValue(By locator) {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        return find(locator).getValue();
    }

    public String getCurrentUrl(){
        return url();
    }

    public String getTitle(){
        return title();
    }

    public void waitForElementVisible(By locator, int timeout) {
        find(locator).shouldBe(visible,Duration.ofSeconds(timeout));
    }

    public void waitForElementClickable(By locator, int timeout) {
        find(locator).shouldBe(clickable,Duration.ofSeconds(timeout));
    }

    public void waitForElementPresent(By locator, int timeout) {
        find(locator).shouldBe(checked,Duration.ofSeconds(timeout));
    }

    public String getAlertText() {
        waitForElementVisible(By.xpath(".//div[contains(@class,'alert')]/span"), 10);
        waitForElementClickable(By.xpath(".//div[contains(@class,'alert')]/span"), 10);
        String alertText = find(By.xpath(".//div[contains(@class,'alert')]/span")).getText();
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
}
