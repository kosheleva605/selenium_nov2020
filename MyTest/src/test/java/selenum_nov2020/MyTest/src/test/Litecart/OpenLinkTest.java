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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class OpenLinkTest {
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
    public void shouldSwitchToNewLinkAndClose() throws InterruptedException {
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
        driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country");
        String originalWindow = driver.getWindowHandle();
        WebElement table = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table[1]"));
        List<WebElement> links = table.findElements(By.cssSelector("a[target = '_blank']"));
        int num = links.size();
        for (int i = 0; i < num; i++) {
            links.get(i).click();
            Thread.sleep(3000);
            Set<String> oldWindows = driver.getWindowHandles();

            Iterator ite = oldWindows.iterator();

            while (ite.hasNext()) {
                String popupHandle = ite.next().toString();
                System.out.println(popupHandle);
                if (!popupHandle.contains(originalWindow)) {
                    driver.switchTo().window(popupHandle);
                    driver.close();
                }
            }
            driver.switchTo().window(originalWindow);

        }


    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
