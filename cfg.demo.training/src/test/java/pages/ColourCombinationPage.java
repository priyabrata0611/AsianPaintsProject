package pages;

import base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class ColourCombinationPage extends Base {

    public void verifyImages() {
        String[] altTexts = {
            "The exterior of a house along with the surrounding landscape. Wall color combinations for home exterior.",
            "A living room with yellow accent wall and TV unit. Wall color combinations for living room.",
            "A bedroom decorated with wooden wall panels and headboard. Wall color combinations for bedroom."
        };

        for (String alt : altTexts) {
            WebElement img = wait.until(driver ->
                driver.findElement(By.xpath("//img[@alt='" + alt + "']")));
            highlight(img);
            Assert.assertTrue(img.isDisplayed(), "Image not visible: " + alt);
        }
    }

    public void highlight(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid yellow'", element);
    }
}
