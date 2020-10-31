package com.example.springt5app.scrapers;

import java.util.List;

public interface IWebScraper {

    void init();

    void close();

    void get(String url);

    void submitText(String selector, String text);

    void wait(String selector, Long ms);

    String getTextFromPath();

    List<String> getAllTextFromAttributesPath(String selector, String attribute);

    List<String> getAllTextFromPath(String selector);

}
