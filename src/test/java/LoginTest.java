import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.pageobject.*;

import static envconfig.EnvConfig.BASE_URI;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.User.*;
import static ru.yandex.praktikum.UserSteps.*;

public class LoginTest {

    private final DriverFactory factory = new DriverFactory();

    public User user;

    @Before
    public void setUp() throws Exception {
        factory.initDriver();
        RestAssured.baseURI = BASE_URI;
        user = new User();
        user.setEmail(generateRandomEmail());
        user.setPassword(generateRandomCorrectPassword());
        user.setName(generateRandomName());
        createUser(user);
    }

    @Test
    public void LoginThroughMainPage() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        assertTrue(mainPage.visibilityCreateOrderButton());
    }

    @Test
    public void LoginThroughPersonalAccount() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        assertTrue(mainPage.visibilityCreateOrderButton());
    }

    @Test
    public void LoginThroughRegistrationForm() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRegistrationLink();
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.clickEnterLink();
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        assertTrue(mainPage.visibilityCreateOrderButton());
    }

    @Test
    public void LoginThroughPasswordRecoveryForm() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickPasswordRecoveryLink();
        PasswordRecoveryPage passwordRecoveryPage = new PasswordRecoveryPage(driver);
        passwordRecoveryPage.clickEnterLink();
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        assertTrue(mainPage.visibilityCreateOrderButton());
    }

    @Test
    public void LogoutThroughPersonalAccount() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.clickExitButton();
        assertTrue(loginPage.visibilityEnterTitle());
    }

    @After
    public void tearDown() {
        String accessToken = loginUser(user).then().extract().body().path("accessToken");
        if (accessToken != null) {
            user.setAccessToken(accessToken);
            deleteUser(user);
        }
        factory.getDriver().quit();
    }
}
