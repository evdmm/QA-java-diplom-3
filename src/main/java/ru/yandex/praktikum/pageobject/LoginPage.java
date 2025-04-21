package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Заголовок Вход
    private final By enterTitle = By.xpath(".//h2[text()='Вход']");
    //Поле Email
    private final By emailField = By.xpath(".//input[@name='name']");
    //Поле Пароль
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка Войти
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    //Ссылка Зарегистрироваться
    private final By registrationLink = By.xpath(".//a[text()='Зарегистрироваться']");
    //Ссылка Восстановить пароль
    private final By passwordRecoveryLink = By.xpath(".//a[text()='Восстановить пароль']");

    //Методы
    //Ввести email
    @Step("Ввести email")
    public void enterEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }
    //Ввести пароль
    @Step("Ввести пароль")
    public void enterPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Заполнить поля Формы логина корректными данными")
    public void fillLoginFormCorrectData(String email, String password) {
        enterEmail(email);
        enterPassword(password);
    }
    @Step("Нажать на кнопку Войти")
    public void clickEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterButton));
        driver.findElement(enterButton).click();
    }
    @Step("Нажать на ссылку Зарегистрироваться")
    public void clickRegistrationLink() throws InterruptedException {
        Thread.sleep(2000);
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.visibilityOfElementLocated(registrationLink));
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(registrationLink));
        driver.findElement(registrationLink).click();
    }
    @Step("Нажать на ссылку Восстановить пароль")
    public void clickPasswordRecoveryLink() throws InterruptedException {
        Thread.sleep(2000);
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.visibilityOfElementLocated(passwordRecoveryLink));
//        new WebDriverWait(driver, Duration.ofSeconds(15))
//                .until(ExpectedConditions.elementToBeClickable(passwordRecoveryLink));
        driver.findElement(passwordRecoveryLink).click();
    }
    @Step("Отображения заголовка Вход")
    public boolean visibilityEnterTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterTitle));
        return driver.findElement(enterTitle).isDisplayed();
    }
}
