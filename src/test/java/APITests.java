import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;
import res.Constants;
import wrappers.APIWrapper;
import wrappers.CommonWrapper;

public class APITests {

    @BeforeClass
    public static void checkAvailability() {
        long statusCode = APIWrapper.getStatusCodePost(Constants.apiUrl);
        Assert.assertEquals(statusCode, 403);
    }


    @RepeatedTest(5)
    public void checkDiscountPercent() {
        int percent = CommonWrapper.getRandomIntInRange(0, 199);
        //System.out.println(percent);
        String testpromo = CommonWrapper.getStringWithLength(percent);
        int dicount = APIWrapper.getJsonBodyEmptyPost(Constants.apiUrl + Constants.resPostCoupon + testpromo)
                .getInt("discount");
        Assert.assertEquals(percent, dicount);
    }

    @Test
    public void checkErrorCreditCardNumberLengthLessThan15() {
        String message = APIWrapper.getJsonBodyJsonPost(Constants.apiUrl + Constants.resPostCheckout
                        , CommonWrapper.getRandomFormWithCertainLengthOfCcNumber(
                                CommonWrapper.getRandomIntInRange(1, 14)))
                .getString("message");
        Assert.assertEquals(Constants.mesInvalidCardNumber, message);
    }

    @Test
    public void checkErrorCreditCardNumberLengthMoreThan17() {
        String message = APIWrapper.getJsonBodyJsonPost(Constants.apiUrl + Constants.resPostCheckout
                        , CommonWrapper.getRandomFormWithCertainLengthOfCcNumber(
                                CommonWrapper.getRandomIntInRange(18, 31)))
                .getString("message");
        Assert.assertEquals(Constants.mesInvalidCardNumber, message);
    }

    @Test
    public void checkErrorCreditCardNumberIs15() {
        String message = APIWrapper.getJsonBodyJsonPost(Constants.apiUrl + Constants.resPostCheckout
                        , CommonWrapper.getRandomFormWithCertainLengthOfCcNumber(15))
                .getString("message");
        Assert.assertEquals(Constants.mesInvalidCardNumber, message);
    }

    @Test
    public void checkErrorCreditCardNumberIs17() {
        String message = APIWrapper.getJsonBodyJsonPost(Constants.apiUrl + Constants.resPostCheckout
                        , CommonWrapper.getRandomFormWithCertainLengthOfCcNumber(17))
                .getString("message");
        Assert.assertEquals(Constants.mesInvalidCardNumber, message);
    }
}