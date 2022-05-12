package helpers;

import Data.TestData;
import com.codeborne.selenide.Configuration;
import config.DataConfig;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;

import static com.codeborne.selenide.Selenide.getWebDriverLogs;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static helpers.EnvironmentsHelper.*;
import static java.lang.String.join;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.openqa.selenium.logging.LogType.BROWSER;

public class DriverHelper {
    
    private static DriverConfig getDriverConfig() {
        return ConfigFactory.newInstance().create(DriverConfig.class, System.getProperties());
    }

    private static DataConfig getDataConfig(){
        return ConfigFactory.newInstance().create(DataConfig.class, System.getProperties());
    }

    public static String getWebRemoteDriver() {
        return String.format(getDriverConfig().webRemoteDriverUrl(),
                getDriverConfig().webRemoteDriverUser(),
                getDriverConfig().webRemoteDriverPassword());
    }

    public static boolean isRemoteWebDriver() {
        return !getDriverConfig().webRemoteDriverUrl().equals("");
    }

    public static String getVideoUrl() {
        return getDriverConfig().videoStorage();
    }

    public static boolean isVideoOn() {
        return !getVideoUrl().equals("");
    }

    public static String getWebMobile(){
        return getDriverConfig().webBrowserMobileView();
    }

    public static boolean isWebMobile(){
        return !getWebMobile().equals("");
    }

    public static String getSessionId(){
        return ((RemoteWebDriver) getWebDriver()).getSessionId().toString().replace("selenoid","");
    }

    public static String getConsoleLogs() {
        return join("\n", getWebDriverLogs(BROWSER));
    }

    public static void configureDriver() {
        addListener("AllureSelenide", new AllureSelenide());


        if(isWeb) {
            Configuration.baseUrl = TestData.getWebUrl();
        }
        Configuration.browser = getDriverConfig().webBrowser();
        Configuration.browserVersion = getDriverConfig().webBrowserVersion();
        Configuration.browserSize = getDriverConfig().webBrowserSize();

        DesiredCapabilities capabilities = new DesiredCapabilities();

        Configuration.browserCapabilities = capabilities;
    }

    private String getAbsolutePath(String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists(), filePath + " not found");

        return file.getAbsolutePath();
    }
}
