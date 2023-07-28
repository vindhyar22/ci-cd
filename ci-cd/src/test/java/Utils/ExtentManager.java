package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter; // Updated import statement
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
    private static ExtentReports extent;
    private static String reportFileName = "ExtentReport.html";
    private static String reportFilePath = System.getProperty("user.dir") + "\\test-output\\" + reportFileName;

    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }

    private static ExtentReports createInstance() {
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(reportFilePath); // Updated to ExtentSparkReporter
        htmlReporter.config().setDocumentTitle("Test Automation Report");
        htmlReporter.config().setReportName("Test Automation Results");
        htmlReporter.config().setTheme(Theme.STANDARD);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}


