package tests.ui;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import tests.TestBase;
import pages.MainPage;

public class MainTest extends TestBase {

    MainPage page = new MainPage();

    @Test
    @Owner("Stanislav")
    @Tag("ui")
    void MainPageTest(){
        page.openPage();
        page.checkEverSOLLink();
        page.checkSectiontotalStake();
    }
}
