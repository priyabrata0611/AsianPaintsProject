package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class CelebrityHomesage extends Base {

    public void verifyCelebrityContent() {
        WebElement exploreBtn = wait.until(driver ->
            driver.findElement(By.xpath("//a[contains(text(),'explore similar styles')]")));
        highlight(exploreBtn);
        Assert.assertTrue(exploreBtn.isDisplayed());

        WebElement videoThumb = wait.until(driver ->
            driver.findElement(By.xpath("//img[contains(@class,'videocards__thumbnail')]")));
        highlight(videoThumb);
        Assert.assertTrue(videoThumb.isDisplayed());
    }

    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
    }
}
