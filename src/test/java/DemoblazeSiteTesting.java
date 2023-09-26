import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners(listnersErrorHandling.class)
public class DemoblazeSiteTesting {
    WebDriver driver;
    String pageUrl = "https://www.demoblaze.com/";

    Demoblaze demoblazeObject;

    @BeforeClass
    public void startSession() {
        //01open Browser And Navigate
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(pageUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        demoblazeObject = PageFactory.initElements(driver, Demoblaze.class);
    }

    @Test()
    public void Test01Phones() {
        WebElement contcont = driver.findElement(By.xpath("//*[@id='contcont']"));
        WebElement Phones = contcont.findElement(By.xpath("//*[text()='Phones']"));
       // System.out.println("Phones :" + "" + Phones);
        Phones.click();
         assertTrue(Phones.isDisplayed());
    }

    @Test()
    public void Test01Laptops() {
        WebElement contcont = driver.findElement(By.xpath("//*[@id='contcont']"));
        WebElement Laptops = contcont.findElement(By.xpath("//*[text()='Laptops']"));
        // System.out.println("Laptops :" + "" + Laptops);
        Laptops.click();
        assertTrue(Laptops.isDisplayed());
    }

    @Test()
    public void Test01Monitors() {
        WebElement contcont = driver.findElement(By.xpath("//*[@id='contcont']"));
        WebElement Monitors = contcont.findElement(By.xpath("//*[text()='Monitors']"));
        // System.out.println("Monitors :" + "" + Monitors);
        Monitors.click();
        assertTrue(Monitors.isDisplayed());
    }

    @AfterClass
    public void closeSession() {
        //CloseBrowserTest10
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.quit();

    }
}

