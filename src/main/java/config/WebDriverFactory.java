package config;

import model.Browser;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    private static final String BROWSER_PROPERTY = "browser";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String WEBDRIVER_CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";

    public static WebDriver createWebDriver() {
        Browser browser = getActiveBrowser();
        WebDriverConfig webDriverConfig = ConfigFactory.create(WebDriverConfig.class);
        if (browser.equals(Browser.CHROME)) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, webDriverConfig.chromeDriverPath());
            return new ChromeDriver();
        } else if (browser.equals(Browser.YANDEX)) {
            System.setProperty(WEBDRIVER_CHROME_DRIVER_PROPERTY, webDriverConfig.yandexDriverPath());
            return new ChromeDriver();
        } else {
            throw new RuntimeException("Нераспознанный браузер: " + browser);
        }
    }

    private static Browser getActiveBrowser() {
        String browserName = System.getProperty(BROWSER_PROPERTY, DEFAULT_BROWSER);
        return Browser.valueOf(browserName.toUpperCase());
    }
}
