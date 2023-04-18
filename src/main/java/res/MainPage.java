package res;

import org.openqa.selenium.By;

public class MainPage {
    public static final By btnCheckout = By.xpath("*//form/button");
    public static final By btnRedeem = By.xpath(".//*[@id=\"promoCode\"]/following::button[1]");

    public static final By inFirstName = By.id("firstName");
    public static final By inLastName = By.id("lastName");
    public static final By inCCName = By.id("cc-name");
    public static final By inCCNumber = By.id("cc-number");
    public static final By inCCExpiration = By.id("cc-expiration");
    public static final By inCCCVV = By.id("cc-cvv");
    public static final By inPromocode = By.id("promoCode");

    public static final By elLoading = By.id("loading");

    public static final By rbCreditCard = By.id("credit");

    public static final By txFirstNameError = By.xpath("//*[@id=\"firstName\"]/following-sibling::div");
    public static final By txLastNameError = By.xpath("//*[@id=\"lastName\"]/following-sibling::div");
    public static final By txCCName = By.xpath("//*[@id=\"cc-name\"]/following-sibling::div");
    public static final By txCCNumber = By.xpath("//*[@id=\"cc-number\"]/following-sibling::div");
    public static final By txCCExpiration = By.xpath("//*[@id=\"cc-expiration\"]/following-sibling::div");
    public static final By txCCCVV = By.xpath("//*[@id=\"cc-cvv\"]/following-sibling::div");
    public static final By txYourCart = By.xpath(".//*[@id=\"itemsList\"]/preceding::h4/span[1]");
    public static final By txAmountOfPurchases = By.xpath(".//*[@id=\"itemsList\"]/preceding::h4/span[2]");
    public static final By txNameOfFirstPurchase = By.xpath("(.//*[@id=\"itemsList\"]//h6)[1]");
    public static final By txDescriptionOfFirstElement = By.xpath("(.//*[@id=\"itemsList\"]//small)[1]");
    public static final By txPriceOfFirstPurchase = By.xpath("(.//*[@id=\"itemsList\"]//span)[1]");
    public static final By txNameOfSecondPurchase = By.xpath("(.//*[@id=\"itemsList\"]//h6)[2]");
    public static final By txDescriptionOfSecondElement = By.xpath("(.//*[@id=\"itemsList\"]//small)[2]");
    public static final By txPriceOfSecondPurchase = By.xpath("(.//*[@id=\"itemsList\"]//span)[2]");
    public static final By txTotalUSD = By.xpath(".//*[@id=\"totalAmount\"]/preceding-sibling::span");
    public static final By txCurrency = By.xpath(".//*[@id=\"totalAmount\"]/preceding-sibling::strong");
    public static final By txTotal = By.id("totalAmount");
    public static final By txSuccess = By.id("success");
}
