import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;

import static envconfig.EnvConfig.BASE_URI;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.User.*;

public class RegistrationTest {

    private final DriverFactory factory = new DriverFactory();
    public String name;
    public String email;
    public String correctPassword;
    public String incorrectPassword;


    @Before
    public void setUp() throws Exception {
        factory.initDriver();
        name = generateRandomName();
        email = generateRandomEmail();
        correctPassword = generateRandomCorrectPassword();
        incorrectPassword = generateRandomIncorrectPassword();
    }

    @Test
    public void checkRegistrationSuccess() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationFormCorrectData(name, email,correctPassword);
        registrationPage.clickRegistrationButton();
        assertTrue(loginPage.visibilityEnterTitle());
    }

    @Test
    public void checkRegistrationError() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.fillRegistrationFormIncorrectData(name, email, incorrectPassword);
        registrationPage.clickRegistrationButton();
        assertTrue(registrationPage.visibilityIncorrectPasswordError());
    }

    @After
    public void tearDown() {
        factory.getDriver().quit();
    }
}
