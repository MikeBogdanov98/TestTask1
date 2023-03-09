package res;

public class Elements {
    public static final String btnCheckout = "*//form/button";
    public static final String btnRedeem = ".//*[@id=\"promoCode\"]/following::button[1]";

    public static final String inFirstName = "firstName";
    public static final String inLastName = "lastName";
    public static final String inCCName = "cc-name";
    public static final String inCCNumber = "cc-number";
    public static final String inCCExpiration = "cc-expiration";
    public static final String inCCCVV = "cc-cvv";
    public static final String inPromocode = "promoCode";

    public static final String elLoading = "loading";

    public static final String rbCreditCard = "credit";

    public static final String txFirstNameError = "//*[@id=\"firstName\"]/following-sibling::div";
    public static final String txLastNameError = "//*[@id=\"lastName\"]/following-sibling::div";
    public static final String txCCName = "//*[@id=\"cc-name\"]/following-sibling::div";
    public static final String txCCNumber = "//*[@id=\"cc-number\"]/following-sibling::div";
    public static final String txCCExpiration = "//*[@id=\"cc-expiration\"]/following-sibling::div";
    public static final String txCCCVV = "//*[@id=\"cc-cvv\"]/following-sibling::div";
    public static final String txYourCart = ".//*[@id=\"itemsList\"]/preceding::h4/span[1]";
    public static final String txAmountOfPurchases = ".//*[@id=\"itemsList\"]/preceding::h4/span[2]";
    public static final String txNameOfFirstPurchase = "(.//*[@id=\"itemsList\"]//h6)[1]";
    public static final String txDescriptionOfFirstElement = "(.//*[@id=\"itemsList\"]//small)[1]";
    public static final String txPriceOfFirstPurchase = "(.//*[@id=\"itemsList\"]//span)[1]";
    public static final String txNameOfSecondPurchase = "(.//*[@id=\"itemsList\"]//h6)[2]";
    public static final String txDescriptionOfSecondElement = "(.//*[@id=\"itemsList\"]//small)[2]";
    public static final String txPriceOfSecondPurchase = "(.//*[@id=\"itemsList\"]//span)[2]";
    public static final String txTotalUSD = ".//*[@id=\"totalAmount\"]/preceding-sibling::span";
    public static final String txCurrency = ".//*[@id=\"totalAmount\"]/preceding-sibling::strong";
    public static final String txTotal = "totalAmount";
    public static final String txSuccess = "success";
}
