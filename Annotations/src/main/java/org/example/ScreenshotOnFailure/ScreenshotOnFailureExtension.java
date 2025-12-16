package org.example.ScreenshotOnFailure;

/**
 * This extension intercepts test execution failures.
 * When a test throws an exception, it checks whether the test or its class
 * is annotated with @ScreenshotOnFailure and performs:
 * 1. Screenshot capture
 * 2. PageSource saving
 * 3. ReportPortal upload (optional)
 * 4. Rethrowing the original exception
 */

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Method;
import java.util.Optional;

public class ScreenshotOnFailureExtension implements TestWatcher {

    /**
     * Triggered by TestWatcher on test failure.
     * When @ScreenshotOnFailure is present on the test,
     * a screenshot is captured and saved.
     *
     * Executed during the JUnit test lifecycle.
     */

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        ScreenshotOnFailure annotation = resolveAnnotation(context);
        if (annotation != null) {
            var driver = DriverManager.getDriver();

            if (driver != null) {
                ScreenshotUtils.captureScreenshot(driver, context);

                if (annotation.savePageSource()) {
                    ScreenshotUtils.savePageSource(driver, context);
                }

                if (annotation.uploadToReportPortal()) {
                    ScreenshotUtils.uploadToReportPortal(driver, context);
                }
            }
        }
        try {
            throw cause; // Always rethrow the original error
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Resolves the @ScreenshotOnFailure annotation.
     * Method-level annotation overrides class-level annotation.
     */
    private ScreenshotOnFailure resolveAnnotation(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        Class<?> testClass = context.getRequiredTestClass();

        ScreenshotOnFailure methodAnn = testMethod.getAnnotation(ScreenshotOnFailure.class);
        if (methodAnn != null) {
            return methodAnn;
        }

        return testClass.getAnnotation(ScreenshotOnFailure.class);
    }
}

