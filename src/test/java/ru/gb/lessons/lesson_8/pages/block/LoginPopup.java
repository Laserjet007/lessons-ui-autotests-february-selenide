package ru.gb.lessons.lesson_8.pages.block;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WrapsElement;
import ru.gb.lessons.lesson_8.pojo.User;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPopup implements WrapsElement {
    @Getter
    private final SelenideElement wrappedElement = $(By.xpath("//div[@class='popup-login__tab is-active']"));   //в место WebElement в силиниде используют  SelenideElement
//    private WebDriver webDriver;
//    public LoginPopup(WebDriver webDriver) {
//        this.webDriver = webDriver;
//        this.wrappedElement = $(By.xpath("//div[@class='popup-login__tab is-active']"));               // $ можно заменить findElement
//    }

    @Step("Авторизоваться юзером {user.login} {user.password}")
    public MainHeader login(User user) {
        return login(user.getLogin(), user.getPassword());
    }

    @Step("Авторизоваться юзером {login} {password}")
    public MainHeader login(String login, String password) {
        getWrappedElement().$(By.name("USER_LOGIN")).sendKeys(login);
        getWrappedElement().$(By.name("USER_PASSWORD")).sendKeys(password);
        getWrappedElement().$(By.xpath(".//button[span[text()='Войти']]")).click();
        return new MainHeader();
    }
    public void checkErrorIsVisible(String errorText) {
        $x("//span[@class='errortext']").shouldHave(Condition.exactText("Был указан некорректный пароль" ));   //exactText - абсолютно такой же текст
//        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='errortext']")))
//                .getText()).as("Был указан некорректный пароль").isEqualTo(errorText);
    }
}