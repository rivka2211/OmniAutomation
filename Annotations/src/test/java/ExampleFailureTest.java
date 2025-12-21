/**
 * This test class demonstrates how @ScreenshotOnFailure works.
 * The test intentionally fails to trigger the extension:
 * - Screenshot capture
 * - Page source saving
 * - (optional) ReportPortal upload
 */


import Drivermanager.ProvideDriver;
import org.example.ScreenshotOnFailure.ScreenshotOnFailure;
import org.example.ScreenshotOnFailure.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(ScreenshotOnFailureExtension.class)
public class ExampleFailureTest {
    static WebDriver driver;

    /**
     * Initializes WebDriver before each test.
     */
    @BeforeAll
    static void setup() {
        driver = new ChromeDriver();
        ProvideDriver.setDriver(driver);
        driver.get("https://translate.google.com/?hl=iw&sl=en&tl=iw&op=translate");
    }

    /**
     * Cleans up WebDriver after test.
     */
    @AfterAll
    static void teardown() {
        ProvideDriver.quitDriver();
    }

    /**
     * Demonstrates screenshot + pageSource saving on failure.
     * This test fails intentionally.
     */
    @Test
    @ScreenshotOnFailure(savePageSource = false, uploadToReportPortal = false)
    void failingTestExample() {

        Assertions.assertEquals(driver.getTitle(), driver.getTitle());
        Report.error("cnjk");
    }
}

