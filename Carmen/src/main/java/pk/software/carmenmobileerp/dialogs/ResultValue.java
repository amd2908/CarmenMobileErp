package pk.software.carmenmobileerp.dialogs;

public class ResultValue<T> extends Result {

    private T value;

    public ResultValue(T value) {
        this.value = value;
    }
    public ResultValue(T value, double tm) {
        this.value = value;
        super.setTime(tm);
    }

    public ResultValue(Result srcRes, T value) {
        super(srcRes);
        this.value = value;
    }

    public ResultValue(int errCode, String errString, T value) {
        this.errCode = errCode;
        this.errString = errString;
        this.value = value;
    }

    public ResultValue(int errCode, String errString, String errStringEx, T value) {
        this.errCode = errCode;
        this.errString = errString;
        this.errStringEx = errStringEx;
        this.value = value;
    }

    @Override
    public ResultValue<T> setTime(double tm) {
        super.setTime(tm);
        return this;
    }

    public T get() {
        return value;
    }

    public void set(T anotherValue) {
        value = anotherValue;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return value.equals(obj);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}