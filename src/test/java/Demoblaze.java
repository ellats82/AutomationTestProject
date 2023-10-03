


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class Demoblaze {

    @FindBy(xpath = "//*[@id='contcont']")
    private WebElement contcont;

    @FindBy(xpath = "//*[@id='tbodyid']")
    private WebElement tbodyid;

    @FindBy(xpath = "//*[@id='navbarExample']/ul/li[1]/a")
    private WebElement homeLink;

    @FindBy(id = "nava")
    private WebElement homeTitle;

    @FindBy(xpath = "//*[@id='navbarExample']/ul/li[2]/a")
    private WebElement contactLink;

    @FindBy(xpath = "//*[@id=\"exampleModalLabel\"]")
    private WebElement contactModalTitle;

    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[1]")
    private WebElement contactCloseButton;

    @FindBy(xpath = "//*[@id='navbarExample']/ul/li[3]/a")
    private WebElement aboutUstLink;

    @FindBy(xpath = "//*[@id='videoModalLabel']")
    private WebElement aboutModalTitle;

    @FindBy(xpath = "//*[@id=\"videoModal\"]/div/div/div[3]/button")
    private WebElement aboutUsCloseButton;

    @FindBy(xpath = "//*[@id=\"cartur\"]")
    private WebElement cartLink;

    @FindBy(xpath = "//*[@id=\"page-wrapper\"]/div/div[1]/h2")
    private WebElement cartPageTitle;

    @FindBy(id = "login2")
    private WebElement logInLink;

    @FindBy(id = "logInModalLabel")
    private WebElement logInModalTitle;

    @FindBy(xpath = "//*[@id=\"logInModal\"]/div/div/div[3]/button[1]")
    private WebElement logInCloseButton;

    @FindBy(id = "signin2")
    private WebElement signUpLink;

    @FindBy(id = "signInModalLabel")
    private WebElement signUpModalTitle;

    @FindBy(xpath = "//*[@id=\"signInModal\"]/div/div/div[3]/button[1]")
    private WebElement signUpCloseButton;

    @FindBy(id = "recipient-email")
    private WebElement emailInput;

    @FindBy(id = "recipient-name")
    private WebElement nameInput;

    @FindBy(id = "message-text")
    private WebElement messageText;

    @FindBy(xpath = "//*[@id=\"exampleModal\"]/div/div/div[3]/button[2]")
    private WebElement contactSubmitButton;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[1]/div/div/h4/a")
    private WebElement appleMonitorSelectLink;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[2]/div/div/h4/a")
    private WebElement asusMonitorSelectLink;

    @FindBy(xpath = "//*[@id=\"tbodyid\"]/div[2]/div/a")
    private WebElement addToCartButton;



    public WebElement getPhones() {
        return this.contcont.findElement(By.xpath("//*[text()='Phones']"));
    }
    public WebElement getLaptops() {
        return this.contcont.findElement(By.xpath("//*[text()='Laptops']"));
    }
    public WebElement getMonitors() {
        return this.contcont.findElement(By.xpath("//*[text()='Monitors']"));
    }

    public void clickOnMonitors() {
        WebElement Monitors = this.getMonitors();
        Monitors.click();
        System.out.println("Monitors Button click");
    }

    public boolean checkIfProductArrExist (String[] productNames){
        boolean isFound = false;

        for (int i = 0; i < productNames.length; i++) {
            try {
                WebElement element = this.tbodyid.findElement(By.xpath("//*[text()='" + productNames[i] + "']"));
                isFound = element.isDisplayed();
                System.out.println(productNames[i] + " is found ? [" + isFound + "]");
                if (!isFound) {
                    break;
                }
            }catch (Exception e){
                System.out.println("Exception at checkIfProductArrExist, " +
                                   "while trying to find productNames[i] Exception=>"+e.getMessage());
                isFound = false;
                break;
            }
        }

        return isFound;
    }

    public WebElement getHomeLink() {
        return this.homeLink;
    }

    public String getHomeTitle(){
        return this.homeTitle.getText();
    }

    public WebElement getContactLink() {
        return this.contactLink;
    }

    public WebElement getContactModalTitleElem (){
        return this.contactModalTitle;
    }

    public void clickOnContactCloseButton() {
        this.contactCloseButton.click();
    }

    public WebElement getAboutUstLink() {
        return this.aboutUstLink;
    }

    public WebElement getAboutModalTitle() {
        return this.aboutModalTitle;
    }

    public void clickOnAboutUsCloseButton() {
        this.aboutUsCloseButton.click();
    }


    public WebElement getCartLink() {
        return this.cartLink;
    }

    public WebElement getCartPageTitle() {
        return this.cartPageTitle;
    }

    public WebElement getLogInLink() {
        return this.logInLink;
    }

    public WebElement getLogInModalTitle() {
        return this.logInModalTitle;
    }

    public void clickOnLogInCloseButton() {
        this.logInCloseButton.click();
    }
    public WebElement getSignUpLink() {
        return this.signUpLink;
    }

    public WebElement getSignUpModalTitle() {
        return this.signUpModalTitle;
    }

    public void clickOnSignUpCloseButton() {
        this.signUpCloseButton.click();
    }

    public WebElement getEmailInput() {
        return this.emailInput;
    }

    public WebElement getNameInput() {
        return nameInput;
    }

    public WebElement getMessageText() {
        return messageText;
    }

    public void clickOnContactSubmitButton() {
        this.contactSubmitButton.click();
        System.out.println("contact Submit Button click");
    }

    public void clickOnAppleMonitorSelectLink() {
        this.appleMonitorSelectLink.click();
    }

    public void clickOnAsusMonitorSelectLink() {
        this.asusMonitorSelectLink.click();
    }

    public void clickOnAddToCartButton() {
        this.addToCartButton.click();
    }


}


