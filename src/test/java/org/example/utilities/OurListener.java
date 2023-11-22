package org.example.utilities;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class OurListener implements ITestListener {
    public void onTestStart(ITestResult iTestResult) {
        System.out.println((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
        takeScreenshot();
    }

    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                getExecutionTime(iTestResult)));
        takeScreenshot();
    }

    public void onTestFailure(ITestResult iTestResult) {
        System.out.println(String.format("======================================== FAILED TEST %s Duration: %ss ========================================",
                iTestResult.getName(),
                getExecutionTime(iTestResult)));
        takeScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
        takeScreenshot();
    }

    private void takeScreenshot() {
        WebDriver driver = getWebDriver();
        Allure.addAttachment("screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    private long getExecutionTime(ITestResult iTestResult) {
        return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
    }
}
