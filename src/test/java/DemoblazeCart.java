import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class DemoblazeCart {

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/tr[1]/td[2]")
    private WebElement asusMonitor;


    @FindBy(xpath = "//*[@id=\"tbodyid\"]/tr[2]/td[2]")
    private WebElement appleMonitor;

    @FindBy(xpath = "//*[@id='page-wrapper']/div/div[2]/button")
    private WebElement placeOrder;

    @FindBy(id="name")
    private WebElement name;

    @FindBy(id="country")
    private WebElement country;

    @FindBy(id="city")
    private WebElement city;

    @FindBy(id="card")
    private WebElement credit_card;

    @FindBy(id="month")
    private WebElement mont;

    @FindBy(id="year")
    private WebElement year;

    @FindBy(xpath = "//*[@id='orderModal']/div/div/div[3]/button[2]")
    private WebElement confirmOrder;

    @FindBy(xpath = "//*[text()='Thank you for your purchase!']")
    private WebElement successTitle;




    public WebElement getAsusMonitor() {
        return asusMonitor;
    }

    public WebElement getAppleMonitor() {
        return appleMonitor;
    }

    public String getExpectedPopUpTitle(){
        return getData("EXPECTED_Success");
    }

    public WebElement getName() {
        return name;
    }

    public WebElement getCountry() {
        return country;
    }

    public WebElement getCity() {
        return city;
    }

    public WebElement getCredit_card() {
        return credit_card;
    }

    public WebElement getMont() {
        return mont;
    }

    public WebElement getYear() {
        return year;
    }

    public void clickOnPlaceOrder() {
         placeOrder.click();
    }

    public void clickConfirmOrder() {
         confirmOrder.click();
    }

    public String getSuccessTitle() {
        return successTitle.getText();
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
}
