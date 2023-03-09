package wrappers;

import org.json.simple.JSONObject;

import java.text.DecimalFormat;

public class CommonWrapper {
    public static int getRandomIntInRange(int from, int to) {
        return (int) (Math.random() * (to - from + 1) + from);
    }

    public static String getStringWithLength(int length) {
        return "x".repeat(length);
    }

    public static String getTotalWithDiscount(double total, int percent) {
        DecimalFormat format = new DecimalFormat("0.##");
        return format.format(total - total * percent / 100)
                .replace(',', '.');
    }

    public static String getRandomStringOfLettersAndDigits(int length) {
        String result = "";
        for (int i = 0; i < length; i++) {
            int charNumber = (int) (Math.random() * 62);
            if (charNumber < 10) {
                result += (char) (charNumber + 48);
            } else if (charNumber < 36) {
                result += (char) (charNumber + 55);
            } else {
                result += (char) (charNumber + 61);
            }
        }
        return result;
    }

    public static JSONObject getRandomFormWithCertainLengthOfCcNumber(int length) {
        JSONObject form = new JSONObject();
        form.put("firstName", getRandomStringOfLettersAndDigits(1));
        form.put("lastName", getRandomStringOfLettersAndDigits(1));
        form.put("paymentMethod", getRandomStringOfLettersAndDigits(1));
        form.put("cc-name", getRandomStringOfLettersAndDigits(1));
        form.put("cc-number", getRandomStringOfLettersAndDigits(length));
        form.put("cc-expiration", getRandomStringOfLettersAndDigits(1));
        form.put("cc-cvv", getRandomStringOfLettersAndDigits(1));
        return form;
    }
}
