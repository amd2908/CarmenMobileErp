package pk.software.carmenmobileerp.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.InputFilter;
import android.text.Spanned;

public class DecimalDigitsInputFilter implements InputFilter {

    boolean allowSign;
    Pattern mPattern;

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero) {
        this(digitsBeforeZero, digitsAfterZero, false);
    }

    public DecimalDigitsInputFilter(int digitsBeforeZero, int digitsAfterZero, boolean allowSign) {
        this.allowSign = allowSign;
        String patternStr = "^" +
                (allowSign ? "-?" : "") +
                "[0-9]{1," + (digitsBeforeZero) + "}(\\.[0-9]{0," + (digitsAfterZero) + "})?$";
        mPattern = Pattern.compile(patternStr);
    }

    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {

        String dStr = dest.toString();
        dStr = dStr.substring(0, dstart) + source + dStr.substring(dend);

        Matcher matcher = mPattern.matcher(dStr);
        boolean isMatch = (dStr.equals("") || (allowSign && dStr.equals("-")) || matcher.matches());
        if(!isMatch)
            return "";
        return null;
    }

    public static DecimalDigitsInputFilter Create(int digitsBeforeZero, int digitsAfterZero) {
        return new DecimalDigitsInputFilter(digitsBeforeZero, digitsAfterZero);
    }

    public static DecimalDigitsInputFilter Create(int digitsBeforeZero, int digitsAfterZero, boolean allowSign) {
        return new DecimalDigitsInputFilter(digitsBeforeZero, digitsAfterZero, allowSign);
    }
}
