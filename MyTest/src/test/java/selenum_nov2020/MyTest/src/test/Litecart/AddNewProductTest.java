package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class AddNewProductTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @Test
    public void shouldAddNewProduct() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.get("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        //General
        driver.findElement(By.cssSelector("input[type = 'radio'][value = '1']")).click();
        WebElement duckName = driver.findElement(By.cssSelector("input[type = 'text'][name = 'name[en]']"));
        duckName.sendKeys("Test Duck");

        driver.findElement(By.cssSelector("input[type = 'text'][name = 'code']")).sendKeys("010101");
        driver.findElement(By.cssSelector("input[type = 'checkbox'][name = 'product_groups[]'][value = '1-3']")).click();

        WebElement quantity = driver.findElement(By.cssSelector("input[type = 'number'][name = 'quantity']"));
        quantity.click();
        quantity.clear();
        quantity.sendKeys("100");

        WebElement soldOut = driver.findElement(By.cssSelector("select[name = 'sold_out_status_id']"));
        Select select = new Select(soldOut);
        select.selectByVisibleText("-- Select --");


        By btn = By.cssSelector("input[type = 'file']");
        File filePath = new File("src/test/resources/testDuck.jpg");
        driver.findElement(btn).sendKeys(filePath.getAbsolutePath());

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }


        WebElement dateFrom = driver.findElement(By.cssSelector("input[type = 'date'][name = 'date_valid_from']"));
        dateFrom.click();
        dateFrom.sendKeys("01012021");

        WebElement dateTo = driver.findElement(By.cssSelector("input[type = 'date'][name = 'date_valid_to']"));
        dateTo.click();
        dateTo.sendKeys("01012022");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        //Information
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]/a")).click();
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'short_description[en]']")).sendKeys("big yellow duck");
        driver.findElement(By.cssSelector("div[class = 'trumbowyg-editor']")).sendKeys("wonderful and bigest duck in our shop");
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'head_title[en]']")).sendKeys("Big yellow duck");


        //Prices
        driver.findElement(By.xpath("//*[@id=\"content\"]/form/div/ul/li[4]/a")).click();

        WebElement price = driver.findElement(By.cssSelector("input[type = 'number'][name = 'purchase_price']"));
        price.click();
        price.clear();
        price.sendKeys("800");

        WebElement currency = driver.findElement(By.cssSelector("select[name = 'purchase_price_currency_code']"));
        Select select2 = new Select(currency);
        select2.selectByVisibleText("US Dollars");

        WebElement price2 = driver.findElement(By.cssSelector("input[type = 'text'][name = 'prices[USD]']"));
        price2.click();
        price2.sendKeys("1600");

        //Save
        driver.findElement(By.cssSelector("button[type = 'submit'][name = 'save']")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        //Check New duck
        driver.get("http://localhost/litecart/en/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }

        List<WebElement> allDucks = driver.findElements(By.cssSelector("div[class = 'name']"));
        int num = allDucks.size();
        for (int i = 0; i < num; i++) {
            String nameOfDuck = allDucks.get(i).getAttribute("textContent");
            if (nameOfDuck.equals("Test Duck")) {
                System.out.println("New duck is added");
            }

        }

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}

