import com.google.common.util.concurrent.Uninterruptibles;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


@Listeners(listnersErrorHandling.class)
public class DemoblazeCartSiteTesting {
    WebDriver driver;

    String pageUrl = getData("URL");

    Demoblaze demoblazeObject;

    String expectedPopUpTitle=getData("EXPECTED_Success");


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

    @Test
    public void test01VerifyLogin(){
        ArrayList<HashMap<String, String>> cartSiteDataMapArray = getDataMap();
        int ind = getRandomNumberUsingNextInt(0, cartSiteDataMapArray.size());
        System.out.println("INDEX: " + ind);

        String Name=cartSiteDataMapArray.get(ind).get("Name");
        String Country=cartSiteDataMapArray.get(ind).get("Country");
        String City=cartSiteDataMapArray.get(ind).get("City");
        String Credit_card=cartSiteDataMapArray.get(ind).get("Credit_card");
        String Month=cartSiteDataMapArray.get(ind).get("Month");
        String Year=cartSiteDataMapArray.get(ind).get("Year");

        driver.findElement(By.xpath("//*[@id='page-wrapper']/div/div[2]/button")).click();
        driver.findElement(By.xpath("//*[@id='name']")).sendKeys(Name);
        driver.findElement(By.xpath("//*[@id='country']")).sendKeys(Country);
        driver.findElement(By.xpath("//*[@id='city']")).sendKeys(City);
        driver.findElement(By.xpath("//*[@id='card']")).sendKeys(Credit_card);
        driver.findElement(By.xpath("//*[@id='month']")).sendKeys(Month);
        driver.findElement(By.xpath("//*[@id='year']")).sendKeys(Year);
        driver.findElement(By.xpath("//*[@id='orderModal']/div/div/div[3]/button[2]")).click();

        String Success=driver.findElement(By.xpath("//*[text()='Thank you for your purchase!']")).getText();
        Assert.assertEquals(Success,expectedPopUpTitle);
    }

    public ArrayList<HashMap<String, String>> getDataMap()
    {
        ArrayList<HashMap<String, String>> cartSiteDataMapArray = null;
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("src/test/java/sauce_data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        }
        catch(Exception e)
        {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        NodeList items = doc.getDocumentElement().getElementsByTagName("Data").item(0).getChildNodes();
        cartSiteDataMapArray = new ArrayList<>();
        for(int i=0; i<items.getLength(); i++){
            NodeList nodeData = items.item(i).getChildNodes();
            boolean isEmptyMap = true;
            HashMap<String, String> cartSiteDataMap = new HashMap<String, String>();
            for(int j=0; j<nodeData.getLength(); j++) {
                if(nodeData!=null && nodeData.item(j)!=null && nodeData.item(j).getNodeName()!=null &&
                        (nodeData.item(j).getNodeName().equals("Name") ||
                         nodeData.item(j).getNodeName().equals("Country") ||
                         nodeData.item(j).getNodeName().equals("City") ||
                         nodeData.item(j).getNodeName().equals("Credit_card") ||
                         nodeData.item(j).getNodeName().equals("Month") ||
                         nodeData.item(j).getNodeName().equals("Year"))) {
                    isEmptyMap = false;
                    cartSiteDataMap.put(nodeData.item(j).getNodeName(), nodeData.item(j).getTextContent());
                }
            }
            if(!isEmptyMap) {
                cartSiteDataMapArray.add(cartSiteDataMap);
            }
        }
        return cartSiteDataMapArray;
    }

    public String getData (String nodeName)
    {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("src/test/java/sauce_data.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try
        {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        }
        catch(Exception e)
        {
            System.out.println("Exception in reading XML file: " + e);
        }
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(nodeName).item(0).getTextContent();
    }



    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }



    @AfterClass
    public void closeSession() {
        //CloseBrowserTest10
        Uninterruptibles.sleepUninterruptibly(4, TimeUnit.SECONDS);
        driver.quit();

    }
}

