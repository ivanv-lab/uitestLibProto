package testlib.base;

import com.codeborne.selenide.*;
import io.qameta.allure.Allure;
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
       return Allure.step("Поиск элемента с локатором: "+locator,()-> {
            waitForElementConditions(locator, defaultTimeout, visible);
            return $(locator);
        });
    }

    public List<String> findCollection(By locator){
        return Allure.step("Поиск коллекции элементов",()-> {
            waitForElementConditions(locator, defaultTimeout, visible, clickable, interactable);
            return $$(locator)
                    .asFixedIterable()
                    .stream().map(SelenideElement::getText)
                    .collect(Collectors.toList());
        });
    }

    public void click(By locator) {
        Allure.step("Клик по элементу с локатором: "+locator,()-> {
            waitForElementConditions(locator, defaultTimeout, visible, clickable);
            retry(() -> {
                find(locator).shouldBe(enabled).click();
                return null;
            });
        });
    }

    public void sendKeys(By locator, String text) {
        Allure.step("Ввод текста "+text+" в элемент с локатором "+locator,()-> {
            waitForElementConditions(locator, defaultTimeout, visible, clickable, interactable);
            find(locator).sendKeys(Keys.CONTROL + "A");
            find(locator).sendKeys(Keys.BACK_SPACE);
            find(locator).sendKeys(text);
        });
    }

    public String getText(By locator) {
        return Allure.step(String.format("Получение текста из элемента с локатором "+locator),()-> {
            waitForElementConditions(locator, defaultTimeout, visible, clickable);
            return find(locator).getText();
        });
    }

    public String getValue(By locator) {
        return Allure.step(String.format("Получение значения из элемента с локатором "+locator),()-> {
            waitForElementConditions(locator, defaultTimeout, visible, clickable);
            return find(locator).getValue();
        });
    }

    private void waitForElementConditions(By locator,int timeout,WebElementCondition... conditions){
        retry(()->{
            for(WebElementCondition condition:conditions){
                Allure.step(String.format("Проверка элемента на условия: "+condition),()-> {
                    $(locator).shouldBe(condition, Duration.ofSeconds(timeout));
                });
        }
            return null;
        });
    }

    public void login(String login,String password){
        Allure.step(String.format("Логин на странице авторизации с логином "+login+" и паролем "+password),()-> {
            sendKeys(By.xpath(".//input[@id='login']"), login);
            sendKeys(By.xpath(".//input[@id='password']"), password);
            click(By.xpath(".//button[@id='button_auth']"));
        });
    }

    public void changeLanguageToRus(){
        Allure.step("Изменение языка интерфейса на русский",()-> {
            click(By.xpath(".//div[label[@for='language']]//input"));
            click(By.xpath(".//button[div/span[contains(.,'Русский')]]"));
        });
    }

    public String getAlertText() {
       return Allure.step("Получение текста уведомления или ошибки",()-> {
            By alertLocator = By.xpath(".//div[contains(@class,'alert')]/span");
            return find(alertLocator).getText();
        });
    }

    public void openSidebar(){
        Allure.step("Открытие сайдбара (навигационного меню)",()-> {
            find(By.xpath(".//div[contains(@class,'sidebar')]")).hover();
            click(By.xpath(".//button[@id='minimizeSidebar']"));
        });
    }
}
