package YufoodsNew.yufoods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartPage {

    WebDriver driver;

    @BeforeTest
    void openBrowser() {
        // Initialize WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://yufoodsco.com/collections/noodle-bowls/products/veg-hakka-noodles-pack-of-3");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Add product into cart and close popup
    @Test
    void TC_01() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement Close_popup = wait.until(ExpectedConditions
                    .elementToBeClickable(By.xpath("//a[@class='js-popup-close newsletter__popup-container-close']")));
            Close_popup.click();
            WebElement clickAddToCart = wait.until(
                    ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='product-add add-pro-cart']")));
            clickAddToCart.click();
        } catch (Exception e) {
            Assert.fail("Error in TC_01: " + e.getMessage());
        }
    }

    // Increase the qty of product on mini cart
    @Test
    void TC_02() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement minicart = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[normalize-space()='+'])[1]")));
            minicart.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail("Error in TC_02:" + e.getMessage());
        }
    }

    // Click On View Cart on Mini Cart to Open Cart Page
    @Test
    void TC_03() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement viewCart = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//a[@class='ajax-cart__button-view_cart button secondary-button']")));
            viewCart.click();
            Thread.sleep(5000);
        } catch (Exception e) {
            Assert.fail("Error in TC_03:" + e.getMessage());
        }
    }

    // Add note
    @Test
    void TC_04() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement Addnote = wait
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("(//*[@class='closed'])[1]")));
            Addnote.click();
            WebElement update_text = wait.until(ExpectedConditions.elementToBeClickable(By.id("note")));
            update_text.click();
            update_text.sendKeys("Pack Food Properly");
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail("Error in TC_04:" + e.getMessage());
        }
    }

    // Click on checkout to redirect checkout page
    @Test
    void TC_05() {
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("//section[@id='shopping-cart']//div[contains(@class,'ajax-cart__buttons')]"))
                    .click();
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail("Error in TC_05:" + e.getMessage());
        }
    }

    @AfterTest
    void closeBrowser() {
        // Close the browser
        driver.quit();
    }
}