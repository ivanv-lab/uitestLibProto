package testlib.base.adm;

import org.openqa.selenium.By;

import java.util.List;

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

    }

    public List<String> getSubSections(String section){

    }
}
