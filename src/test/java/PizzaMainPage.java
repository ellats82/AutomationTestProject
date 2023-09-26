


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;


public class PizzaMainPage {

    @FindBy(xpath = "//li[@id=\"field_5_2\"]/div/span")
    private WebElement actualPrice;

    @FindBy(xpath = "//li[@id=\"field_5_2\"]/div/span")
    private WebElement priceAfterDelivery;


    @FindBy(xpath = "//input[@id=\"input_5_22_3\"]")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id=\"input_5_22_6\"]")
    private WebElement lastName;

    @FindBy(xpath = "//select[@id=\"input_5_21\"]")
    private WebElement isThisPickUpOrDeliverySelect;

    @FindBy(xpath = "//li[@id=\"field_5_19\"]/iframe")
    private WebElement iframe;

    @FindBy(id = "coupon_Number")
    private WebElement couponNum;

    @FindBy(xpath = "//textarea[@id=\"input_5_20\"]")
    private WebElement textarea;

    @FindBy(id = "gform_submit_button_5")
    private WebElement submitButton;


    public String getActualPrice() {
       return this.actualPrice.getText();
    }

    public void Step03InsertingFirstAndLastName(String firstName, String lastName) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        System.out.println("First And Last Name : " + "" + firstName + " " + lastName);

    }

    public void Step04ComboBoxDelivery(String selctedValue) {
        Select isThisPickUpOrDelivery = new Select(isThisPickUpOrDeliverySelect);
        isThisPickUpOrDelivery.selectByValue(selctedValue);
        WebElement option = isThisPickUpOrDelivery.getFirstSelectedOption();
        System.out.println("Is This Pick Up or Delivery? : " + "" + option.getText());
    }
    public String getPriceAfterDelivery() {
        return this.priceAfterDelivery.getText();
    }

    public WebElement getIframe() {
        return this.iframe;
    }

    public String getCouponNum() {
        return this.couponNum.getText();
    }

    public WebElement getTextarea() {
        return this.textarea;
    }

    public void Step07ButtonSubmit() {
        submitButton.click();
        System.out.println("Submit your order button click");
    }


}


