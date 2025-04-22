package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локаторы
    //Кнопка Личный кабинет
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    //Кнопка Войти в аккаунт
    private final By enterAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //Кнопка Конструктор
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //Кнопка Оформить заказ
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //Изображение логотипа
    private By logoImage = By.xpath(".//div[contains(@class, 'header__logo')]");
    //Таб Булки
    private By bunTab = By.xpath(".//span[text()='Булки']");
    //Таб Булки
    private By souceTab = By.xpath(".//span[text()='Соусы']");
    //Таб Булки
    private By toppingTab = By.xpath(".//span[text()='Начинки']");
    //Раздел Булки
    private By bunSection = By.xpath(".//h2[text()='Булки']");
    //Раздел Соусы
    private By souceSection = By.xpath(".//h2[text()='Соусы']");
    //Раздел Начинки
    private By toppingSection = By.xpath(".//h2[text()='Начинки']");
    //Активный раздел
    private By activeSection = By.xpath(".//div[contains(@class,'current')]/span");

    //Методы
    @Step("Нажать на кнопку Войти в аккаунт")
    public void clickEnterAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(enterAccountButton));
        driver.findElement(enterAccountButton).click();
    }
    @Step("Нажать на кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(personalAccountButton));
        driver.findElement(personalAccountButton).click();
    }
    @Step("Нажать на кнопку Конструктор")
    public void clickConstructorButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(constructorButton));
        driver.findElement(constructorButton).click();
    }
    @Step("Нажать на изображение логотипа")
    public void clickLogoImage() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(logoImage));
        driver.findElement(logoImage).click();
    }
    @Step("Отображение кнопки Оформить заказ")
    public boolean visibilityCreateOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
        return driver.findElement(createOrderButton).isDisplayed();
    }
    @Step("Нажать на таб Булки")
    public void clickBunTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunTab));
        driver.findElement(bunTab).click();
    }
    @Step("Нажать на таб Соусы")
    public void clickSouceTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(souceTab));
        driver.findElement(souceTab).click();
    }
    @Step("Нажать на таб Начинки")
    public void clickToppingTab() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(toppingTab));
        driver.findElement(toppingTab).click();
    }
    @Step("Отображение раздела Булки")
    public boolean visibilityBunSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(bunSection));
        return driver.findElement(bunSection).isDisplayed();
    }
    @Step("Отображение раздела Соусы")
    public boolean visibilitySouceSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(souceSection));
        return driver.findElement(souceSection).isDisplayed();
    }
    @Step("Отображение раздела Начинки")
    public boolean visibilityToppingSection() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(toppingSection));
        return driver.findElement(toppingSection).isDisplayed();
    }
    @Step("Получение заголовка активного раздела")
    public String getActiveSectionTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(activeSection));
        return driver.findElement(activeSection).getText();
    }
}

