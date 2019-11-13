package com.chupin.ibanvalidator.entity;

public class IbanNumber {

    private String ibanOriginalNumber;

    private boolean validity;

    public boolean getValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getIbanOriginalNumber() {
        return ibanOriginalNumber;
    }

    public void setIbanOriginalNumber(String ibanOriginalNumber) {
        this.ibanOriginalNumber = ibanOriginalNumber;
    }
}
