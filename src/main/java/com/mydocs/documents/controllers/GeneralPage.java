package com.mydocs.documents.controllers;

import com.mydocs.documents.models.Document;
import com.mydocs.documents.services.DocumentsDetailsService;
import com.mydocs.documents.util.DocValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reestr")
public class GeneralPage {

    private final DocumentsDetailsService documentsDetailsService;
    private final DocValidator docValidator;

@Autowired
    public GeneralPage(DocumentsDetailsService documentsDetailsService, DocValidator docValidator) {
    this.documentsDetailsService = documentsDetailsService;
    this.docValidator = docValidator;
}

    @GetMapping()
    public String reestr(Model model, @RequestParam(value = "sort_by_number", required = false) boolean sortByNumber) {
        model.addAttribute("document", documentsDetailsService.findAll(sortByNumber));
        return "/generalPage";
    }

    @GetMapping("/addDoc")
    public String AdditionPage(@ModelAttribute("document")Document document){
        return "docs/addDoc";
    }

    @PostMapping("/addDoc")
    public String performAddition(@ModelAttribute("document")@Valid Document document, BindingResult bindingResult){
        docValidator.validate(document,bindingResult);
        if (bindingResult.hasErrors())
            return "docs/addDoc";
        documentsDetailsService.registerDocument(document);
        return "redirect:/reestr";
    }
    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("name") String name) {
        model.addAttribute("document", documentsDetailsService.searchByName(name));
        return "/generalPage";
    }
}
