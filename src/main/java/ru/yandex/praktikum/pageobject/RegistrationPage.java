package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Заголовок Регистрация
    private final By registrationTitle = By.xpath(".//h2[text()='Регистрация']");
    //Поле Имя
    private final By nameField = By.xpath("(.//input[@name='name'])[1]");
    //Поле Email
    private final By emailField = By.xpath("(.//input[@name='name'])[2]");
    //Поле Пароль
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //Кнопка Зарегистрироваться
    private final By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //Ошибка Некорректный пароль
    private final By incorrectPasswordError = By.xpath(".//p[text()='Некорректный пароль']");
    //Ссылка Войти
    private final By enterLink = By.xpath(".//a[text()='Войти']");

    //Методы
    @Step("Ввести имя")
    public void enterName(String name) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(nameField).sendKeys(name);
    }
    @Step("Ввести email")
    public void enterEmail(String email) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(emailField));
        driver.findElement(emailField).sendKeys(email);
    }
    @Step("Ввести пароль")
    public void enterPassword(String password) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(password);
    }
    @Step("Нажать на кнопку Зарегистрироваться")
    public void clickRegistrationButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(registrationButton));
        driver.findElement(registrationButton).click();
    }
    @Step("Нажать на ссылку Войти")
    public void clickEnterLink() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterLink));
        driver.findElement(enterLink).click();
    }
    @Step("Отображение ошибки Некорректный пароль")
    public boolean visibilityIncorrectPasswordError() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordError));
        return driver.findElement(incorrectPasswordError).isDisplayed();
    }
    @Step("Заполнить поля Формы регистрации корректными данными")
    public void fillRegistrationFormCorrectData(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }
    @Step("Заполнить поля Формы регистрации некорректными данными")
    public void fillRegistrationFormIncorrectData(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
    }
}
