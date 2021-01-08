package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;


public class AuthorisationTest {

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
    public void shouldAuthorizationClient() {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }
        driver.get("http://localhost/litecart/admin/?app=settings&doc=security&setting_group_key=store_info&page=1&action=edit&key=captcha_enabled");

        driver.findElement(By.cssSelector("input[type = 'radio'][value = '0']")).click();
        driver.findElement(By.cssSelector("button[type = 'submit'][value = 'Save']")).click();
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.linkText("New customers click here")).click();
        wait.until(ExpectedConditions.titleIs("Create Account | My Store"));
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'firstname']")).sendKeys("john");
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'lastname']")).sendKeys("doe");
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'address1']")).sendKeys("wall street");
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'postcode']")).sendKeys("10001");
        driver.findElement(By.cssSelector("input[type = 'text'][name = 'city']")).sendKeys("new york");

        driver.findElement(By.cssSelector("span[class = 'select2-selection__arrow']")).click();
        driver.findElement(By.cssSelector("input[class = 'select2-search__field']")).sendKeys("United States" + Keys.ENTER);

        Random randomGenerator = new Random();
        WebElement email = driver.findElement(By.cssSelector("input[type = 'email'][name = 'email']"));
        int randomInt = randomGenerator.nextInt(1000);
        email.sendKeys("username" + randomInt + "@gmail.com");
        String newEmail = driver.findElement(By.cssSelector("input[type = 'email'][name = 'email']")).getAttribute("value");

        Select select = new Select(driver.findElement(By.cssSelector("select[name = 'zone_code']")));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].selectedIndex = 5", select);

        driver.findElement(By.cssSelector("input[type = 'tel'][name = 'phone']")).sendKeys("+11111111222");

        driver.findElement(By.cssSelector("input[type = 'password'][name = 'password']")).sendKeys("test");
        driver.findElement(By.cssSelector("input[type = 'password'][name = 'confirmed_password']")).sendKeys("test");
        String newPsw = driver.findElement(By.cssSelector("input[type = 'password'][name = 'password']")).getAttribute("value");

        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }

        driver.findElement(By.cssSelector("button[type = 'submit'][name = 'create_account']")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }

       driver.get("http://localhost/litecart/en/logout");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }


        WebElement login2 = driver.findElement(By.cssSelector("input[type = 'text'][name = 'email']"));
        login2.clear();
        login2.sendKeys(newEmail);
        WebElement psw = driver.findElement(By.cssSelector("input[type = 'password'][name = 'password']"));
        psw.clear();
        psw.sendKeys(newPsw);

        driver.findElement(By.cssSelector("button[type = 'submit'][name = 'login'][value = 'Login']")).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        driver.get("http://localhost/litecart/en/logout");
    }
        @After
        public void stop() {
            driver.quit();
            driver = null;
        }
    }
