package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MessageTest extends TestBase {


    @Test
    public void shouldGetNoMessage() throws InterruptedException {
        driver.get("http://localhost/litecart/admin/login.php");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        try {
            wait.until(ExpectedConditions.titleContains("My Store"));
        } catch (NoSuchElementException error) {
            driver.quit();
        }

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");
        WebElement table = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table"));
        List<WebElement> products = table.findElements(By.cssSelector("tr[class = 'row']"));
        int num = products.size();
        for (int i = 0; i < num; i++) {
            try {
                products.get(i).findElement(By.cssSelector("a[href*=\"product_id=\"]")).click();
            }
            catch (NoSuchElementException nsee) {
                System.out.println("No element of that id present!");
            }
            Thread.sleep(2000);
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            int num2 = logs.size();
            if(num2 == 0){
                System.out.println("No messages");
            }
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=2");
            Thread.sleep(2000);
            table = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table"));
            products = table.findElements(By.cssSelector("tr[class = 'row']"));

        }
    }
}
