package ru.gb.lessons.lesson_8.pages.block;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import ru.gb.lessons.lesson_8.pages.BaseView;
import ru.gb.lessons.lesson_8.pages.ProductPage;

import java.time.Duration; /

import static com.codeborne.selenide.Selenide.*;

public class MainHeader {

    private final SelenideElement loginButton = $x("//div[@class='header__links']//a[div[text()='Войти']]");
//    public MainHeader(WebDriver webDriver) {
//        super(webDriver);
//    }

    @Step("Перейти на страницу {tab1} -> {tab2}")
    public ProductPage goToProductPage(String tab1, String tab2) {
        $x("//ul[@data-type='header']//li/a[text()='" + tab1 + "']");                                     // перемещение элемента
        $x("//ul[@data-type='header']//li/a[text()='" + tab2 + "']").click();
        return page(ProductPage.class);
    }

    @Step("Проверить, что кнопка 'Войти' отображается на странице")
    public MainHeader checkLoginButtonIsVisible() {
        $x("//div[@class='header__links']//a[div[text()='Войти']]").should(Condition.visible.because("так как не авторизованы"), Duration.ofSeconds(5));   // ожидание появления элемента
        //       new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='header__links']//a[div[text()='Войти']]")));
        return this;
    }

    @Step("Кликнуть на кнопку 'Войти'")
    public LoginPopup clickLoginButton() {
        loginButton.click();
        return new LoginPopup();
    }

    @Step("Кликнуть на кнопку 'Выйти'")
    public MainHeader logout() {
        $("div.header__user").click();            //css локаторы обозначаются просто $
        $x("//a[text()='Выйти']").should(Condition.visible.because("так как авторизованы"), Duration.ofSeconds(5))   // ожидание появления элемента
        .click();
//        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//a[text()='Выйти']"))))
//                .click();
        return new MainHeader();
    }
}