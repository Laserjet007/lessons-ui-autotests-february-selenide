package ru.gb.lessons.lesson_8.extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.qameta.allure.selenide.LogType;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import ru.gb.lessons.lesson_8.pojo.User;

import java.util.logging.Level;

import static com.codeborne.selenide.Selenide.open;

public class UITestExtension implements BeforeAllCallback, AfterEachCallback, BeforeEachCallback {

    public final static String LOGIN = "autosupertravel@yandex.ru";
    public final static String PASSWORD = "12345678";
    public final static User user = new User(LOGIN, PASSWORD);

    @Override
    public void afterEach(ExtensionContext extensionContext) throws Exception {
        Selenide.clearBrowserCookies();                                                                   // чистим куки
        Selenide.clearBrowserLocalStorage();                                                              // чистим локалстореж
        WebDriverRunner.clearBrowserCache();                                                              // чистим кеш
    }

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        Configuration.baseUrl = "https://pop-music.ru/";
        Configuration.timeout = 5000;
        Configuration.browser = "chrome";
        Configuration.browserSize = "2000x1500";

    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        open("/");
        SelenideLogger.addListener("AllureListener", new AllureSelenide()
                .savePageSource(true).screenshots(true).enableLogs(LogType.BROWSER, Level.WARNING));  //сохраняе пейджсорсы в алюр
    }
}

