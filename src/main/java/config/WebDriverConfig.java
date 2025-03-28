package config;

import org.aeonbits.owner.Config;

/**
 * Конфигурация запуска WebDriver.
 */

public interface WebDriverConfig extends Config {
    @Key("chromeDriverPath")
    String chromeDriverPath();

    @Key("yandexDriverPath")
    String yandexDriverPath();
}