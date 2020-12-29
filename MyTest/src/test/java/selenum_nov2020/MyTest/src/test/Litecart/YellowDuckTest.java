package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class YellowDuckTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

    }

    @Test
    public void shouldGetTrueInfo() {
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
        List<WebElement> listOfDucks = driver.findElements(By.xpath("//*[@id=\"box-campaigns\"]/div"));
        WebElement yellowDuck = listOfDucks.get(0).findElement(By.className("name"));
        String duckName = yellowDuck.getAttribute("textContent");

        WebElement yellowDuckPrice = listOfDucks.get(0).findElement(By.className("regular-price"));
        String duckPrice = yellowDuckPrice.getAttribute("textContent");

        WebElement yellowDuckSale = listOfDucks.get(0).findElement(By.className("campaign-price"));
        String duckSale = yellowDuckSale.getAttribute("textContent");


//проверяем, что шрифт жирный
        String duckSaleStyle = yellowDuckSale.getCssValue("font-weight");
        if (duckSaleStyle.equals("700")) {
            System.out.println("bold");
        }
//проверяем, что шрифт зачеркнутый
        String duckPriceStyle = yellowDuckPrice.getCssValue("text-decoration-line");
        if (duckPriceStyle.equals("line-through")) {
            System.out.println("line-through");
        }
//определяем цвет скидки на главной странице
        String duckSaleColor = yellowDuckSale.getCssValue("color");
        String duckSaleColor_hex[];
        duckSaleColor_hex = duckSaleColor.replace("rgba(", "").split(",");
        if ((Integer.parseInt(duckSaleColor_hex[0].trim()) > 0) && (duckSaleColor_hex[1].trim().equals("0")) && (duckSaleColor_hex[2].trim().equals("0"))) {
            System.out.println("red");
        }

//определяем цвет цены на главной странице
        String duckPriceColor = yellowDuckPrice.getCssValue("color").trim();
        String duckPriceColor_hex[];
        duckPriceColor_hex = duckPriceColor.replace("rgba(", "").split(",");
        if ((duckPriceColor_hex[0].equals(duckPriceColor_hex[1].trim())) && (duckPriceColor_hex[0].equals(duckPriceColor_hex[2].trim()))) {
            System.out.println("grey");
        }
//сравниваем размер шрифта цены со скидкой и без
        String duckSaleSize = yellowDuckSale.getCssValue("font-size");
        String duckSaleSizeMod;
        duckSaleSizeMod = duckSaleSize.replace("px", "");

        String duckPriceSize = yellowDuckPrice.getCssValue("font-size").trim();
        String duckPriceSizeMod;
        duckPriceSizeMod = duckPriceSize.replace("px", "");
        if ((Double.parseDouble(duckSaleSizeMod)) > (Double.parseDouble(duckPriceSizeMod))) {
            System.out.println("Sale size > Price size");
        }
//переходим на страницу товара
        yellowDuck.click();

        WebElement yellowDuckName = driver.findElement(By.cssSelector("h1[class = 'title']"));
        String duckName2 = yellowDuckName.getAttribute("textContent");

        WebElement yellowDuckPrice2 = driver.findElement(By.className("regular-price"));
        String duckPrice2 = yellowDuckPrice2.getAttribute("textContent");

        WebElement yellowDuckSale2 = driver.findElement(By.className("campaign-price"));
        String duckSale2 = yellowDuckSale2.getAttribute("textContent");

//проверяем, что шрифт жирный
        String duckSaleStyle2 = yellowDuckSale2.getCssValue("font-weight");
        if (duckSaleStyle2.equals("700")) {
            System.out.println("bold");
        }
//проверяем, что шрифт зачеркнутый
        String duckPriceStyle2 = yellowDuckPrice2.getCssValue("text-decoration-line");
        if (duckPriceStyle2.equals("line-through")) {
            System.out.println("line-through");
        }
//определяем цвет скидки на странице товара
        String duckSaleColor2 = yellowDuckSale2.getCssValue("color");
        String duckSaleColor_hex2[];
        duckSaleColor_hex2 = duckSaleColor2.replace("rgba(", "").split(",");
        if ((Integer.parseInt(duckSaleColor_hex2[0].trim()) > 0) && (duckSaleColor_hex2[1].trim().equals("0")) && (duckSaleColor_hex2[2].trim().equals("0"))) {
            System.out.println("red");
        }
//определяем цвет без скидки на странице товара
        String duckPriceColor2 = yellowDuckPrice2.getCssValue("color").trim();
        String duckPriceColor_hex2[];
        duckPriceColor_hex2 = duckPriceColor2.replace("rgba(", "").split(",");
        if ((duckPriceColor_hex2[0].equals(duckPriceColor_hex2[1].trim())) && (duckPriceColor_hex2[0].equals(duckPriceColor_hex2[2].trim()))) {
            System.out.println("grey");
        }

//сравниваем размер шрифта цены со скидкой и без

        String duckSaleSize2 = yellowDuckSale2.getCssValue("font-size").trim();
        String duckSaleSizeMod2;
        duckSaleSizeMod2 = duckSaleSize2.replace("px", "");

        String duckPriceSize2 = yellowDuckPrice2.getCssValue("font-size").trim();
        String duckPriceSizeMod2;
        duckPriceSizeMod2 = duckPriceSize2.replace("px", "");
        if ((Integer.parseInt(duckSaleSizeMod2)) > (Integer.parseInt(duckPriceSizeMod2))) {
            System.out.println("Sale size > Price size");
        }


// сравниваем название товара и цены на главной старнице и на странице товара
        Assert.assertEquals(duckName, duckName2);
        Assert.assertEquals(duckPrice, duckPrice2);
        Assert.assertEquals(duckSale, duckSale2);

    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
