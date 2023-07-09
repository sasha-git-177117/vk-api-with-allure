package tests.base;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import consts.ConfigEnum;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import steps.StepsApi;

abstract public class BaseTest {
    @BeforeClass
    public void setupClass() {
        Logger log = AqualityServices.getLogger();
        ISettingsFile configData = new JsonSettingsFile(ConfigEnum.CONFIG_DATA_PATH.label);
        String url = configData.getValue(ConfigEnum.BASE_URL.label).toString();

        log.info("Шаг 1 - Настройка браузера и переход на " + url);
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(url);
        AqualityServices.getBrowser().waitForPageToLoad();
        StepsApi.attachScreenshotWithDiscInAllure(StepsApi.getScreenshot(),"Открывается страница входа");

    }
    @AfterClass
    public void tearDown() {
        AqualityServices.getBrowser().quit();
    }
}
