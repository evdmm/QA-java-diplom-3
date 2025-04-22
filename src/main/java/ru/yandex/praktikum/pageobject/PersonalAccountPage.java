package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private final WebDriver driver;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Заголовок Профиль
    private final By profileTitle = By.xpath(".//a[text()='Профиль']");
    //Кнопка Выйти
    private final By exitButton = By.xpath(".//button[text()='Выход']");

    //Методы
    //Отображение заголовка Профиль
    public boolean visibilityProfileTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(profileTitle));
        return driver.findElement(profileTitle).isDisplayed();
    }
    @Step("Нажать на кнопку Выйти")
    public void clickExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
        driver.findElement(exitButton).click();
    }
}
