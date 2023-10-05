import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners(listnersErrorHandling.class)
public class DemoblazeSiteTesting {
    WebDriver driver;
    String pageUrl = "https://www.demoblaze.com/";

    Demoblaze demoblazeObject;
    DemoblazeCart demoblazeCartObject;


    @BeforeClass
    public void startSession() {
        //01open Browser And Navigate
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(pageUrl);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        demoblazeObject = PageFactory.initElements(driver, Demoblaze.class);
        demoblazeCartObject = PageFactory.initElements(driver, DemoblazeCart.class);
    }

    @Test(description = "Test01 Click On Phones")
    @Description("Click On Phones From Categories List On Home Page")
    public void Test01Phones() {
        WebElement Phones = demoblazeObject.getPhones();
        System.out.println("Phones :" + "" + Phones.getText());
        Phones.click();

        String []names={"Samsung galaxy s6","Nokia lumia 1520"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test(description = "Test02 Click On Laptops")
    @Description("Click On Laptops From Categories List On Home Page")
    public void Test01Laptops() {
        WebElement Laptops = demoblazeObject.getLaptops();
        System.out.println("Laptops :" + "" + Laptops.getText());
        Laptops.click();

        String []names={"Sony vaio i5","MacBook air"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test(description = "Test03 Click On Monitors")
    @Description("Click On Monitors From Categories List On Home Page")
    public void Test01Monitors() {
        WebElement Monitors = demoblazeObject.getMonitors();
        System.out.println("Monitors :" + "" + Monitors.getText());
        Monitors.click();

        String []names={"Apple monitor 24","ASUS Full HD"};
        assertTrue(demoblazeObject.checkIfProductArrExist(names));
    }

    @Test(description = "Test04 Click On Home Link")
    @Description("Click On Home Link From Header On Home Page")
    public void Test02Home() {
        WebElement homeLink = demoblazeObject.getHomeLink();
        homeLink.click();
        System.out.println("linkText: " + homeLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        String title = demoblazeObject.getHomeTitle();
        System.out.println("title: " + title);
        Assert.assertTrue(title.contains("PRODUCT STORE"));
    }

    @Test(description = "Test05 Click On Contact Link")
    @Description("Click On Contact Link From Header ON Home Page")
    public void Test02Contact() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement modalTitle = demoblazeObject.getContactModalTitleElem();
        System.out.println("modalTitle: " + modalTitle.getText());
        Assert.assertTrue(modalTitle.isDisplayed());
        demoblazeObject.clickOnContactCloseButton();
    }

    @Test(description = "Test06 Click On About Us Link")
    @Description("Click On About Us Link From Header On Home Page")
    public void Test02AboutUs() {
        WebElement aboutUstLink = demoblazeObject.getAboutUstLink();
        aboutUstLink.click();
        System.out.println("linkText: " + aboutUstLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement modalTitle =demoblazeObject.getAboutModalTitle();
        System.out.println("modalTitle: " + modalTitle.getText());
        Assert.assertTrue(modalTitle.isDisplayed());
        demoblazeObject.clickOnAboutUsCloseButton();
    }

    @Test(description = "Test07 Click On Cart Link")
    @Description("Click On Cart Link From Header On Home Page")
    public void Test02Cart() {
        WebElement cartLink = demoblazeObject.getCartLink();
        System.out.println("linkText: " + cartLink.getText());
        cartLink.click();

        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

        WebElement cartPageTitle = demoblazeObject.getCartPageTitle();
        System.out.println("PageTitle: " + cartPageTitle.getText());

        Assert.assertEquals(cartPageTitle.getText(),"Products");
        WebElement homeLink = demoblazeObject.getHomeLink();
        homeLink.click();

    }

    @Test(description = "Test08 Click On Log In Link")
    @Description("Click On Log In Link From Header On Home Page")
    public void Test02LogIn() {
        WebElement logInLink = demoblazeObject.getLogInLink();
        logInLink.click();
        System.out.println("linkText: " + logInLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement logInModalTitle = demoblazeObject.getLogInModalTitle();
        System.out.println("modalTitle: " + logInModalTitle.getText());
        Assert.assertTrue(logInModalTitle.isDisplayed());
        demoblazeObject.clickOnLogInCloseButton();
    }

    @Test(description = "Test09 Click On Sign Up Link")
    @Description("Click On Sign Up Link From Header On Home Page")
    public void Test02SignUp() {
        WebElement signUpLink = demoblazeObject.getSignUpLink();
        signUpLink.click();
        System.out.println("linkText: " + signUpLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement signUpModalTitle = demoblazeObject.getSignUpModalTitle();
        System.out.println("modalTitle: " + signUpModalTitle.getText());
        Assert.assertTrue(signUpModalTitle.isDisplayed());
        demoblazeObject.clickOnSignUpCloseButton();
    }

    @Test(description = "Test10 Verify Existence Of Contact Fields")
    @Description("Verify Existence Of Contact Fields: Email , Name , Message")
    public void Test03ContactFields() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        WebElement emailInput = demoblazeObject.getEmailInput();
        WebElement nameInput = demoblazeObject.getNameInput();
        WebElement messageText = demoblazeObject.getMessageText();

        Assert.assertTrue(emailInput.isDisplayed() && nameInput.isDisplayed() && messageText.isDisplayed() );
        demoblazeObject.clickOnContactCloseButton();
    }

    @Test(description = "Test11 Fill Up Contact Fields: Email , Name , Message")
    @Description("Verify Sent Message After Filling Contact Fields & Submitting Them ")
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

        String alertText =  alert.getText();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        alert.accept();
        Assert.assertEquals(alertText,"Thanks for the message!!");


    }

    @Test(description = "Test12 (Bonus) Not Filling Up Contact Fields: Email , Name , Message")
    @Description("Not Filling Up Contact Fields Click On Sent Message Button And Expect No Successes Alert ")
    public void Test03ContactSendMessageWithEmptyFields() {
        WebElement contactLink = demoblazeObject.getContactLink();
        contactLink.click();
        System.out.println("linkText: " + contactLink.getText());
        Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);

        demoblazeObject.clickOnContactSubmitButton();

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());
        String alertText =  alert.getText();
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
        alert.accept();

        Assert.assertNotEquals(alertText,"Thanks for the message!!");
    }

    @Test(description = "Test13 (Included Bonus of calculated price) Chose Products And Place Order")
    @Description("Chose Products , verify them & Place Order")
    public void Test04ChoseProductsAndPlaceOrder() {
        long appleMonitorPrice = choseAppleMonitor();

        WebElement homeLink = demoblazeObject.getHomeLink();
        System.out.println("linkText: " + homeLink.getText());
        homeLink.click();
        waitTilDataLoad(3);

        long asusMonitorPrice = choseAsusMonitor();

        verifyAppleAndAsusMonitorsPresentAtCart();
        System.out.println("Bonus - verify total price: " +
                "total price - ["+demoblazeCartObject.getTotalPrice().getText()+"], " +
                "calculated total price - ["+(appleMonitorPrice+asusMonitorPrice)+"]");

        assertEquals((appleMonitorPrice+asusMonitorPrice)+"",demoblazeCartObject.getTotalPrice().getText());

        ArrayList<HashMap<String, String>> cartSiteDataMapArray = demoblazeCartObject.getDataMap();
        int ind = demoblazeCartObject.getRandomNumberUsingNextInt(0, cartSiteDataMapArray.size());
        System.out.println("INDEX: " + ind);

        String Name=cartSiteDataMapArray.get(ind).get("Name");
        String Country=cartSiteDataMapArray.get(ind).get("Country");
        String City=cartSiteDataMapArray.get(ind).get("City");
        String Credit_card=cartSiteDataMapArray.get(ind).get("Credit_card");
        String Month=cartSiteDataMapArray.get(ind).get("Month");
        String Year=cartSiteDataMapArray.get(ind).get("Year");

        demoblazeCartObject.clickOnPlaceOrder();
        demoblazeCartObject.getName().sendKeys(Name);
        demoblazeCartObject.getCountry().sendKeys(Country);
        demoblazeCartObject.getCity().sendKeys(City);
        demoblazeCartObject.getCredit_card().sendKeys(Credit_card);
        demoblazeCartObject.getMont().sendKeys(Month);
        demoblazeCartObject.getYear().sendKeys(Year);
        demoblazeCartObject.clickConfirmOrder();

        Assert.assertEquals(demoblazeCartObject.getSuccessTitle(),demoblazeCartObject.getExpectedPopUpTitle());

    }

    @Step ("Chose Apple Monitor")
    public long choseAppleMonitor (){
        demoblazeObject.clickOnMonitors();

        waitTilDataLoad(3);

        long price = getSelectedProductPrice(demoblazeObject.getAppleMonitorSelectLink());
        demoblazeObject.clickOnAppleMonitorSelectLink();

        waitTilDataLoad(5);

        demoblazeObject.clickOnAddToCartButton();

        waitTilDataLoad(3);
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text is: " + alert.getText());

        Assert.assertEquals(alert.getText(),"Product added");
        waitTilDataLoad(4);
        alert.accept();

        waitTilDataLoad(3);

        return price;
    }

    @Step ("Chose Asus Monitor")
    public long choseAsusMonitor (){
        demoblazeObject.clickOnMonitors();
        waitTilDataLoad(3);

        long price = getSelectedProductPrice(demoblazeObject.getAsusMonitorSelectLink());
        demoblazeObject.clickOnAsusMonitorSelectLink();

        waitTilDataLoad(5);

        demoblazeObject.clickOnAddToCartButton();

        waitTilDataLoad(3);

        Alert alert = driver.switchTo().alert();
        System.out.println("Alert2 text is: " + alert.getText());

        Assert.assertEquals(alert.getText(),"Product added");
        waitTilDataLoad(4);
        alert.accept();

        waitTilDataLoad(3);

        return price;
    }

    @Step ("Verify Apple & Asus Monitors Present At Cart")
    public void verifyAppleAndAsusMonitorsPresentAtCart (){
        WebElement cartLink = demoblazeObject.getCartLink();
        cartLink.click();

        waitTilDataLoad(3);

        WebElement asusMonitor = demoblazeCartObject.getAsusMonitor();
        WebElement appleMonitor = demoblazeCartObject.getAppleMonitor();

        assertTrue(asusMonitor.isDisplayed() && appleMonitor.isDisplayed());

    }

    private long getSelectedProductPrice(WebElement productElement){
        WebElement productElementPapa = productElement.findElement(By.xpath("./.."));
        System.out.println("productElement Papa: "+productElementPapa.getAttribute("outerHTML"));
        WebElement nextToPapaProductElement = productElementPapa.findElement(By.xpath("following-sibling::*"));
        System.out.println("nextToPapaAsus: "+nextToPapaProductElement.getAttribute("outerHTML"));
        if(nextToPapaProductElement.getText()!=null && !nextToPapaProductElement.getText().equals("")) {
            System.out.println("price: " + (nextToPapaProductElement.getText()).replace("$", ""));
            try {
                Long price = Long.parseLong((nextToPapaProductElement.getText()).replace("$", ""));
                if(price!=null){
                    return price;
                }
            }catch (Exception ignored){}
        }

        return 0;
    }

    private void waitTilDataLoad(int seconds){
        Uninterruptibles.sleepUninterruptibly(seconds, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

        @AfterClass
    public void closeSession() {

        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.quit();

    }
}

