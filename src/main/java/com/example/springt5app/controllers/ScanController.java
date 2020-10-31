package com.example.springt5app.controllers;

import com.example.springt5app.scanners.IScanner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScanController {

    private IScanner scanner;

    public ScanController(IScanner scanner) {
        this.scanner = scanner;
    }

    @GetMapping({"/scanPage"})
    public String scan(@RequestParam("searchText") String searchText) {
        scanner.scan(searchText.toLowerCase());
        return "scan";
    }
}
