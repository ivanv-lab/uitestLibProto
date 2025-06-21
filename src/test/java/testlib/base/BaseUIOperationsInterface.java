package testlib.base;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.By;

import java.util.List;

public interface BaseUIOperationsInterface {

    SelenideElement find(By locator);

    List<String> findCollection(By locator);

    void click(By locator);

    void sendKeys(By locator, String text);

    String getText(By locator);

    String getValue(By locator);

    void login(String login, String password);

    void changeLanguageToRus();

    String getAlertText();

    void openSidebar();
}
