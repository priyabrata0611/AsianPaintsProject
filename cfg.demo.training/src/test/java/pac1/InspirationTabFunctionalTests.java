package pac1;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class InspirationTabFunctionalTests {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void clickInspirationTab() throws InterruptedException {
        driver.get("https://www.asianpaints.com/");
        Thread.sleep(3000);

        WebElement inspiration = driver.findElement(By.xpath("//a[@data-target='#Inspiration']"));
        js.executeScript("arguments[0].click();", inspiration);
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void testColourCombinations() throws InterruptedException {
        driver.get("https://www.asianpaints.com/inspiration/ideas/colour-inspiration.html");
        Thread.sleep(3000);

        // Check if Exterior tile is present
        WebElement exteriorImg = driver.findElement(By.xpath("//img[contains(@alt, 'Wall color combinations for home exterior')]"));
        assert exteriorImg.isDisplayed() : "Exterior image not found";

        // Check if Living Room tile is present
        WebElement livingRoomImg = driver.findElement(By.xpath("//img[contains(@alt, 'Wall color combinations for living room')]"));
        assert livingRoomImg.isDisplayed() : "Living Room image not found";

        System.out.println("✅ Colour Combinations page loaded with both Living Room and Exterior categories.");
    }

    @Test(priority = 3)
    public void testCelebrityHomes() throws InterruptedException {
        driver.get("https://www.asianpaints.com/where-the-heart-is/season-7.html");
        Thread.sleep(3000);

        // Check Explore Similar Styles button (sample one)
        WebElement exploreButton = driver.findElement(By.xpath("//a[contains(@href,'blog-janhvi-kapoor') and contains(text(),'explore similar styles')]"));
        assert exploreButton.isDisplayed() : "'Explore Similar Styles' button not visible";

        // Check one video thumbnail image (Sunil Grover)
        WebElement videoThumb = driver.findElement(By.xpath("//img[contains(@class,'videocards__thumbnail') and contains(@src,'sunil-grover-thumbnail.jpg')]"));
        assert videoThumb.isDisplayed() : "Celebrity video thumbnail not visible";

        System.out.println("✅ Celebrity Homes page loaded with video and explore button.");
    }

    @Test(priority = 4)
    public void testColourOfTheYear() throws InterruptedException {
        driver.get("https://www.asianpaints.com/colour-next.html");
        Thread.sleep(3000);

        // Check if Colour of the Year text is present
        WebElement colourOfYearTitle = driver.findElement(By.xpath("//span[contains(text(),'Cardinal (8206)')]"));
        assert colourOfYearTitle.isDisplayed() : "Colour of the Year 'Cardinal (8206)' not visible";

        System.out.println("✅ Colour of the Year page displays 'Cardinal (8206)'.");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
