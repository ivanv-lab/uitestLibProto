package testlib.base;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Класс работы с таблицами в интерфейсе
 */
public class TableWorker extends BasePage {

    /**
     * Метод кликает на ссылку в строке, которая содержит текст @param text в качестве ссылки
     * @param text
     */
    public void tableHrefClick(String text){
        click(By.xpath(".//tbody/tr[td//*[normalize-space(text())='"+text+"']]/td//a"));
    }

    /**
     * Метод проверяет наличие текста @param text в любой строке таблицы и возвращает булево
     * @param text
     * @return
     */
    public boolean tableRowExists(String text){

        int tableSize=findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection="";
        for(int i=1;i<=tableSize;i++){
            if($(By.xpath(".//table/tbody/tr["+i+"]")).exists()) {
                tableRowCollection = getText(By.xpath(".//table/tbody/tr[" + i + "]"));
                if (tableRowCollection.contains(text)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод ищет первую строку в таблице с указанным текстом @param rowText и кликает на столбец с указанным номером @param cellNumber
     * @param rowText
     * @param cellNumber
     */
    public void tableRowCellClick(String rowText, int cellNumber) {

        int tableSize = findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection = "";
        for (int i = 1; i <= tableSize; i++) {
            tableRowCollection = getText(By.xpath(".//table/tbody/tr[" + i + "]"));
            if (tableRowCollection.contains(rowText)) {
                click(By.xpath(".//table/tbody/tr["+i+"]/td["+cellNumber+"]"));
                return;
            }
        }
    }
}
