package testlib.base;

import com.codeborne.selenide.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import testlib.utils.handlers.PropertyHandler;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static testlib.utils.RetryUtils.retry;

public abstract class BasePage{
    
    private final int defaultTimeout=Integer.parseInt(PropertyHandler
            .getProperty("timeout.default"));

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
        retry(()->{
            find(locator).shouldBe(enabled).click();
            return null;
        });
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
        retry(()->{
            for(WebElementCondition condition:conditions){
            $(locator).shouldBe(condition,Duration.ofSeconds(timeout));
        }
            return null;
        });
    }

    public void login(String login,String password){
        sendKeys(By.xpath(".//input[@id='login']"),login);
        sendKeys(By.xpath(".//input[@id='password']"),password);
        click(By.xpath(".//button[@id='button_auth']"));
    }

    public void changeLanguageToRus(){
        click(By.xpath(".//div[label[@for='language']]//input"));
        click(By.xpath(".//button[div/span[contains(.,'Русский')]]"));
    }

    public String getAlertText() {
        By alertLocator=By.xpath(".//div[contains(@class,'alert')]/span");
        waitForElementConditions(alertLocator,defaultTimeout,visible,clickable);
        return find(alertLocator).getText();
    }

    public void openSidebar(){
        find(By.xpath(".//div[contains(@class,'sidebar')]")).hover();
        click(By.xpath(".//button[@id='minimizeSidebar']"));
    }
}
