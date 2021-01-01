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

public class LoginLitecartTest {

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
    public void shouldGetCountriesA_Z() {
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

    @Test
    public void shouldGetZonesOfCountriesA_Z() {
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
        List<WebElement> allCountries = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[6]"));
        List<WebElement> allCoun1 = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[5]/a"));
        int numberOfCountries = allCountries.size();
        String prev;
        for (int i = 0; i < numberOfCountries; i++) {
            prev = allCountries.get(i).getAttribute("textContent");


            if (!prev.equals("0")) {
                allCoun1.get(i).click();
                List<WebElement> allCountZones = driver.findElements(By.xpath("//*[@id=\"table-zones\"]/tbody/tr/td[3]"));
                int numberOfZones = allCountZones.size();
                boolean num1 = true;
                String next1;
                String prev1;
                for (int j = 0; j < (numberOfZones - 2); j++) {
                    prev1 = allCountZones.get(j).getAttribute("textContent");
                    next1 = allCountZones.get(j + 1).getAttribute("textContent");

                    if (prev1.compareTo(next1) > 0) {
                        num1 = false;
                    }

                }
                System.out.println(num1);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
                allCountries = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[6]"));
                allCoun1 = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[5]/a"));
            } else {
                System.out.println("Zones = 0");
            }


        }
    }

    @Test
    public void shouldGetGeoZonesA_Z() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> allZones = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[3]/a"));
        int numberOfZones = allZones.size();
        for (int i = 0; i < (numberOfZones); i++) {
            allZones.get(i).click();
            List<WebElement> zones = driver.findElements(By.xpath("//*[@id=\"table-zones\"]/tbody/tr/td[3]/select/option"));
            int numZones = zones.size();
            System.out.println(zones.size());
            List<String> listOfZones = new ArrayList<String>();

            for (int j = 0; j < numZones; j++) {
                if (zones.get(j).isSelected()) {
                    listOfZones.add(zones.get(j).getAttribute("textContent"));
                }
            }
            System.out.println(listOfZones);
            boolean num = true;
            String next;
            String prev;
            int listZones = listOfZones.size();
            if (listZones > 1) {
                for (int j = 0; j < (listZones - 1); j++) {

                    if (listOfZones.get(j).compareTo(listOfZones.get(j + 1)) > 0) {
                        num = false;
                    }
                }
                System.out.println(num);
            } else {
                System.out.println("Zones < 2");
            }

            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
            allZones = driver.findElements(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr/td[3]/a"));
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}


