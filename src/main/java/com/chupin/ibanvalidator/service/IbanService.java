package com.chupin.ibanvalidator.service;

import com.chupin.ibanvalidator.entity.IbanFile;
import com.chupin.ibanvalidator.entity.IbanNumber;
import com.chupin.ibanvalidator.validator.IbanFileListReader;
import com.chupin.ibanvalidator.validator.IbanValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;

@Component
public class IbanService {

    @Autowired
    private IbanValidator ibanValidator;

    @Autowired
    private IbanFileListReader ibanFileListReader;

    public void validate(IbanNumber ibanNumber) {
        validateIbanNumber(ibanNumber);
    }

    public void validate(IbanFile ibanFile) {
        validateFileIban(ibanFile.getPath());
    }

    private void validateIbanNumber(IbanNumber ibanNumber) {
        try {
            ibanNumber.setValidity(ibanValidator.validate(ibanNumber.getIbanOriginalNumber()));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    private void validateFileIban(String path) {
        ibanFileListReader.writeFile(ibanFileListReader.validateListIban(ibanFileListReader.readFile(path)));
    }
}
