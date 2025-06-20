package testlib.base;

public interface UIOperationsInterface extends BaseUIOperationsInterface{

    /**
     * Производится вход в Админку по базовому логину\паролю
     * @return
     */
    public UIOperationsInterface loginAcui();

    /**
     * Производится вход в Админку по введенному логину и паролю
     * @param login    Логин
     * @param password Пароль
     * @return
     */
    public UIOperationsInterface loginAcui(String login,String password);

    /**
     * Производится вход в ЛК пользователя по базовому логину\паролю
     * @return
     */
    public UIOperationsInterface loginLkui();

    /**
     * Производится вход в ЛК пользователя по введенному логину и паролю
     * @param login Логин
     * @param password Пароль
     * @return
     */
    public UIOperationsInterface loginLkui(String login,String password);

    /**
     * Проверка отображаемого логина пользователя после входа в аккаунт.
     * При входе в аккаунт Админки или ЛК, логин пользователя отображается в правом верхнем углу.
     * По нему можно понять:
     * 1) Вход успешный или нет
     * 2) Под каким пользователем мы зашли
     * @param value Значение ожидаемого логина пользователя
     * @return
     */
    public UIOperationsInterface topUserTextEquals(String value);

    /**
     * Вход по указанному адресу с указанным логином и паролем
     * @param address Адрес /login страницы
     * @param login Логин
     * @param password Пароль
     * @return
     */
    public UIOperationsInterface login(String address, String login, String password);

    /**
     * Выход из учетной записи авторизованного пользователя
     * @return
     */
    public UIOperationsInterface logout();

    /**
     * Клик по указанной секции навигационного меню
     * @param section Наименование секции
     * @return
     */
    public UIOperationsInterface sectionClick(String section);

    /**
     * Клик по указанной секции навигационного меню, далее клик по указанной подсекции меню
     * @param section Секция
     * @param subSection Подсекция
     * @return
     */
    public UIOperationsInterface subSectionClick(String section, String subSection);

    /**
     * Клик по кнопке с указанным наименованием (текстом)
     * @param buttonText Наименование(текст) кнопки
     * @return
     */
    public UIOperationsInterface buttonClick(String buttonText);

    /**
     * Клик по кнопке с указанным id
     * @param buttonId id кнопки
     * @return
     */
    public UIOperationsInterface buttonClickById(String buttonId);

    /**
     * Клик по инпуту(полю ввода) с указанным Наименованием
     * @param inputName Наименование поля
     * @return
     */
    public UIOperationsInterface inputClick(String inputName);

    /**
     * Клик по инпуту(полю ввода) с указанным id
     * @param inputId id поля ввода
     * @return
     */
    public UIOperationsInterface inputClickById(String inputId);

    /**
     * Ввод указанного значения в поле ввода
     * @param inputName Наименование поля ввода
     * @param value Значение для ввода
     * @return
     */
    public UIOperationsInterface inputSet(String inputName, String value);

    /**
     * Ввод значения в поле ввода указанного региона
     * @param inputName Наименование поля ввода
     * @param value Значение для ввода
     * @param cardName Регион с полем ввода
     * @return
     */
    public UIOperationsInterface cardInputSet(String inputName, String value, String cardName);

    /**
     * Ввод значения в указанный по id инпут(поле ввода)
     * По возможности нужно использовать его
     * @param inputId id поля ввода
     * @param value Значение
     * @return
     */
    public UIOperationsInterface inputSetById(String inputId, String value);

    /**
     * Выбор значения из выпадающего списка
     * @param dropDownInputName Наименование списка
     * @param value Значение для выбора
     * @return
     */
    public UIOperationsInterface dropDownListInputSet(String dropDownInputName,
                                          String value);

    /**
     * Проверка поля ввода на содержание значения
     * @param inputName Наименование поля ввода
     * @param value Значение для проверки
     * @return
     */
    public UIOperationsInterface inputContains(String inputName, String value);

    /**
     * Проверка поля ввода в указанном регионе на содержание значения
     * @param inputName Наименование поля ввода
     * @param value Значение для проверки
     * @param cardName Наименование региона
     * @return
     */
    public UIOperationsInterface cardInputContains(String inputName,String value,String cardName);

    /**
     * Переключение чекбокса
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Позиция чекбокса: true - вкл., false - выкл.
     * @return
     */
    public UIOperationsInterface switchCheckbox(String checkboxName,
                                    boolean switchPosition);

    /**
     * Проверка положения чекбокса
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Значение для проверки: true - вкл., false - выкл.
     * @return
     */
    public UIOperationsInterface switchCheckboxIs(String checkboxName,boolean switchPosition);

    /**
     * Проверка положения чекбокса в указанном регионе
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Положение чекбокса: true - вкл., false - выкл.
     * @param cardName Наименование региона
     * @return
     */
    public UIOperationsInterface cardSwitchCheckboxIs(String checkboxName,boolean switchPosition, String cardName);

    /**
     * Переключение чекбокса на вкл.
     * @param checkboxName Наименование чекбокса
     * @return
     */
    public UIOperationsInterface switchCheckbox(String checkboxName);

    /**
     * Переключение чекбокса в указанном регионе
     * @param checkboxName Наименование чекбокса
     * @param switchPosition Положение чекбокса: true - вкл., false - выкл.
     * @param cardName Наименование региона
     * @return
     */
    public UIOperationsInterface cardSwitchCheckbox(String checkboxName,
                                        boolean switchPosition, String cardName);

    /**
     * Ввод в указанное поле ввода случайного значения из таблицы, находящейся на странице.
     * Для этого нужно, чтобы Наименование поля ввода совпадало с одним из столбцов таблицы.
     * Например, поле ввода "Адрес" и в таблице должен быть столбец с заголовком "Адрес".
     * В таком случае будет взято первое значение из таблицы.
     * @param inputName Наименование поля ввода и Наименование столбца таблицы
     * @return
     */
    public UIOperationsInterface setInputValueFromTableValues(String inputName);

    /**
     * Ввод фильтров
     * @param filterInput Наименование поля ввода в фильтрах
     * @param value Значение фильтра
     * @return
     */
    public UIOperationsInterface filterSet(String filterInput, String value);

    /**
     * Принимает параметры настроек таблицы, которые будут выключены
     * @param settingsToOff Параметры настроек таблицы для выключения
     * @return
     */
    public UIOperationsInterface settingsOff(String... settingsToOff);

    /**
     * Принимает параметры настроек таблицы, которые будут включены
     * @param settingsToOn Параметры настроек таблицы для включения
     * @return
     */
    public UIOperationsInterface settingsOn(String... settingsToOn);

    /**
     * Выставляет необходимую дату в поле календаря
     * @param calendarInputName Наименование поля календаря
     * @param year Год в формате "2025"
     * @param month Месяц в формате "Май"
     * @param date Дата в формате "12"
     * @return
     */
    public UIOperationsInterface calendarSet(String calendarInputName, String year,
                                 String month, String date);

    /**
     * Выставляет текущую дату в поле календаря
     * @param calendarInputName Наименование поля календаря
     * @return
     */
    public UIOperationsInterface calendarSet(String calendarInputName);

    /**
     * Выставляет дату с отклонением в указанный параметр от текущей даты в поле календаря.
     * Например, вызвав метод с параметром -3, будет выставлена дата на 3 дня раньше текущей
     * @param calendarInputName Наименование поля календаря
     * @param relationDay Отклонение от текущей даты
     * @return
     */
    public UIOperationsInterface calendarSet(String calendarInputName, int relationDay);

    /**
     * Проверка на содержание значения строки в таблице
     * @param text Текст проверки
     * @return
     */
    public UIOperationsInterface tableRowExists(String text);

    /**
     * Отрицательная проверка на содержание значения строки в таблице
     * @param text
     * @return
     */
    public UIOperationsInterface tableRowNotExists(String text);

    /**
     * Клик по указанным строке и столбцу в таблице в таблице
     * @param rowText Текст строки
     * @param cellNumber Номер столбца
     * @return
     */
    public UIOperationsInterface tableRowCellClick(String rowText, int cellNumber);

    /**
     * Клик по первой найденной ссылке в строке таблицы
     * @param text Текст строки таблицы
     * @return
     */
    public UIOperationsInterface tableCellHrefClick(String text);

    /**
     * Проверка существования тэга(например, тэга фильтра) с указанным наименованием и значением
     * @param tagName Наименование тэга
     * @param tagValue Значение тэга
     * @return
     */
    public UIOperationsInterface tagExists(String tagName, String tagValue);

    /**
     * Отрицательная проверка существования тэга(например, тэга фильтра) с указанным наименованием и значением
     * @param tagName Наименование тэга
     * @param tagValue Значение тэга
     * @return
     */
    public UIOperationsInterface tagNotExists(String tagName, String tagValue);

    /**
     * Удаление записи из таблицы, если такая существует
     * @param rowValue Текст строки в таблице для удаления записи
     * @return
     */
    public UIOperationsInterface deleteFromTableIfExists(String rowValue);

    /**
     * Проверка соответствия текста ошибки с указанным текстом
     * @param value Должный текст ошибки
     * @return
     */
    public UIOperationsInterface alertTextEquals(String value);

    /**
     * Согласие на удаление записи.
     * Кликает на кнопку "ОК", "Да" по ее Id
     * @return
     */
    public UIOperationsInterface confirmDelete();
}
