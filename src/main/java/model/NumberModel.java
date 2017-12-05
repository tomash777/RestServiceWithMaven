package model;

public class NumberModel {
    private String convertedNumber;

    public NumberModel(String convertedNumber) {
        this.convertedNumber = convertedNumber;
    }

    public NumberModel() {
    }

    public String getConvertedNumber() {
        return convertedNumber;
    }

    public void setConvertedNumber(String convertedNumber) {
        this.convertedNumber = convertedNumber;
    }
}
