package pk.software.carmenmobileerp.dialogs;

import android.text.TextUtils;
public class Result {

    protected int errCode = 0;
    protected String errString = "";
    protected String errStringEx = null;
    protected Object resultValue = null;
    protected double time = 0.0D;
    protected Throwable e = null;

    public static Result resultOK() {
        return new Result();
    }

    public static Result resultOK(double tm) {
        return new Result().setTime(tm);
    }

    public static Result resultValue(Object value) {
        return new Result(value);
    }

    public static Result resultError(int errCode, String errString) {
        return new Result(errCode, errString);
    }

    public static Result resultError(int errCode, String errString, String errStringEx) {
        return new Result(errCode, errString, errStringEx);
    }


    public Result() {
        this(0, "");
    }

    public Result(Result srcRes) {
        copyFrom(srcRes);
    }

    public Result(Object value) {
        this(0, "");
        this.resultValue = value;
    }

    public Result(int errCode, String errString) {
        this.errCode = errCode;
        this.errString = errString;
    }

    public Result(int errCode, String errString, String errStringEx) {
        this.errCode = errCode;
        this.errString = errString;
        this.errStringEx = errStringEx;
    }

    public Result(int errCode, String errString, String errStringEx, Throwable e) {
        this.errCode = errCode;
        this.errString = errString;
        this.errStringEx = errStringEx;
        this.e = e;
    }

    public Result copyFrom(Result srcRes) {
        if (srcRes != null) {
            errCode     = srcRes.errCode;
            errString   = srcRes.errString;
            errStringEx = srcRes.errStringEx;
            resultValue = srcRes.resultValue;
        }
        return this;
    }

    public Object getValue() {
        return resultValue;
    }

    public Object getValue(Object defValue) {
        return (this.resultValue != null) ? this.resultValue : defValue;
    }

    public Result setValue(Object value) {
        this.resultValue = value;
        return this;
    }

    public Result setTime(double tm) {
        this.time = tm;
        return this;
    }

    public double getTime() {
        return this.time;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrString() {
        return errString;
    }
    public String getErrString(String defValue) {
        return TextUtils.isEmpty(this.errString) ? defValue : this.errString;
    }

    public Result setErrCode(int errCode) {
        this.errCode = errCode;
        return this;
    }
    public Result setErrString(String errString) {
        this.errString = errString;
        return this;
    }

    public Result setError(int errCode, String errString) {
        this.errCode = errCode;
        this.errString = errString;
        return this;
    }

    public String getErrorStringEx() {
        return this.errStringEx;
    }
    public String getErrorStringEx(String defValue) {
        return TextUtils.isEmpty(this.errStringEx) ? defValue : this.errStringEx;
    }
    public Result setErrorStringEx(String errStringEx) {
        this.errStringEx = errStringEx;
        return this;
    }

    public Result setThrowable(Throwable e) {
        this.e = e;
        return this;
    }

    public Throwable getThrowable() {
        return e;
    }

    public boolean isSuccess() {
        return (errCode == 0);
    }

    @Override
    public String toString() {
        return errString;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Result) {
            Result rObj = (Result)obj;
            boolean isEqual = (
                    this.errCode == rObj.errCode &&
                            this.errString.equals(rObj.errString));
            if (isEqual && this.errCode == 0)
                isEqual = this.resultValue.equals(rObj.resultValue);
            return isEqual;
        }
        return false;
    }

//    @Override
//    public int hashCode() {
//        return value.hashCode();
//    }

}