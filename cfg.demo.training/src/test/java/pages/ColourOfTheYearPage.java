package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class ColourOfTheYearPage extends Base {

    public void verifyColourOfYear() {
        WebElement heading = wait.until(driver ->
            driver.findElement(By.xpath("//span[contains(text(),'Colour of the year 2025 – Cardinal (8206)')]")));
        highlight(heading);
        Assert.assertTrue(heading.isDisplayed());
    }

    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
    }
}
