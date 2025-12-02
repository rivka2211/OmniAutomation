package org.example.ScreenshotOnFailure;

/**
 * Central WebDriver provider using ThreadLocal.
 * Ensures each test gets its own isolated driver instance.
 */
import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /**
     * Retrieves the WebDriver instance for the current thread.
     */
    public static WebDriver getDriver() {
        return DRIVER.get();
    }
    /**
     * Sets the WebDriver instance for the current thread.
     */
    public static void setDriver(WebDriver driver) {
        DRIVER.set(driver);
    }
    /**
     * Cleans up driver instance after test completion.
     */
    public static void quitDriver() {
        WebDriver driver = DRIVER.get();
        if (driver != null) {
            driver.quit();
            DRIVER.remove();
        }
    }
}

