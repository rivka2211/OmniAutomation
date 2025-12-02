/**
 * This test class demonstrates how @ScreenshotOnFailure works.
 * The test intentionally fails to trigger the extension:
 * - Screenshot capture
 * - Page source saving
 * - (optional) ReportPortal upload
 */


import org.example.ScreenshotOnFailure.DriverManager;
import org.example.ScreenshotOnFailure.ScreenshotOnFailure;
import org.example.ScreenshotOnFailure.ScreenshotOnFailureExtension;
import org.junit.jupiter.api.*;
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
        DriverManager.setDriver(driver);
        driver.get("https://www.google.com/");
    }

    /**
     * Demonstrates screenshot + pageSource saving on failure.
     * This test fails intentionally.
     */
    @Test
    @ScreenshotOnFailure(savePageSource = false, uploadToReportPortal = false)
    void failingTestExample() {
        Assertions.assertEquals("Not Google Title", driver.getTitle());
    }

    /**
     * Cleans up WebDriver after test.
     */
    @AfterAll
    static void teardown() {
        DriverManager.quitDriver();
    }
}

