// Package location
package tests;

// Import base class which handles browser setup and teardown
import base.Base;

// Import page object classes
import pages.*;

// Import custom Extent Report manager
import utilities.ExtentManager;

// TestNG annotations and assertions
import org.testng.annotations.*;
import com.aventstack.extentreports.*;
import org.testng.Assert;

// Selenium core classes for browser interaction
import org.openqa.selenium.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.JavascriptExecutor;

// Java utility imports
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AutomationTest extends Base {

    // Declaring global variables for reporting and page classes
    ExtentReports extent;               // Main report object
    ExtentTest test;                    // Logs each test
    HomePage home;                      // Page object for homepage
    ColourCombinationPage combinationsPage; // Page object for 'Colour Combinations'
    CelebrityHomesage celebrityPage;        // Page object for 'Celebrity Homes'
    ColourOfTheYearPage colourPage;         // Page object for 'Colour of the Year'

    // This method runs once before all tests. It sets up the browser and report.
    @Parameters("browser")
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        initializeBrowser(browser);             // Launches browser based on parameter
        extent = ExtentManager.getInstance();   // Initializes ExtentReports
        home = new HomePage();                  // Instantiate HomePage object
        combinationsPage = new ColourCombinationPage(); // Instantiate ColourCombination page
        celebrityPage = new CelebrityHomesage();         // Instantiate CelebrityHomes page
        colourPage = new ColourOfTheYearPage();          // Instantiate Colour of the Year page
    }

    // Test 1: Verifies clicking on the 'Inspiration' tab
    @Test(priority = 1)
    public void clickInspirationTabOnly() throws InterruptedException {
        test = extent.createTest("Click on 'Inspiration' Tab");   // Create report entry
        driver.get("https://www.asianpaints.com/");              // Navigate to home
        home.clickInspirationTab();                              // Clicks the Inspiration tab
        test.pass("Clicked on 'Inspiration' tab successfully."); // Log success
    }

    // Test 2: Verifies 'Colour Combinations' page content
    @Test(priority = 2)
    public void testColourCombinationsPage() {
        test = extent.createTest("Verify Colour Combinations Page");
        driver.get("https://www.asianpaints.com/inspiration/ideas/colour-inspiration.html");
        combinationsPage.verifyImages();             // Custom method to verify image alt texts
        test.pass("Verified all images by alt text.");
    }

    // Test 3: Verifies 'Celebrity Homes' content like video and button
    @Test(priority = 3)
    public void testCelebrityHomesPage() {
        test = extent.createTest("Verify Celebrity Homes Page");
        driver.get("https://www.asianpaints.com/where-the-heart-is/season-7.html");

        try {
            // Locate and scroll to the 'Explore similar styles' button
            WebElement exploreBtn = driver.findElement(By.xpath("//a[contains(text(),'explore similar styles')]"));
            scrollToElement(exploreBtn);              // Scroll to the button
            Thread.sleep(500);                        // Wait for scroll animation
            takeScreenshot("celebrity_explore_similar_styles.png");  // Take screenshot

            // Locate and scroll to the video play button
            WebElement playBtn = driver.findElement(By.xpath("//span[contains(@class,'playVideoIcon')]"));
            scrollToElement(playBtn);
            Thread.sleep(500);
            takeScreenshot("celebrity_video_play_button.png");

            test.pass("Validated 'Explore similar styles' and video play icon with screenshots.");

            celebrityPage.verifyCelebrityContent();   // Run content validation logic
            test.pass("Celebrity content verified successfully.");

        } catch (Exception e) {
            takeScreenshot("celebrity_page_failure.png"); // On failure, capture screenshot
            test.fail("Celebrity Homes page validation failed: " + e.getMessage());
            Assert.fail("Celebrity Homes validation failed");
        }
    }

    // Test 4: Validates the 'Colour of the Year' page elements like heading, video, and download
    @Test(priority = 4)
    public void testColourOfTheYearPage() {
        test = extent.createTest("Verify Colour of the Year 2025 Page");
        driver.get("https://www.asianpaints.com/colour-next.html");

        try {
            // Scroll to and screenshot the heading
            WebElement heading = driver.findElement(By.xpath("//*[contains(text(),'Colour of the year 2025 – Cardinal')]"));
            scrollToElement(heading);
            Thread.sleep(500);
            takeScreenshot("colour_of_year_heading.png");

            // Scroll to and screenshot the video thumbnail
            WebElement videoThumb = driver.findElement(By.xpath("//img[contains(@class,'video-elem__thumbnail-img')]"));
            scrollToElement(videoThumb);
            Thread.sleep(500);
            takeScreenshot("colour_of_year_video_thumbnail.png");

            // Scroll to and screenshot the Download button
            WebElement downloadBtn = driver.findElement(By.xpath("//a[contains(text(),'Download')]"));
            scrollToElement(downloadBtn);
            Thread.sleep(500);
            takeScreenshot("colour_of_year_download_button.png");

            test.pass("Heading, video thumbnail, and Download button validated with screenshots.");

            colourPage.verifyColourOfYear(); // Run content validation logic
            test.pass("Colour of the Year heading verified.");

        } catch (Exception e) {
            takeScreenshot("colour_of_year_page_failure.png"); // On failure, capture screenshot
            test.fail("Colour of the Year verification failed: " + e.getMessage());
            Assert.fail("Colour of the Year validation failed");
        }
    }

    // Test 5: Negative test that simulates a submodule failure
    @Test(priority = 5)
    public void testInspirationSubModulesFailing() {
        test = extent.createTest("(Failing) Validate Submodules Under Inspiration Tab");

        driver.get("https://www.asianpaints.com/");
        try {
            home.clickInspirationTab();    // Click Inspiration tab

            // Scroll to bottom of page to simulate looking for content
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
            Thread.sleep(1000);

            // Try to find 'Colour Combinations' submodule (could be missing to simulate failure)
            driver.findElement(By.xpath("//a[text()='Colour Combinations']"));
            test.pass("Submodule 'Colour Combinations' is visible");

        } catch (Exception e) {
            takeScreenshot("submodule_failure.png");  // Screenshot on error
            test.fail("Submodule 'Colour Combinations' not found or visible: " + e.getMessage());
            Assert.fail("Submodule verification failed");
        }
    }

    // Utility method: Takes screenshot and attaches to extent report
    public void takeScreenshot(String fileName) {
        try {
            File dir = new File("screenshots");
            if (!dir.exists()) dir.mkdirs();  // Create folder if not exists

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Capture
            File dest = new File(dir, fileName); // Set destination
            Files.copy(src.toPath(), dest.toPath()); // Save file
            test.addScreenCaptureFromPath(dest.getPath()); // Attach to report

        } catch (IOException e) {
            test.warning("Screenshot failed: " + e.getMessage());
        }
    }

    // Utility method: Scrolls to a web element smoothly and centers it on screen
    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", element); // Center scroll
        js.executeScript("window.scrollBy(0, -100);"); // Adjust for fixed headers
    }

    // After all tests: quit browser and flush extent report
    @AfterClass
    public void tearDown() {
        quitBrowser();     // Close browser
        extent.flush();    // Finalize and save report
    }
}
