import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
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
        WebElement Phones = demoblazeObject.getPhones();
        System.out.println("Phones :" + "" + Phones.getText());
        Phones.click();

        String []names={"Samsung galaxy s6","Nokia lumia 1520"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test()
    public void Test01Laptops() {
        WebElement Laptops = demoblazeObject.getLaptops();
        System.out.println("Laptops :" + "" + Laptops.getText());
        Laptops.click();

        String []names={"Sony vaio i5","MacBook air"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test()
    public void Test01Monitors() {
        WebElement Monitors = demoblazeObject.getMonitors();
        System.out.println("Monitors :" + "" + Monitors.getText());
        Monitors.click();

        String []names={"Apple monitor 24","ASUS Full HD"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test()
    public void Test02Home() {
        WebElement homeLink = demoblazeObject.getHomeLink();
        homeLink.click();
        System.out.println("linkText: " + homeLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        String title = demoblazeObject.getHomeTitle();
        System.out.println("title: " + title);
        Assert.assertTrue(title.contains("PRODUCT STORE"));
    }

    @Test()
    public void Test02Contact() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement modalTitle = demoblazeObject.getContactModalTitleElem();
        System.out.println("modalTitle: " + modalTitle.getText());
        Assert.assertTrue(modalTitle.isDisplayed());
    }

    @Test()
    public void Test02AboutUs() {
        WebElement aboutUstLink = demoblazeObject.getAboutUstLink();
        aboutUstLink.click();
        System.out.println("linkText: " + aboutUstLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement modalTitle =demoblazeObject.getAboutModalTitle();
        System.out.println("modalTitle: " + modalTitle.getText());
        Assert.assertTrue(modalTitle.isDisplayed());
    }

    @Test()
    public void Test02Cart() {
        WebElement cartLink = demoblazeObject.getCartLink();
        System.out.println("linkText: " + cartLink.getText());
        cartLink.click();

        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        WebElement cartPageTitle = demoblazeObject.getCartPageTitle();
        System.out.println("PageTitle: " + cartPageTitle.getText());

        Assert.assertEquals(cartPageTitle.getText(),"Products");
    }

    @Test()
    public void Test02LogIn() {
        WebElement logInLink = demoblazeObject.getLogInLink();
        logInLink.click();
        System.out.println("linkText: " + logInLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement logInModalTitle = demoblazeObject.getLogInModalTitle();
        System.out.println("modalTitle: " + logInModalTitle.getText());
        Assert.assertTrue(logInModalTitle.isDisplayed());
    }

    @Test()
    public void Test02SignUp() {
        WebElement signUpLink = demoblazeObject.getSignUpLink();
        signUpLink.click();
        System.out.println("linkText: " + signUpLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement signUpModalTitle = demoblazeObject.getSignUpModalTitle();
        System.out.println("modalTitle: " + signUpModalTitle.getText());
        Assert.assertTrue(signUpModalTitle.isDisplayed());
    }

    @Test()
    public void Test03ContactFields() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement emailInput = demoblazeObject.getEmailInput();
        WebElement nameInput = demoblazeObject.getNameInput();
        WebElement messageText = demoblazeObject.getMessageText();

        Assert.assertTrue(emailInput.isDisplayed() && nameInput.isDisplayed() && messageText.isDisplayed() );
    }

    @Test()
    public void Test03ContactSendMessage() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement emailInput = demoblazeObject.getEmailInput();
        WebElement nameInput = demoblazeObject.getNameInput();
        WebElement messageText = demoblazeObject.getMessageText();
        emailInput.sendKeys("ella@gmail.com");
        nameInput.sendKeys("ella");
        messageText.sendKeys("sucsses contact test");
        demoblazeObject.clickOnContactSubmitButton();

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());

        Assert.assertEquals(alert.getText(),"Thanks for the message!!");
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        alert.accept();

    }

    @Test()
    public void Test04ChoseProducts() {
        demoblazeObject.clickOnMonitors();

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        demoblazeObject.clickOnAppleMonitorSelectLink();

        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        demoblazeObject.clickOnAddToCartButton();

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());

        Assert.assertEquals(alert.getText(),"Product added");
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        alert.accept();

        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement homeLink = demoblazeObject.getHomeLink();
        System.out.println("linkText: " + homeLink.getText());
        homeLink.click();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        demoblazeObject.clickOnMonitors();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        demoblazeObject.clickOnAppleMonitorSelectLink();

//        wait till page loaded
        Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

        @AfterClass
    public void closeSession() {

        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.quit();

    }
}

