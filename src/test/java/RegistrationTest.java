import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;

import static envconfig.EnvConfig.BASE_URI;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.User.*;
import static ru.yandex.praktikum.UserSteps.deleteUser;
import static ru.yandex.praktikum.UserSteps.loginUser;

public class RegistrationTest {

    private final DriverFactory factory = new DriverFactory();

    public User user;

    public String name;
    public String email;
    public String correctPassword;
    public String incorrectPassword;

    @Before
    public void setUp() throws Exception {
        factory.initDriver();
        RestAssured.baseURI = BASE_URI;
        name = generateRandomName();
        email = generateRandomEmail();
        correctPassword = generateRandomCorrectPassword();
        incorrectPassword = generateRandomIncorrectPassword();
        user = new User();
        user.setEmail(generateRandomEmail());
        user.setPassword(generateRandomCorrectPassword());
        user.setName(generateRandomName());
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
        registrationPage.fillRegistrationFormCorrectData(name, email, correctPassword);
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
        String accessToken = loginUser(user).then().extract().body().path("accessToken");
        if (accessToken != null) {
            user.setAccessToken(accessToken);
            deleteUser(user);
        }
        factory.getDriver().quit();
    }
}
