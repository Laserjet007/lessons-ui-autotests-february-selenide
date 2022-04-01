package ru.gb.lessons.lesson_8;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.gb.lessons.lesson_8.pages.MainPage;
import ru.gb.lessons.lesson_8.pages.block.LoginPopup;

@DisplayName("Авторизация")
public class LoginAndLogoutTest extends BaseTest {

    @Test
    @DisplayName("Успешные Авторизация и выход из системы")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Успешная авторизация под существующим юзером и выход из системы")
    @TmsLink("")
    @Epic("")
    @Feature("Авторизация на сайте")
    @Issue("")
    @Story("")
    void loginAndLogout() {
        new MainPage()
                .getHeader()
                .clickLoginButton()
                .login(user)
                .logout()
                .checkLoginButtonIsVisible();
    }

    @Test
    @DisplayName("Авторизация: Негативный сценарий: неверный пароль")
    @Severity(SeverityLevel.NORMAL)
    void incorrectPasswordTest() {
        LoginPopup loginPopup = new MainPage()
                .getHeader()
                .clickLoginButton();
        loginPopup.login(LOGIN, "incorrectPassword");
        loginPopup.checkErrorIsVisible("Неверный логин или пароль.");
    }
}
