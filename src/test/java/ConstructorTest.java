import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.pageobject.MainPage;

import static envconfig.EnvConfig.BASE_URI;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

    private final DriverFactory factory = new DriverFactory();

    public User user;

    @Before
    public void setUp() throws Exception {
        factory.initDriver();
    }

    @Test
    public void goToBunSection() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSouceTab();
        mainPage.clickBunTab();
        assertTrue(mainPage.visibilityBunSection());
        assertEquals("Булки", mainPage.getActiveSectionTitle());
    }

    @Test
    public void goToSouceSection() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSouceTab();
        assertTrue(mainPage.visibilitySouceSection());
        assertEquals("Соусы", mainPage.getActiveSectionTitle());
    }

    @Test
    public void goToToppingSection() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickToppingTab();
        assertTrue(mainPage.visibilityToppingSection());
        assertEquals("Начинки", mainPage.getActiveSectionTitle());
    }

    @After
    public void tearDown() {
        factory.getDriver().quit();
    }
}
