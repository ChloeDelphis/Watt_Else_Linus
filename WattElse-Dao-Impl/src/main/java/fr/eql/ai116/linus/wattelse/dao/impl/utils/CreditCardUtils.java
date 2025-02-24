package fr.eql.ai116.linus.wattelse.dao.impl.utils;

public class CreditCardUtils {

    public static String hideCreditCard(String creditCard) {
        if (creditCard == null) return "";
        else return creditCard.substring(creditCard.length() - 4);
    }

    public static String hideCss(Integer css) {
        if (css == null) return "";
        else return css.toString().substring(0,1) + "xx";
    }
}
