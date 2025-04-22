import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.User;
import ru.yandex.praktikum.pageobject.LoginPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonalAccountPage;

import static envconfig.EnvConfig.BASE_URI;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.User.*;
import static ru.yandex.praktikum.UserSteps.*;

public class PersonalAccountTest {

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
    public void goToPersonalAccount() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickPersonalAccountButton();
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        assertTrue(personalAccountPage.visibilityProfileTitle());
    }

    @Test
    public void goToConstructorFromPersonalAccount() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickPersonalAccountButton();
        mainPage.clickConstructorButton();
        assertTrue(mainPage.visibilityCreateOrderButton());
    }

    @Test
    public void goToMainPageFromPersonalAccount() throws InterruptedException {
        WebDriver driver = factory.getDriver();
        driver.get(BASE_URI);
        MainPage mainPage = new MainPage(driver);
        mainPage.clickEnterAccountButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillLoginFormCorrectData(user.getEmail(), user.getPassword());
        loginPage.clickEnterButton();
        mainPage.clickPersonalAccountButton();
        mainPage.clickLogoImage();
        assertTrue(mainPage.visibilityCreateOrderButton());
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
