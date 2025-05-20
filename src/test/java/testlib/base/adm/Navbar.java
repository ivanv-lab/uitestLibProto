package testlib.base.adm;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class Navbar extends BasePage{

    By sidebar=By.xpath(".//div[contains(@class,'sidebar')]");
    By sidebarLockButton=By.xpath(".//button[@id='minimizeSidebar']");
    By section=By.xpath(".//div[contains(@class,'sidebar')]/ul/li");
    By subSection=By.xpath(".//div[contains(@class,'sidebar')]/ul//ul//a");

    public void openSidebar() throws InterruptedException {
        click(sidebar);
        waitForElementClickable(sidebarLockButton,5);
        click(sidebarLockButton);
    }

    public void subSectionClick(String sectionText, String subSectionText){
        click(By.xpath(section+"[a/p[contains(.,'"+sectionText+"')]]/a"));
        click(By.xpath(subSection+"[span[@class='sidebar-normal' and text()='"+subSectionText+"']]"));
    }

    public List<String> getSections(){
        return  $$x((section+"/a/p"))
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asFixedIterable().stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }

    public List<String> getSubSections(String section){
        return $$x((section+"[a/p[contains(.,'"+section+"')]]//li//span[@class='sidebar-normal']"))
                .shouldHave(CollectionCondition.sizeGreaterThan(0))
                .asFixedIterable().stream()
                .map(SelenideElement::getText)
                .collect(Collectors.toList());
    }
}
