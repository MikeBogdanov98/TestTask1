import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import res.Constants;
import res.Elements;
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
        browser.clickOnByXpath(Elements.btnCheckout);
        browser.checkDisplayByXpath(Elements.txFirstNameError);
        browser.checkDisplayByXpath(Elements.txLastNameError);
        browser.checkDisplayByXpath(Elements.txCCName);
        browser.checkDisplayByXpath(Elements.txCCNumber);
        browser.checkDisplayByXpath(Elements.txCCExpiration);
        browser.checkDisplayByXpath(Elements.txCCCVV);
        browser.checkSelectedById(Elements.rbCreditCard);
    }

    @Test
    public void checkLoadingOfCart() {
        browser.checkDisplayById(Elements.elLoading);
        browser.waitVisibilityByid(Elements.txTotal);
        Assert.assertEquals(browser.getTextByXpath(Elements.txYourCart)
                , Constants.yourCart);
        Assert.assertEquals(browser.getTextByXpath(Elements.txAmountOfPurchases)
                , Constants.amountOfPurchases);
        Assert.assertEquals(browser.getTextByXpath(Elements.txNameOfFirstPurchase)
                , Constants.nameOfFirstPurchase);
        Assert.assertEquals(browser.getTextByXpath(Elements.txDescriptionOfFirstElement)
                , Constants.descriptionOfFirstPurchase);
        Assert.assertEquals(browser.getTextByXpath(Elements.txPriceOfFirstPurchase)
                , Constants.priceOfFirstPurchase);
        Assert.assertEquals(browser.getTextByXpath(Elements.txNameOfSecondPurchase)
                , Constants.nameOfSecondPurchase);
        Assert.assertEquals(browser.getTextByXpath(Elements.txDescriptionOfSecondElement)
                , Constants.descriptionOfSecondElement);
        Assert.assertEquals(browser.getTextByXpath(Elements.txPriceOfSecondPurchase)
                , Constants.priceOfSecondPurchase);
        Assert.assertEquals(browser.getTextByXpath(Elements.txTotalUSD)
                , Constants.totalUSD);
        Assert.assertEquals(browser.getTextByXpath(Elements.txCurrency)
                , Constants.currency);
        Assert.assertEquals(browser.getTextById(Elements.txTotal)
                , Constants.total);
    }


    //Unstable test because of bug "Promo code can break total (long fractional part)" reproduce test below
    //Idea to solve: round for 2 digits in fractional part
    @RepeatedTest(3)
    public void checkPromoCodeChangesTotal() {
        int percent = CommonWrapper.getRandomIntInRange(0, 99);
        browser.checkDisplayById(Elements.elLoading);
        browser.waitVisibilityByid(Elements.txTotal);
        String exp = CommonWrapper.getTotalWithDiscount(browser.getDoubleById(Elements.txTotal)
                , percent);
        browser.sendKeysById(Elements.inPromocode
                , CommonWrapper.getStringWithLength(percent));
        browser.clickOnByXpath(Elements.btnRedeem);
        browser.waitInvisibilityByid(Elements.inPromocode);
        String act = browser.getTextById(Elements.txTotal);
        Assert.assertEquals(exp, act);
    }

    //Reproduce of bug "Promo code can break total (long fractional part)" must be failed
    @Test
    public void proofBugOfChangingTotalByPromoCodeOn14Percent() {
        browser.checkDisplayById(Elements.elLoading);
        browser.waitVisibilityByid(Elements.txTotal);
        String exp = CommonWrapper.getTotalWithDiscount(browser.getDoubleById(Elements.txTotal)
                , 14);
        browser.sendKeysById(Elements.inPromocode
                , CommonWrapper.getStringWithLength(14));
        browser.clickOnByXpath(Elements.btnRedeem);
        browser.waitInvisibilityByid(Elements.inPromocode);
        String act = browser.getTextById(Elements.txTotal);
        Assert.assertEquals(exp, act);
    }

    //Reproduce of bug "Total can't be less than 0" must be failed.
    //This bug is based on my user experience, SRS can differ.
    @Test
    public void proofBugOfTotalLessThan0() {
        int percent = CommonWrapper.getRandomIntInRange(101, 200);
        browser.checkDisplayById(Elements.elLoading);
        browser.waitVisibilityByid(Elements.txTotal);
        browser.sendKeysById(Elements.inPromocode
                , CommonWrapper.getStringWithLength(percent));
        browser.clickOnByXpath(Elements.btnRedeem);
        browser.waitInvisibilityByid(Elements.inPromocode);
        String act = browser.getTextById(Elements.txTotal);
        Assert.assertEquals(0, act);
    }

    @Test
    public void checkSuccessOfFormSubmission() {
        browser.sendKeysById(Elements.inFirstName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeysById(Elements.inLastName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeysById(Elements.inCCName
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeysById(Elements.inCCNumber
                , CommonWrapper.getRandomStringOfLettersAndDigits(16));
        browser.sendKeysById(Elements.inCCExpiration
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.sendKeysById(Elements.inCCCVV
                , CommonWrapper.getRandomStringOfLettersAndDigits(1));
        browser.checkSelectedById(Elements.rbCreditCard);
        browser.clickOnByXpath(Elements.btnCheckout);
        browser.waitVisibilityByid(Elements.txSuccess);
        browser.checkDisplayById(Elements.txSuccess);
        Assert.assertEquals(Constants.success
                , browser.getTextById(Elements.txSuccess));

    }

    @AfterEach
    @After
    public void postConditionsOfUITests() {
        browser.closeBrowser();
    }

}
