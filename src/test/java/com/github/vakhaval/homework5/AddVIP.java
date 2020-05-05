package com.github.vakhaval.homework5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddVIP {

    private WebDriver driver;
    WebElement firstNameField;
    WebElement lastNameField;
    WebElement addButton;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.ranorex.com/web-testing-examples/vip/");
        firstNameField = driver.findElement(By.id("FirstName"));
        lastNameField = driver.findElement(By.id("LastName"));
        addButton = driver.findElement(By.id("Add"));
    }

    @Test(priority = 1)
    public void newVipDisplayed() {
        firstNameField.sendKeys("Tom");
        lastNameField.sendKeys("Riddle");
        addButton.click();
        assertEquals(driver.findElement(By.xpath("//form[@id='form']//tr[2]//td[2]")).getText(), "Tom");
        assertEquals(driver.findElement(By.xpath("//form[@id='form']//tr[2]//td[3]")).getText(), "Riddle");
        assertEquals(driver.findElement(By.id("count")).getText(), "VIP count: 1");
    }

    @Test(priority = 2)
    public void countIncreased() {
        firstNameField.sendKeys("Harry");
        lastNameField.sendKeys("Potter");
        addButton.click();
        assertEquals(driver.findElement(By.id("count")).getText(), "VIP count: 2");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
