package testlib.base;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<String> findCollection(By locator){

        waitForElementVisible(locator,10);
        return $$(locator).shouldHave(CollectionCondition
                .sizeGreaterThan(0))
                .asFixedIterable()
                .stream().map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void click(By locator) {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        find(locator).shouldBe(enabled).click();
    }

    public void sendKeys(By locator, String text) {
        waitForElementVisible(locator, 10);
        waitForElementClickable(locator, 10);
        find(locator).sendKeys(Keys.CONTROL+"A");
        find(locator).sendKeys(Keys.BACK_SPACE);
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
        $(locator).shouldBe(visible,Duration.ofSeconds(timeout));
    }

    public void waitForElementClickable(By locator, int timeout) {
        $(locator).shouldBe(clickable,Duration.ofSeconds(timeout));
    }

    public void waitForElementPresent(By locator, int timeout) {
        $(locator).shouldBe(checked,Duration.ofSeconds(timeout));
    }

    public void waitTitle(By titleLocator, String expectedTitle, String hrefTitle){
        $(titleLocator).shouldBe(visible).shouldHave(text(expectedTitle));
        Selenide.Wait().until(url->url.getCurrentUrl().contains(hrefTitle));
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

    public void setCalendar(By locator, String year, String month, String date){
        click(locator);
        click(By.xpath(".//div[@class='vdatetime-popup__year']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-year-picker__item') and normalize-space(text())='"+year+"']"));
        click(By.xpath(".//div[@class='vdatetime-popup__date']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-month-picker__item') and normalize-space(text())='"+month+"']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-calendar__month__day')]//span[normalize-space(text())='"+date+"']"));
        click(By.xpath(".//div[contains(@class,'hours')]/div[contains(@class,'selected')]"));
        click(By.xpath(".//div[contains(@class,'minutes')]/div[contains(@class,'selected')]"));
    }
}
