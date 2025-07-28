package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

public class HomePage extends Base {

    public void clickInspirationTab() throws InterruptedException {
        WebElement inspirationTab = wait.until(driver ->
            driver.findElement(By.xpath("//a[@data-target='#Inspiration']")));
        highlight(inspirationTab);
        inspirationTab.click();
        Thread.sleep(2000);
    }

    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
    }
}
