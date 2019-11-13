package com.chupin.ibanvalidator.validator;

import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.math.BigInteger;

@Component
public class IbanValidator {

    private final BigInteger divider = new BigInteger("97");
    private final BigInteger expectedResult = new BigInteger("1");

    public boolean validate(String ibanNumber) throws ValidationException {
        String ibanNumberWithoutSpaces = ibanNumber.trim();
        if (ibanNumberWithoutSpaces.length() < 5 || ibanNumberWithoutSpaces.length() > 34) {
            return false;
        }
        ibanNumberWithoutSpaces = ibanNumberWithoutSpaces.substring(4) + ibanNumberWithoutSpaces.substring(0, 4);
        StringBuilder ibanStringBuilder = new StringBuilder();
        for (int i = 0; i < ibanNumberWithoutSpaces.length(); i++) {
            int transformedCharToInt = Character.getNumericValue(ibanNumberWithoutSpaces.charAt(i));
            ibanStringBuilder.append(transformedCharToInt);
        }
        ibanNumberWithoutSpaces = ibanStringBuilder.toString();
        BigInteger ibanNumberBigInteger = new BigInteger(ibanNumberWithoutSpaces);

        return ibanNumberBigInteger.remainder(divider).equals(expectedResult);
    }


}
