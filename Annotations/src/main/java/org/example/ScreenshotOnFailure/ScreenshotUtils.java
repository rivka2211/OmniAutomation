package org.example.ScreenshotOnFailure;

import org.openqa.selenium.*;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Utility class for handling screenshot and related artifacts.
 * Provides support for: screenshot capture, page source saving,
 * and (optional) ReportPortal upload integration.
 */
public class ScreenshotUtils {
    /**
     * Captures a screenshot and saves it in the /screenshots directory.
     */
    public static void captureScreenshot(WebDriver driver, ExtensionContext context) {
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            Path folder = Path.of("screenshots");
            Files.createDirectories(folder);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            Path dest = folder.resolve(
                    context.getDisplayName() + "_screenshot_" + timestamp + ".png"
            );

            Files.copy(src.toPath(), dest);

        } catch (Exception e) {

            // Logging intentionally avoided here, extension should not fail screenshot silently
        }
    }

    /**
     * Saves the current page source to /pagesource directory.
     */
    public static void savePageSource(WebDriver driver, ExtensionContext context) {
        try {
            String source = driver.getPageSource();

            Path folder = Path.of("pagesource");
            Files.createDirectories(folder);
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));
            Path dest = folder.resolve(context.getDisplayName() + "_pageSource_"+timestamp+".html");
            Files.writeString(dest, source);

        } catch (Exception e) {
            // Intentional silent catch
        }
    }

    /**
     * Uploads screenshot and/or page source to ReportPortal.
     */
    public static void uploadToReportPortal(WebDriver driver, ExtensionContext context) {
        try {
            // Example: integration stub (depends on your RP setup)
            // ReportPortal.emitLog("Screenshot taken", "INFO", new File(...));

        } catch (Exception e) {
            // Avoid breaking the test flow due to RP failure
        }
    }
}
