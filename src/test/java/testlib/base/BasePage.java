package testlib.base;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;

public abstract class BasePage extends BaseTest {
    
    private final int defaultTimeout=10;

    public SelenideElement find(By locator){
        waitForElementConditions(locator,defaultTimeout,visible);
        return $(locator);
    }

    public List<String> findCollection(By locator){
        waitForElementConditions(locator,defaultTimeout,visible,clickable,interactable);
        return $$(locator)
                .asFixedIterable()
                .stream().map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public void click(By locator) {
        waitForElementConditions(locator,defaultTimeout,visible,clickable);
        find(locator).shouldBe(enabled).click();
    }

    public void sendKeys(By locator, String text) {
        waitForElementConditions(locator,defaultTimeout,visible,clickable,interactable);
        find(locator).sendKeys(Keys.CONTROL+"A");
        find(locator).sendKeys(Keys.BACK_SPACE);
        find(locator).sendKeys(text);
    }

    public String getText(By locator) {
        waitForElementConditions(locator,defaultTimeout,visible,clickable);
        return find(locator).getText();
    }

    public String getValue(By locator) {
        waitForElementConditions(locator,defaultTimeout,visible,clickable);
        return find(locator).getValue();
    }

    private void waitForElementConditions(By locator,int timeout,WebElementCondition... conditions){
        for(WebElementCondition condition:conditions){
            $(locator).shouldBe(condition,Duration.ofSeconds(timeout));
        }
    }

    private boolean waitForPageLoad(){
//        WebDriverWait wait=new WebDriverWait(getWebDriver(),Duration.ofSeconds(defaultTimeout));
//
//        ExpectedCondition<Boolean> pageLoadCondition=new
//                ExpectedCondition<Boolean>() {
//                    @Override
//                    public Boolean apply(WebDriver driver) {
//                        return ((JavascriptExecutor) driver).executeScript("return document.readyState")
//                                .equals("complete");
//                    }
//                };
//        return wait.until(pageLoadCondition);
        return Selenide.executeJavaScript("return document.readyState")
                .equals("complete");
    }

    public void waitTitle(By titleLocator, String expectedTitle, String hrefTitle){
        find(titleLocator).shouldBe(visible).shouldHave(text(expectedTitle));
        Selenide.Wait().until(url->url.getCurrentUrl().contains(hrefTitle));
        waitForPageLoad();
    }

    public String getAlertText() {
        By alertLocator=By.xpath(".//div[contains(@class,'alert')]/span");
        waitForElementConditions(alertLocator,defaultTimeout,visible,clickable);
        return find(alertLocator).getText();
    }

    public void confirmDelete(){
        click(By.xpath(".//button[contains(@class,'button_confirm')]"));
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

    public void switchCheckbox(By locator,boolean switchPosition){
        if(switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .is(exist))
                click(locator);

        if(!switchPosition)
            if(find(By.xpath(".//input[contains(@id,'md-switch')]/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                    .is(exist))
                click(locator);
    }
}
