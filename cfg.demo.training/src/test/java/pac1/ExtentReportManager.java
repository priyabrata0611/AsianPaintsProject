package pac1;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            spark.config().setDocumentTitle("Asian Paints Automation Report");
            spark.config().setReportName("Inspiration Tab Test Execution");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Project", "Asian Paints UI Automation");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "Priyabrata Behera");
        }
        return extent;
    }
}
