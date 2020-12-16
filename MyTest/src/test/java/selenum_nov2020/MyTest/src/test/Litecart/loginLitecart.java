package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.json.JsonOutput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.w3c.dom.ls.LSOutput;

import java.time.Duration;
import java.util.*;

import static java.util.Collections.sort;

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
            elementList = driver.findElements(By.cssSelector("#box-apps-menu a"));
            List<WebElement> h1 = driver.findElements(By.tagName("h1"));

            List<WebElement> subMenu = elementList.get(i).findElements(By.cssSelector(".app-"));
            int numberOfListElementsNew = subMenu.size();
            for (int j = 0; j < numberOfListElementsNew; j++) {
                subMenu = elementList.get(i).findElements(By.cssSelector(".app-"));
                subMenu.get(j).click();
                List<WebElement> h2 = driver.findElements(By.tagName("h1"));
            }


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

    @Test
    public void ShouldGetCountriesA_Z() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        List<WebElement> allCountries = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[5]"));
        int numberOfCountries = allCountries.size();
        boolean num = true;
        String next;
        String prev;
        if (numberOfCountries > 1) {
            for (int i = 0; i < (numberOfCountries - 1); i++) {
                prev = allCountries.get(i).getAttribute("textContent");
                next = allCountries.get(i + 1).getAttribute("textContent");
                //String countries = allCountries.get(i).getAttribute("textContent");
                //System.out.println(countries);
                //System.out.println(prev.compareTo(next));
                if (prev.compareTo(next) > 0) {
                    num = false;
                }
            }
            System.out.println(num);

        } else {
            System.out.println("Countries < 2");
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}


