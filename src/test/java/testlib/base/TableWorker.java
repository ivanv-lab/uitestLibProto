package testlib.base;

import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс работы с таблицами в интерфейсе
 */
public class TableWorker extends BasePage {

    /**
     * Метод кликает на ссылку в строке, которая содержит текст @param text в качестве ссылки
     * @param text
     */
    public void tableHrefClick(String text){
        click(By.xpath(".//a[contains(@id,'link_edit') and text()='"+text+"']"));
    }

    /**
     * Метод проверяет наличие текста @param text в любой строке таблицы и возвращает булево
     * @param text
     * @return
     */
    public boolean tableRowExists(String text){

        List<String> elementsRowCollection=new ArrayList<>();

        int tableSize=findCollection(By.xpath(".//table/tbody/tr")).size();

        String tableRowCollection="";
        for(int i=0;i<tableSize;i++){
            tableRowCollection=getText(By.xpath(".//table/tbody/tr["+i+"]"));
            if(tableRowCollection.contains(text)){
                return true;
            }
        }
        return false;
    }
}
