package com.chupin.ibanvalidator.controller;

import com.chupin.ibanvalidator.entity.IbanFile;
import com.chupin.ibanvalidator.entity.IbanNumber;
import com.chupin.ibanvalidator.service.IbanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1")
public class ViewController {

    @Autowired
    private IbanService ibanService;

    @GetMapping("/validator")
    public String validatorForm(Model model) {
        model.addAttribute("validator", new IbanNumber());
        model.addAttribute("validatorFile", new IbanFile());
        return "validator";
    }

    @PostMapping("/validator")
    public String validatorSubmitIban(@ModelAttribute IbanNumber ibanNumber) {
        if (ibanNumber.getIbanOriginalNumber() == "") {
            return "emptyfielderror";
        } else ibanService.validate(ibanNumber);
        return "result";
    }

    @PostMapping("/filevalidator")
    public String validatorSubmitFileWithIban(@ModelAttribute IbanFile ibanFile) {
        if (ibanFile.getPath() == "") {
            return "emptyfielderror";
        } else ibanService.validate(ibanFile);
        return "success";
    }

}