

import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
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


@Listeners(listnersErrorHandlingEllaTshorny.class)
public class AnswersTestEllaTshorny {
    WebDriver driver;
    String pageUrl = "https://atidcollege.co.il/Xamples/pizza";
    String expectedPrice = "$7.50";
    String firstName = "ella";
    String lastName = "tshorny";
    String checkPrice = "$10.50";
    String couponNum;
    PizzaMainPage pizzaMainPage;

    @BeforeClass
    public void startSession() {
        //01open Browser And Navigate
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(pageUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        pizzaMainPage = PageFactory.initElements(driver, PizzaMainPage.class);
    }

    @Test()
    public void Test01() {
        String actualPrice = pizzaMainPage.getActualPrice();
        System.out.println("Actual Price :" + "" + actualPrice);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test()
    public void Test02() {
        pizzaMainPage.Step03InsertingFirstAndLastName(firstName, lastName);
        pizzaMainPage.Step04ComboBoxDelivery("Delivery|3");
        //05DeliveryPriceNow
        String priceAfterDelivery = pizzaMainPage.getPriceAfterDelivery();
        System.out.println("Price After Delivery :" + "" + priceAfterDelivery);
        assertEquals(checkPrice, priceAfterDelivery);
    }

    @Test()
    public void Test03() {
        //06NumCouponInAdditionalComments
        WebElement iframe = pizzaMainPage.getIframe();
        driver.switchTo().frame(iframe);
        couponNum = pizzaMainPage.getCouponNum();
        driver.switchTo().defaultContent();
        pizzaMainPage.getTextarea().sendKeys(couponNum);
        System.out.println("Coupon :" + "" + couponNum);
    }

    @Test()
    public void Test04() {
        pizzaMainPage.Step07ButtonSubmit();
        //08WindowAlert09CloseAlert
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());
        System.out.println("My text: " + firstName + " " + lastName + " " + couponNum);
        assertEquals(alert.getText(), firstName + " " + lastName + " " + couponNum);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        alert.accept();
    }

    @AfterClass
    public void closeSession() {
        //CloseBrowserTest10
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.quit();

    }
}

