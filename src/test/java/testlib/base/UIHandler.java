package testlib.base;

import com.codeborne.selenide.HoverOptions;
import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import testlib.utils.handlers.PropertyHandler;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UIHandler extends BasePage {

    public UIHandler loginAcui() {
        Selenide.open(PropertyHandler.getProperty("base.URL" + "/acui/login"));
        changeLanguageToRus();
        login(PropertyHandler.getProperty("admin.login"), PropertyHandler.getProperty("admin.password"));
        return this;
    }

    public UIHandler loginAcui(String login,String password){
        Selenide.open(PropertyHandler.getProperty("base.URL" + "/acui/login"));
        changeLanguageToRus();
        login(login, password);
        return this;
    }

    public UIHandler loginLkui() {
        Selenide.open(PropertyHandler.getProperty("base.URL" + "/lkui/login"));
        changeLanguageToRus();
        login(PropertyHandler.getProperty("lk.login"), PropertyHandler.getProperty("lk.password"));
        return this;
    }

    public UIHandler loginLkui(String login,String password){
        Selenide.open(PropertyHandler.getProperty("base.URL" + "/lkui/login"));
        changeLanguageToRus();
        login(login, password);
        return this;
    }

    public UIHandler topUserTextEquals(String value){
        assertTrue(getText(By.xpath(".//span[contains(@class,'top-user')]")).equals(value));
        return this;
    }

    public UIHandler login(String address, String login, String password) {
        Selenide.open(address);
        changeLanguageToRus();
        login(login,password);
        return this;
    }

    public UIHandler logout() {
        buttonClickById("button_logout");
        return this;
    }

    public UIHandler sectionClick(String section) {
        click(By.xpath(".//div[contains(@class,'sidebar')]/ul/li[a/p[contains(.,'" + section + "')]]/a"));
        return this;
    }

    public UIHandler subSectionClick(String section, String subSection) {
        click(By.xpath(section + "[a/p[contains(.,'" + section + "')]]/a"));
        click(By.xpath(subSection + "[span[@class='sidebar-normal' and text()='" + subSection + "']]"));
        return this;
    }

    public UIHandler buttonClick(String buttonText) {
        click(By.xpath(""));
        return this;
    }

    public UIHandler buttonClickById(String buttonId) {
        click(By.xpath(".//button[@id='" + buttonId + "']"));
        return this;
    }

    public UIHandler inputClick(String inputName) {
        click(By.xpath(".//label[text()='" + inputName + "']/parent::div//input"));
        return this;
    }

    public UIHandler inputClickById(String inputId) {
        click(By.xpath(".//input[@id='" + inputId + "']"));
        return this;
    }

    public UIHandler inputSet(String inputName, String value) {
        inputClick(inputName);
        sendKeys(By.xpath(".//label[text()='Приоритет']/parent::div//input"),
                value);
        return this;
    }

    public UIHandler inputSetById(String inputId, String value) {
        inputClickById(inputId);
        sendKeys(By.xpath(".//input[@id='" + inputId + "']"),
                value);
        return this;
    }

    public UIHandler dropDownListInputSet(String dropDownInputName,
                                          String value) {
        inputClick(dropDownInputName);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='" + value + "']]"));
        return this;
    }

    public UIHandler switchCheckbox(String checkboxName,
                                    boolean switchPosition) {
        if (switchPosition) {
            if (find(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"));
        }

        if (!switchPosition) {
            if (find(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"));
        }
        return this;
    }

    public UIHandler setInputValueFromTableValues(String inputName) {
        return this;
    }

    public UIHandler filterSet(String filterInput, String value) {
        click(By.xpath(".//label[text()='" + filterInput + "']/parent::div//input"));
        if ($(By.xpath(".//li/button[div/span[normalize-space(text())='" + value + "']]"))
                .exists())
            click(By.xpath(".//li/button[div/span[normalize-space(text())='" + value + "']]"));
        else
            sendKeys(By.xpath(".//label[text()='" + filterInput + "']/parent::div//input"), value);

        buttonClickById("button_apply_filter");
        return this;
    }

    public UIHandler settingsOff(String... settingsToOff) {
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for (String setting : settingsToOff) {
            if ($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[contains(@class,'checked')]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[contains(@class,'checked')]//input"));
        }
        return this;
    }

    public UIHandler settingsOn(String... settingsToOn) {
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for (String setting : settingsToOn) {
            if ($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[not(contains(@class,'checked'))]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[not(contains(@class,'checked'))]//input"));
        }
        return this;
    }

    public UIHandler calendarSet(String calendarInputName, String year,
                                 String month, String date) {
        inputClick(calendarInputName);
        click(By.xpath(".//div[@class='vdatetime-popup__year']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-year-picker__item') and normalize-space(text())='" + year + "']"));
        click(By.xpath(".//div[@class='vdatetime-popup__date']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-month-picker__item') and normalize-space(text())='" + month + "']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-calendar__month__day')]//span[normalize-space(text())='" + date + "']"));
        click(By.xpath(".//div[contains(@class,'hours')]/div[contains(@class,'selected')]"));
        click(By.xpath(".//div[contains(@class,'minutes')]/div[contains(@class,'selected')]"));
        return this;
    }

    public UIHandler tableRowExists(String text) {
        int tableSize = findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection = "";
        for (int i = 1; i <= tableSize; i++) {
            if ($(By.xpath(".//table/tbody/tr[" + i + "]")).exists()) {
                tableRowCollection = getText(By.xpath(".//table/tbody/tr[" + i + "]"));
                assertTrue(tableRowCollection.contains(text));
            }
        }
        return this;
    }

    public UIHandler tableRowNotExists(String text) {
        int tableSize = findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection = "";
        for (int i = 1; i <= tableSize; i++) {
            if ($(By.xpath(".//table/tbody/tr[" + i + "]")).exists()) {
                tableRowCollection = getText(By.xpath(".//table/tbody/tr[" + i + "]"));
                assertFalse(tableRowCollection.contains(text));
            }
        }
        return this;
    }

    public UIHandler tableRowCellClick(String rowText, int cellNumber) {
        int tableSize = findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection = "";
        for (int i = 1; i <= tableSize; i++) {
            tableRowCollection = getText(By.xpath(".//table/tbody/tr[" + i + "]"));
            if (tableRowCollection.contains(rowText)) {
                click(By.xpath(".//table/tbody/tr[" + i + "]/td[" + cellNumber + "]"));
            }
        }

        return this;
    }

    public UIHandler tableCellHrefClick(String text) {
        click(By.xpath(".//tbody/tr[td//*[normalize-space(text())='" + text + "']]/td//a"));
        return this;
    }

    public UIHandler tagExists(String tagName, String tagValue) {
        assertTrue(find(By.xpath(".//div[text()='" + tagName + ":']/parent::div/div[normalize-space((text())='" + tagValue + "')]//i"))
                .exists());

        return this;
    }

    public UIHandler tagNotExists(String tagName, String tagValue) {
        assertFalse(find(By.xpath(".//div[text()='" + tagName + ":']/parent::div/div[normalize-space((text())='" + tagValue + "')]//i"))
                .exists());

        return this;
    }

    public UIHandler deleteFromTableIfExists(String rowValue) {
        if (!$(By.xpath(".//table/tbody/tr")).exists()) {

            tableRowExists(rowValue);
            tableRowCellClick(rowValue, 6);
            buttonClickById("button_delete");
            confirmDelete();
        }

        return this;
    }

    public UIHandler alertTextEquals(String value){
        assertTrue(getAlertText().equals(value));
        return this;
    }

    public UIHandler confirmDelete(){

        return this;
    }
}
