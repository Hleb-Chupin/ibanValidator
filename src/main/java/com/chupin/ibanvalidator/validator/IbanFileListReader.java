package com.chupin.ibanvalidator.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.ValidationException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class IbanFileListReader {

    @Autowired
    private IbanValidator ibanValidator;

    private String inputFileName;

    public String getInputFileName() {
        return inputFileName;
    }

    public void setInputFileName(String inputFileName) {
        this.inputFileName = inputFileName;
    }

    public List<String> readFile(String path) {
        this.inputFileName = path;
        try (Stream<String> stream = Files.lines(Paths.get(inputFileName))) {
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> validateListIban(List<String> ibanList) {
        for (int i = 0; i < ibanList.size(); i++) {
            try {
                ibanList.set(i, ibanList.get(i) + ";" + ibanValidator.validate(ibanList.get(i)));
            } catch (ValidationException e) {
                e.printStackTrace();
            }
        }
        System.out.println(ibanList);
        return ibanList;
    }

    public void writeFile(List<String> ibanList) {
        try (FileWriter writer = new FileWriter(inputFileName + ".out")) {
            for (String str : ibanList) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}