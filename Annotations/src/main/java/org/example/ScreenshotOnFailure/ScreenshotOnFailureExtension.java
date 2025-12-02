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

import java.lang.reflect.Method;

public class ScreenshotOnFailureExtension implements TestExecutionExceptionHandler {

    /**
     * Handles any exception thrown during the test.
     * If @ScreenshotOnFailure is present, performs screenshot actions.
     */
    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {

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
        throw throwable; // Always rethrow the original error
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

