package testlib.base;

import com.codeborne.selenide.HoverOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import testlib.utils.handlers.PropertyHandler;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;

import static com.codeborne.selenide.Condition.clickable;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UIHandler extends BasePage {

    /**
     * Перечисление с id основных кнопок управления
     * @create - Создать
     * @delete - Удалить
     * @filterOpen - Открыть фильтры
     * @filterClean - Очистить фильтры
     * @save - Сохранить
     * @back - Назад
     */
    public enum ButtonId{
        create("button_create"),
        delete("button_delete"),
        filterOpen("button_show_filter"),
        filterClean("button_clear_filter"),
        save("button_save"),
        back("button_back");

        private String id;

        ButtonId(String id){
            this.id=id;
        }
        public String getId(){return id;}
    }

    /**
     * Производится вход в Админку по базовому логину\паролю
     * @return
     */
    public UIHandler loginAcui() {
        if(!WebDriverRunner.driver().url().equals(PropertyHandler.getProperty("base.URL") + "/acui/login"))
            Selenide.open(PropertyHandler.getProperty("base.URL") + "/acui/login");
        changeLanguageToRus();
        login(PropertyHandler.getProperty("admin.login"), PropertyHandler.getProperty("admin.password"));
        return this;
    }

    /**
     * Производится вход в Админку по введенному логину и паролю
     * @param login    Логин
     * @param password Пароль
     * @return
     */
    public UIHandler loginAcui(String login,String password){
        if(!WebDriverRunner.driver().url().equals(PropertyHandler.getProperty("base.URL") + "/acui/login"))
            Selenide.open(PropertyHandler.getProperty("base.URL") + "/acui/login");
        changeLanguageToRus();
        login(login, password);
        return this;
    }

    /**
     * Производится вход в ЛК пользователя по базовому логину\паролю
     * @return
     */
    public UIHandler loginLkui() {
        if(!WebDriverRunner.driver().url().equals(PropertyHandler.getProperty("base.URL") + "/lkui/login"))
            Selenide.open(PropertyHandler.getProperty("base.URL")+ "/lkui/login");
        changeLanguageToRus();
        login(PropertyHandler.getProperty("lk.login"), PropertyHandler.getProperty("lk.password"));
        return this;
    }

    /**
     * Производится вход в ЛК пользователя по введенному логину и паролю
     * @param login Логин
     * @param password Пароль
     * @return
     */
    public UIHandler loginLkui(String login,String password){
        if(!WebDriverRunner.driver().url().equals(PropertyHandler.getProperty("base.URL") + "/lkui/login"))
            Selenide.open(PropertyHandler.getProperty("base.URL") + "/lkui/login");
        changeLanguageToRus();
        login(login, password);
        return this;
    }

    /**
     * Проверка отображаемого логина пользователя после входа в аккаунт.
     * При входе в аккаунт Админки или ЛК, логин пользователя отображается в правом верхнем углу.
     * По нему можно понять:
     * 1) Вход успешный или нет
     * 2) Под каким пользователем мы зашли
     * @param value Значение ожидаемого логина пользователя
     * @return
     */
    public UIHandler topUserTextEquals(String value){
        assertTrue(getText(By.xpath(".//span[contains(@class,'top-user')]")).equals(value));
        return this;
    }

    /**
     * Вход по указанному адресу с указанным логином и паролем
     * @param address Адрес /login страницы
     * @param login Логин
     * @param password Пароль
     * @return
     */
    public UIHandler login(String address, String login, String password) {
        Selenide.open(address);
        changeLanguageToRus();
        login(login,password);
        return this;
    }

    /**
     * Выход из учетной записи авторизованного пользователя
     * @return
     */
    public UIHandler logout() {
        buttonClickById("button_logout");
        return this;
    }

    /**
     * Клик по указанной секции навигационного меню
     * @param section Наименование секции
     * @return
     */
    public UIHandler sectionClick(String section) {
        click(By.xpath(".//div[contains(@class,'sidebar')]/ul/li[a/p[contains(.,'" + section + "')]]/a"));
        return this;
    }

    /**
     * Клик по указанной секции навигационного меню, далее клик по указанной подсекции меню
     * @param section Секция
     * @param subSection Подсекция
     * @return
     */
    public UIHandler subSectionClick(String section, String subSection) {
        click(By.xpath(section + "[a/p[contains(.,'" + section + "')]]/a"));
        click(By.xpath(subSection + "[span[@class='sidebar-normal' and text()='" + subSection + "']]"));
        return this;
    }

    /**
     * Клик по кнопке с указанным наименованием (текстом)
     * @param buttonText Наименование(текст) кнопки
     * @return
     */
    public UIHandler buttonClick(String buttonText) {
        click(By.xpath(""));
        return this;
    }

    /**
     * Клик по кнопке с указанным id
     * @param buttonId id кнопки
     * @return
     */
    public UIHandler buttonClickById(String buttonId) {
        click(By.xpath(".//button[@id='" + buttonId + "']"));
        return this;
    }

    /**
     * Клик по инпуту(полю ввода) с указанным Наименованием
     * @param inputName Наименование поля
     * @return
     */
    public UIHandler inputClick(String inputName) {
        click(By.xpath(".//label[text()='" + inputName + "']/parent::div//input"));
        return this;
    }

    /**
     * Клик по инпуту(полю ввода) с указанным id
     * @param inputId id поля ввода
     * @return
     */
    public UIHandler inputClickById(String inputId) {
        click(By.xpath(".//input[@id='" + inputId + "']"));
        return this;
    }

    /**
     * Ввод указанного значения в поле ввода
     * @param inputName Наименование поля ввода
     * @param value Значение для ввода
     * @return
     */
    public UIHandler inputSet(String inputName, String value) {
        inputClick(inputName);
        sendKeys(By.xpath(".//label[text()='Приоритет']/parent::div//input"),
                value);
        return this;
    }

    /**
     * Ввод значения в поле ввода указанного региона
     * @param inputName Наименование поля ввода
     * @param value Значение для ввода
     * @param cardName Регион с полем ввода
     * @return
     */
    public UIHandler cardInputSet(String inputName, String value, String cardName) {
        inputClick(inputName);
        sendKeys(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='Приоритет']/parent::div//input"),
                value);
        return this;
    }

    /**
     * Ввод значения в указанный по id инпут(поле ввода)
     * По возможности нужно использовать его
     * @param inputId id поля ввода
     * @param value Значение
     * @return
     */
    public UIHandler inputSetById(String inputId, String value) {
        inputClickById(inputId);
        sendKeys(By.xpath(".//input[@id='" + inputId + "']"),
                value);
        return this;
    }

    /**
     * Выбор значения из выпадающего списка
     * @param dropDownInputName Наименование списка
     * @param value Значение для выбора
     * @return
     */
    public UIHandler dropDownListInputSet(String dropDownInputName,
                                          String value) {
        inputClick(dropDownInputName);
        click(By.xpath(".//li/button[div/span[normalize-space(text())='" + value + "']]"));
        return this;
    }

    /**
     * Проверка поля ввода на содержание значения
     * @param inputName Наименование поля ввода
     * @param value Значение для проверки
     * @return
     */
    public UIHandler inputContains(String inputName, String value){
        if(getText(By.xpath(".//label[text()='"+inputName+"']/parent::div//input"))!=null){
            assertTrue(getText(By.xpath(".//label[text()='"+inputName+"']/parent::div//input")).equals(value));
        } else{
           assertTrue(getValue(By.xpath(".//label[text()='"+inputName+"']/parent::div//input")).equals(value));
        }
        return this;
    }

    /**
     * Проверка поля ввода в указанном регионе на содержание значения
     * @param inputName Наименование поля ввода
     * @param value Значение для проверки
     * @param cardName Наименование региона
     * @return
     */
    public UIHandler cardInputContains(String inputName,String value,String cardName){
        if(getText(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='"+inputName+"']/parent::div//input"))!=null){
            assertTrue(getText(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='"+inputName+"']/parent::div//input")).equals(value));
        } else{
            assertTrue(getValue(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='"+inputName+"']/parent::div//input")).equals(value));
        }
        return this;
    }

    /**
     * Переключение чекбокса
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Позиция чекбокса: true - вкл., false - выкл.
     * @return
     */
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

    /**
     * Проверка положения чекбокса
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Значение для проверки: true - вкл., false - выкл.
     * @return
     */
    public UIHandler switchCheckboxIs(String checkboxName,boolean switchPosition){
        if (switchPosition) {
                assertTrue(find(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                        .exists());
        }

        if (!switchPosition) {
                assertTrue(find(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                        .exists());
        }
        return this;
    }

    /**
     * Проверка положения чекбокса в указанном регионе
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Положение чекбокса: true - вкл., false - выкл.
     * @param cardName Наименование региона
     * @return
     */
    public UIHandler cardSwitchCheckboxIs(String checkboxName,boolean switchPosition, String cardName){
        if (switchPosition) {
            assertTrue(find(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                    .exists());
        }

        if (!switchPosition) {
            assertTrue(find(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .exists());
        }
        return this;
    }

    /**
     * Переключение чекбокса на вкл.
     * @param checkboxName Наименование чекбокса
     * @return
     */
    public UIHandler switchCheckbox(String checkboxName){
        if (find(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                .exists())
            click(By.xpath(".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"));
        return this;
    }

    /**
     * Переключение чекбокса в указанном регионе
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Положение чекбокса: true - вкл., false - выкл.
     * @param cardName Наименование региона
     * @return
     */
    public UIHandler cardSwitchCheckbox(String checkboxName,
                                    boolean switchPosition, String cardName) {
        if (switchPosition) {
            if (find(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and not(contains(@class,'checked'))]"));
        }

        if (!switchPosition) {
            if (find(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"))
                    .exists())
                click(By.xpath(".//h4[text()='"+cardName+"']/parent::div/parent::div/parent::div//"+".//label[text()='" + checkboxName + "']/parent::div//div[contains(@class,'md-switch-container')]//input[@type='checkbox']/ancestor::div[4][contains(@class,'md-switch') and (contains(@class,'checked'))]"));
        }
        return this;
    }

    /**
     * Ввод в указанное поле ввода случайного значения из таблицы, находящейся на странице.
     * Для этого нужно, чтобы Наименование поля ввода совпадало с одним из столбцов таблицы.
     * Например, поле ввода "Адрес" и в таблице должен быть столбец с заголовком "Адрес".
     * В таком случае будет взято первое значение из таблицы.
     * @param inputName Наименование поля ввода и Наименование столбца таблицы
     * @return
     */
    public UIHandler setInputValueFromTableValues(String inputName) {
        return this;
    }

    /**
     * Ввод фильтров
     * @param filterInput Наименование поля ввода в фильтрах
     * @param value Значение фильтра
     * @return
     */
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

    /**
     * Принимает параметры настроек таблицы, которые будут выключены
     * @param settingsToOff Параметры настроек таблицы для выключения
     * @return
     */
    public UIHandler settingsOff(String... settingsToOff) {
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for (String setting : settingsToOff) {
            if ($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[contains(@class,'checked')]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[contains(@class,'checked')]//input"));
        }
        return this;
    }

    /**
     * Принимает параметры настроек таблицы, которые будут включены
     * @param settingsToOn Параметры настроек таблицы для включения
     * @return
     */
    public UIHandler settingsOn(String... settingsToOn) {
        click(By.xpath(".//button[div//i[text()='settings']]"));
        for (String setting : settingsToOn) {
            if ($(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[not(contains(@class,'checked'))]//input"))
                    .exists())
                click(By.xpath(".//div[@class='open dropdown']/ul/li[div/label[text()='" + setting + "']]/div[not(contains(@class,'checked'))]//input"));
        }
        return this;
    }

    /**
     * Выставляет необходимую дату в поле календаря
     * @param calendarInputName Наименование поля календаря
     * @param year Год в формате "2025"
     * @param month Месяц в формате "Май"
     * @param date Дата в формате "12"
     * @return
     */
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

    /**
     * Выставляет текущую дату в поле календаря
     * @param calendarInputName Наименование поля календаря
     * @return
     */
    public UIHandler calendarSet(String calendarInputName) {
        inputClick(calendarInputName);
        click(By.xpath(".//div[@class='vdatetime-popup__year']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-year-picker__item') and normalize-space(text())='" + LocalDateTime.now().getYear() + "']"));
        click(By.xpath(".//div[@class='vdatetime-popup__date']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-month-picker__item') and normalize-space(text())='" + Month.of(LocalDateTime.now().getMonthValue()) + "']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-calendar__month__day')]//span[normalize-space(text())='" + LocalDateTime.now().getDayOfMonth() + "']"));
        click(By.xpath(".//div[contains(@class,'hours')]/div[contains(@class,'selected')]"));
        click(By.xpath(".//div[contains(@class,'minutes')]/div[contains(@class,'selected')]"));
        return this;
    }

    /**
     * Выставляет дату с отклонением в указанный параметр от текущей даты в поле календаря.
     * Например, вызвав метод с параметром -3, будет выставлена дата на 3 дня раньше текущей
     * @param calendarInputName Наименование поля календаря
     * @param relationDay Отклонение от текущей даты
     * @return
     */
    public UIHandler calendarSet(String calendarInputName, int relationDay) {
        inputClick(calendarInputName);
        click(By.xpath(".//div[@class='vdatetime-popup__year']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-year-picker__item') and normalize-space(text())='" + LocalDateTime.now().getYear() + "']"));
        click(By.xpath(".//div[@class='vdatetime-popup__date']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-month-picker__item') and normalize-space(text())='" + Month.of(LocalDateTime.now().getMonthValue()) + "']"));
        click(By.xpath(".//div[contains(@class,'vdatetime-calendar__month__day')]//span[normalize-space(text())='" + LocalDateTime.now().plusDays(relationDay) + "']"));
        click(By.xpath(".//div[contains(@class,'hours')]/div[contains(@class,'selected')]"));
        click(By.xpath(".//div[contains(@class,'minutes')]/div[contains(@class,'selected')]"));
        return this;
    }

    /**
     * Проверка на содержание значения строки в таблице
     * @param text Текст проверки
     * @return
     */
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

    /**
     * Отрицательная проверка на содержание значения строки в таблице
     * @param text
     * @return
     */
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

    /**
     * Клик по указанным строке и столбцу в таблице в таблице
     * @param rowText Текст строки
     * @param cellNumber Номер столбца
     * @return
     */
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

    /**
     * Клик по первой найденной ссылке в строке таблицы
     * @param text Текст строки таблицы
     * @return
     */
    public UIHandler tableCellHrefClick(String text) {
        click(By.xpath(".//tbody/tr[td//*[normalize-space(text())='" + text + "']]/td//a"));
        return this;
    }

    /**
     * Проверка существования тэга(например, тэга фильтра) с указанным наименованием и значением
     * @param tagName Наименование тэга
     * @param tagValue Значение тэга
     * @return
     */
    public UIHandler tagExists(String tagName, String tagValue) {
        assertTrue(find(By.xpath(".//div[text()='" + tagName + ":']/parent::div/div[normalize-space((text())='" + tagValue + "')]//i"))
                .exists());

        return this;
    }

    /**
     * Отрицательная проверка существования тэга(например, тэга фильтра) с указанным наименованием и значением
     * @param tagName Наименование тэга
     * @param tagValue Значение тэга
     * @return
     */
    public UIHandler tagNotExists(String tagName, String tagValue) {
        assertFalse(find(By.xpath(".//div[text()='" + tagName + ":']/parent::div/div[normalize-space((text())='" + tagValue + "')]//i"))
                .exists());

        return this;
    }

    /**
     * Удаление записи из таблицы, если такая существует
     * @param rowValue Текст строки в таблице для удаления записи
     * @return
     */
    public UIHandler deleteFromTableIfExists(String rowValue) {
        if (!$(By.xpath(".//table/tbody/tr")).exists()) {

            tableRowExists(rowValue);
            tableRowCellClick(rowValue, 6);
            buttonClickById("button_delete");
            confirmDelete();
        }

        return this;
    }

    /**
     * Проверка соответствия текста ошибки с указанным текстом
     * @param value Должный текст ошибки
     * @return
     */
    public UIHandler alertTextEquals(String value){
        assertTrue(getAlertText().equals(value));
        return this;
    }

    /**
     * Согласие на удаление записи.
     * Кликает на кнопку "ОК", "Да" по ее Id
     * @return
     */
    public UIHandler confirmDelete(){
        click(By.xpath(".//button[contains(@class,'button_confirm')]"));
        return this;
    }
}
