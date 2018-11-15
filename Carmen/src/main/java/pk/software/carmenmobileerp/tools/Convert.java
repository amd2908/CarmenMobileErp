package pk.software.carmenmobileerp.tools;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Convert {

    private static DateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    public static int DECIMAL_PLACES = 2;

    public static double toDouble(String str, double defValue) {
        try {
            if (str.equals(""))
                return defValue;

            return Double.parseDouble(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defValue;
    }

    public static double roundToDecimalsPrecision(double d, int precision) {
        return roundToDecimals(d, precision);
    }

    public static double roundToDecimals(double d) {
        return roundToDecimals(d, DECIMAL_PLACES);
    }

    public static double roundToDecimals(double d, int c) {
        int temp = (int) Math.round(d * Math.pow(10, c));
        return (((double) temp) / Math.pow(10, c));
    }

    public static String doubleToString(double d) {
        return String.format("%01.2f", d);
    }

    public static String doubleToStringDot(double d){
        return String.format("%01.2f", d).replace(',', '.');
    }

    public static String doubleAsCurrencyString(double d, String currency) {
        return String.format("%01.2f %s", d, currency);
    }

    public static double getFromCurrencyToDouble(String amount, String currency)
    {
        return Double.parseDouble(amount
                .replace(currency, "")
                .trim().replace(",", "."));
    }



}
