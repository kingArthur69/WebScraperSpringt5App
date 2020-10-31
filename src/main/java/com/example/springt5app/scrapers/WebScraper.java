package com.example.springt5app.scrapers;

import com.example.springt5app.AppPropertiesConfig;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class WebScraper implements IWebScraper {

    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private WebDriver webDriver;

    private AppPropertiesConfig appPropertiesConfig;

    public WebScraper(AppPropertiesConfig appPropertiesConfig) {
        this.appPropertiesConfig = appPropertiesConfig;
    }

    public void init() {
        webDriver = createWebDriver();
    }

    @Override
    public void close() {
        webDriver.close();
        webDriver.quit();
    }

    @Override
    public void get(String url) {
        webDriver.get(url);
    }

    @Override
    public void submitText(String selector, String text) {
        WebElement element = webDriver.findElement(By.cssSelector(selector));

        if (element != null && element.isEnabled()) {
            element.sendKeys(text);
            element.submit();
        }
    }

    @Override
    public void wait(String selector, Long seconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
    }

    private WebDriver createWebDriver() {
        String driver = appPropertiesConfig.getWebDriver();
        if ("htmlUnit".equals(driver)) {
            return null;
        } else if ("chrome".equals(driver)) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER, appPropertiesConfig.getPathToChromeDriver());
            return new ChromeDriver();
        }
        return null;
    }


    @Override
    public String getTextFromPath() {
        return null;
    }

    @Override
    public List<String> getAllTextFromAttributesPath(String selector, String attribute) {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(selector));
        if (elements != null) {
            return elements.stream().map(webElement -> webElement.getAttribute(attribute)).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    public List<String> getAllTextFromPath(String selector) {
        return null;
    }
}
