import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import res.Constants;
import res.MainPage;
import wrappers.CommonWrapper;
import wrappers.UIWrapper;

public class UITests {
    private final UIWrapper browser = new UIWrapper("chrome", 15);

    @BeforeEach
    @Before
    public void preconditionsOfUITests() {
        browser.goToLink(Constants.siteLink);
    }

    @Test
    public void checkErrorsOfAllMandatoryFields() {
        browser.clickOn(MainPage.btnCheckout);
        browser.checkDisplay(MainPage.txFirstNameError);
        browser.checkDisplay(MainPage.txLastNameError);
        browser.checkDisplay(MainPage.txCCName);
        browser.checkDisplay(MainPage.txCCNumber);
        browser.checkDisplay(MainPage.txCCExpiration);
        browser.checkDisplay(MainPage.txCCCVV);
        browser.checkSelected(MainPage.rbCreditCard);
    }

    @Test
    public void checkLoadingOfCart() {
        browser.checkDisplay(MainPage.elLoading);
        browser.waitVisibility(MainPage.txTotal);
        Assert.assertEquals(browser.getText(MainPage.txYourCart)
                , Constants.yourCart);
        Assert.assertEquals(browser.getText(MainPage.txAmountOfPurchases)
                , Constants.amountOfPurchases);
        Assert.assertEquals(browser.getText(MainPage.txNameOfFirstPurchase)
                , Constants.nameOfFirstPurchase);
        Assert.assertEquals(browser.getText(MainPage.txDescriptionOfFirstElement)
                , Constants.descriptionOfFirstPurchase);
        Assert.assertEquals(browser.getText(MainPage.txPriceOfFirstPurchase)
                , Constants.priceOfFirstPurchase);
        Assert.assertEquals(browser.getText(MainPage.txNameOfSecondPurchase)
                , Constants.nameOfSecondPurchase);
        Assert.assertEquals(browser.getText(MainPage.txDescriptionOfSecondElement)
                , Constants.descriptionOfSecondElement);
        Assert.assertEquals(browser.getText(MainPage.txPriceOfSecondPurchase)
                , Constants.priceOfSecondPurchase);
        Assert.assertEquals(browser.getText(MainPage.txTotalUSD)
                , Constants.totalUSD);
        Assert.assertEquals(browser.getText(MainPage.txCurrency)
                , Constants.currency);
        Assert.assertEquals(browser.getText(MainPage.txTotal)
                , Constants.total);
    }


    //Unstable test because of bug "Promo code can break total (long fractional part)" reproduce test below
    //Idea to solve: round for 2 digits in fractional part
    @RepeatedTest(3)
    public void checkPromoCodeChangesTotal() {
        int percent = CommonWrapper.getRandomIntInRange(0, 99);
        browser.checkDisplay(MainPage.elLoading);
        browser.waitVisibility(MainPage.txTotal);
        String exp = CommonWrapper.getTotalWithDiscount(browser.getDouble(MainPage.txTotal)
                , percent);
        browser.sendKeys(MainPage.inPromocode
                , CommonWrapper.getStringWithLength(percent));
        browser.clickOn(MainPage.btnRedeem);
        browser.waitInvisibility(MainPage.inPromocode);
        String act = browser.getText(MainPage.txTotal);
        Assert.assertEquals(exp, act);
    }

    //Reproduce of bug "Promo code can break total (long fractional part)" must be failed
    @Test
    public void proofBugOfChangingTotalByPromoCodeOn14Percent() {
        browser.checkDisplay(MainPage.elLoading);
        browser.waitVisibility(MainPage.txTotal);
        String exp = CommonWrapper.getTotalWithDiscount(browser.getDouble(MainPage.txTotal)
                , 14);
        browser.sendKeys(MainPage.inPromocode
                , CommonWrapper.getStringWithLength(14));
        browser.clickOn(MainPage.btnRedeem);
        browser.waitInvisibility(MainPage.inPromocode);
        String act = browser.getText(MainPage.txTotal);
        Assert.assertEquals(exp, act);
    }

    //Reproduce of bug "Total can't be less than 0" must be failed.
    //This bug is based on my user experience, SRS can differ.
    @Test
    public void proofBugOfTotalLessThan0() {
        int percent = CommonWrapper.getRandomIntInRange(101, 200);
        browser.checkDisplay(MainPage.elLoading);
        browser.waitVisibility(MainPage.txTotal);
        browser.sendKeys(MainPage.inPromocode
                , CommonWrapper.getStringWithLength(percent));
        browser.clickOn(MainPage.btnRedeem);
        browser.waitInvisibility(MainPage.inPromocode);
        String act = browser.getText(MainPage.txTotal);
        Assert.assertEquals(0, act);
    }

    @Test
    public void checkSuccessOfFormSubmission() {
        browser.sendKeys(MainPage.inFirstName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeys(MainPage.inLastName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeys(MainPage.inCCName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeys(MainPage.inCCNumber
                , CommonWrapper.getRandomStringOfLettersAndDigits(16));
        browser.sendKeys(MainPage.inCCExpiration
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeys(MainPage.inCCCVV
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.checkSelected(MainPage.rbCreditCard);
        browser.clickOn(MainPage.btnCheckout);
        browser.waitVisibility(MainPage.txSuccess);
        browser.checkDisplay(MainPage.txSuccess);
        Assert.assertEquals(Constants.success
                , browser.getText(MainPage.txSuccess));

    }

    @AfterEach
    @After
    public void postConditionsOfUITests() {
        browser.closeBrowser();
    }

}
