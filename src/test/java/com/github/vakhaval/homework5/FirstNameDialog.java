package com.github.vakhaval.homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class FirstNameDialog {

    private WebDriver driver;
    private Set<String> oldWindowsSet;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
    }

    @Test(priority = 1)
    public void firstNameDialogOpen() {
        oldWindowsSet = driver.getWindowHandles();
        WebElement add = driver.findElement(By.id("Add"));
        add.click();

        String newWindow = (new WebDriverWait(driver, 10))
                .until(new ExpectedCondition<String>() {
                           public String apply(WebDriver driver) {
                               Set<String> newWindowsSet = driver.getWindowHandles();
                               newWindowsSet.removeAll(oldWindowsSet);
                               return newWindowsSet.size() > 0 ? newWindowsSet.iterator().next() : null;
                           }
                       }
                );
        driver.switchTo().window(newWindow);
        assertEquals(driver.findElement(By.id("alertTextOK")).getText(), "Please specify 'First Name' value");
    }

    @Test(priority = 2)
    public void firstNameDialogClose() {
        driver.findElement(By.id("OK")).click();
        assertEquals(driver.getWindowHandles(), oldWindowsSet);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
