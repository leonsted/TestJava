package pages;

import Data.TestData;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class MainPage {
    @BeforeAll
    static void configureBaseUrl() {
        baseUrl = TestData.getWebUrl();
    }

    WebElement title = $(By.xpath("//h2[contains(text(),'EverSOL Stake Pool')]")); //Title element
    WebElement titleSectionSOLStaking = $(By.xpath("//h3[contains(text(),'SOL Staking Evolution. Join for Good')]")); // Title Section "SOL Staking Evolution. Join for Good"
    WebElement sectionSOLSackingText = $(By.xpath("//*[@id=\"root\"]//div[2]/div/p/span")); //Text in section SOL Staking
    WebElement delegatePoolBtn = $(By.xpath("//*[@id=\"root\"]//div[3]/div/div[1]/div/div[2]/a")); //delegate to the Pool button element
    WebElement titleTotalStake = $(By.xpath("//h3[contains(text(),\"Total Stake\")]")); //Title total stake element


    public MainPage openPage() {
        step("Open login page and check title is displayed", () -> {
            open(baseUrl);
            title.isDisplayed();
        });
        return this;
    }

    public MainPage checkEverSOLLink() {
        step("Check EverSOL link is displayed", () -> {
            titleSectionSOLStaking.isDisplayed();

        });

        step("Check Title section SOL Staking Evolution contains correct text",()->{
            Assertions.assertEquals(titleSectionSOLStaking.getText(),"SOL Staking Evolution. Join for Good");
        });

        step("Check Text section SOL Staking Evolution contains correct text",()->{
            Assertions.assertEquals(sectionSOLSackingText.getText(),"Eversol's main concept is to dedicate a percentage of the pool rewards to fund the best solutions being built on Solana. Unlike other stake pools, Eversol aims to directly facilitate and boost the DeFi ecosystem by helping new apps and products come to life.");
        });

        return this;
    }

    public MainPage checkButtonDelegatePool(WebDriver driver){
        step("Check is button displayed",()->{
            delegatePoolBtn.isDisplayed();
        });

        step("Check is button enabled",()->{
            delegatePoolBtn.isEnabled();
        });

        step("Check is button have correct text",()->{
            Assertions.assertEquals(delegatePoolBtn.getText(),"Delegate to the Pool");
        });

        step("Click on button",()->{
            delegatePoolBtn.click();
        });

        step("Check opened new page in new tab",()->{
            WebDriver tab = driver;
            tab.getWindowHandle();
            Assertions.assertEquals(tab.getCurrentUrl(),"https://eversol.one/");
        });

        return this;
    }

    public MainPage checkSectiontotalStake(){
        step("Check dispalyed title",()->{
            titleTotalStake.isDisplayed();
        });

        step("Check text in title",()->{
            Assertions.assertEquals(titleTotalStake.getText(),"Total Stake");
        });

        step("Check text in title",()->{
            Assertions.assertEquals(titleTotalStake.getText(),"Total Stake");
        });

        return this;
    }
}
