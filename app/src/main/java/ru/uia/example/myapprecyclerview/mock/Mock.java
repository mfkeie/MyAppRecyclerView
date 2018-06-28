package ru.uia.example.myapprecyclerview.mock;

public class Mock {

    private String mName;
    private int mValue;

    public Mock(String mName, int mValue) {
        this.mName = mName;
        this.mValue = mValue;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getValue() {
        return String.valueOf(mValue);
    }

    public void setValue(int mValue) {
        this.mValue = mValue;
    }
}
