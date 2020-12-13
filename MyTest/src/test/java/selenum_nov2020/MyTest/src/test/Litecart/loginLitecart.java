package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class loginLitecart {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.quit();


    }

    @Test
    public void mySecondTest() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        List<WebElement> elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
        int numberOfListElements = elementList.size();

        for (int i = 0; i < numberOfListElements; i++) {
            elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
            elementList.get(i).click();

            List<WebElement> h1 = driver.findElements(By.tagName("h1"));

        }

    }

    @Test
    public void shouldFindOneSticker() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.get("http://localhost/litecart/en/");

        List<WebElement> elementListDucks = driver.findElements(By.cssSelector(".product.column.shadow.hover-light"));

        int numberOfListElements = elementListDucks.size();

        for (int i = 0; i < numberOfListElements; i++) {

            try {
                WebElement imgDuck = elementListDucks.get(i).findElement(By.cssSelector(".image-wrapper"));
                List<WebElement> stickers = imgDuck.findElements(By.cssSelector(".sticker"));
                int numberOfListStickers = stickers.size();

                if (numberOfListStickers > 1) {
                    System.out.println("stickers > 1");
                }
                if (numberOfListStickers == 0) {
                    System.out.println("stickers = 0");
                }
                if (numberOfListStickers == 1) {
                    System.out.println("stickers = 1");
                }
            } catch (Exception e) {
            }


        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}


