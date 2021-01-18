package selenum_nov2020.MyTest.src.test.Litecart;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddCartTest {
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
    public void shouldUpdateCart() throws InterruptedException {
        driver.get("https://litecart.stqa.ru/en/");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ie) {
        }
        for (int i = 0; i < 3; i++) {

            List<WebElement> allDucks = driver.findElements(By.cssSelector("li[class = 'product column shadow hover-light']"));
            WebElement duck = allDucks.get(0);
            duck.findElement(By.cssSelector("a[class = 'link']")).click();
            wait.until(presenceOfElementLocated(By.className("title")));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
            }
            try {
                WebElement sizeOfDuck = driver.findElement(By.cssSelector("select[name = 'options[Size]']"));
                Select select = new Select(sizeOfDuck);
                select.selectByVisibleText("Small");
            } catch (NoSuchElementException nsee) {
                System.out.println("No element of that id present!");
            }
            WebElement cart = driver.findElement(By.cssSelector("button[name = 'add_cart_product']"));
            cart.click();

            //driver.navigate().refresh();
            String numOfProduct = driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]/span[1]")).getText();
            int num = Integer.parseInt(numOfProduct);
            num = num + 1;
            numOfProduct = Integer.toString(num);
            //System.out.println(numOfProduct);
            wait.until(textToBe(By.xpath("//*[@id=\"cart\"]/a[2]/span[1]"), numOfProduct));
        }

        String numOfProduct = driver.findElement(By.xpath("//*[@id=\"cart\"]/a[2]/span[1]")).getText();
        int num = Integer.parseInt(numOfProduct);

        driver.get("https://litecart.stqa.ru/en/checkout");
        WebElement table = driver.findElement(By.cssSelector("table[class = 'dataTable rounded-corners']"));
        int rows = table.findElements(By.tagName("tr")).size();
        //System.out.println(rows);
        boolean checkCart = false;
        do {
            WebElement update = driver.findElement(By.cssSelector("input[name = 'quantity']"));
            update.clear();
            update.sendKeys("0");
            driver.findElement(By.cssSelector("button[name = 'update_cart_item']")).click();
            Thread.sleep(1000);
            try {
                table = driver.findElement(By.cssSelector("table[class = 'dataTable rounded-corners']"));
                //System.out.println(table.findElements(By.tagName("tr")).size());
                rows = rows - 1;
                if ((rows == table.findElements(By.tagName("tr")).size()) || (!table.isDisplayed())) {
                    System.out.println("table update");
                } else {
                    System.out.println("ALARM!!!!");
                }
            }
            catch (NoSuchElementException nsee) {
               System.out.println("No element of that id present_3!");

            }
            try {
                WebElement cartIsEmpty = driver.findElement(By.xpath("//*[@id=\"checkout-cart-wrapper\"]/p[1]/em"));
                String cartText = cartIsEmpty.getAttribute("textContent");
//                System.out.println(cartText);
                checkCart = cartText.equals("There are no items in your cart.");
            } catch (NoSuchElementException nsee) {
                System.out.println("No element of that id present_2!");
            }

            }
         while (!checkCart);
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}
