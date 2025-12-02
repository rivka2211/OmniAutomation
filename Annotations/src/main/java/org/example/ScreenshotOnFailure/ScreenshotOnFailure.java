package org.example.ScreenshotOnFailure;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface ScreenshotOnFailure {
    boolean savePageSource() default true;
    boolean uploadToReportPortal() default false;
}

