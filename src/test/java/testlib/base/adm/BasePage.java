package testlib.base.adm;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.url;

public abstract class BasePage extends BaseTest {

    public void open(String url){
        Selenide.open(url);
    }

    public SelenideElement find(By locator){
        return $(locator);
    }

    public void click(By locator) {
        waitForElementVisible(locator, 5);
        waitForElementClickable(locator, 5);
        $(locator).click(ClickOptions.usingJavaScript());
    }

    public void sendKeys(By locator, String text) {
        waitForElementVisible(locator, 5);
        waitForElementClickable(locator, 5);
        find(locator).clear();
        find(locator).sendKeys(text);
    }

    public String getText(By locator)  {
        waitForElementVisible(locator, 5);
        waitForElementClickable(locator, 5);
        return find(locator).getText();
    }

    public String getValue(By locator) {
        waitForElementVisible(locator, 5);
        waitForElementClickable(locator, 5);
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
        waitForElementVisible(By.xpath(".//div[contains(@class,'alert')]/span"), 5);
        waitForElementClickable(By.xpath(".//div[contains(@class,'alert')]/span"), 5);
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
