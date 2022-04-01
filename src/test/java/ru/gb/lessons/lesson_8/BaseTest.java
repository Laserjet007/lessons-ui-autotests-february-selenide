package ru.gb.lessons.lesson_8;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.gb.lessons.lesson6.listener.AllureListener;
import ru.gb.lessons.lesson_8.pojo.User;

import java.util.concurrent.TimeUnit;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class BaseTest {
    protected WebDriver webDriver;
    protected EventFiringWebDriver webDriver;
    public final String LOGIN = "autosupertravel@yandex.ru";
    public final String PASSWORD = "12345678";
    public final User user = new User(LOGIN, PASSWORD);

    @BeforeEach
    void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver = new EventFiringWebDriver(WebDriverManager.chromedriver().capabilities(chromeOptions).create());
        webDriver.register(new AllureListener());
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.get("https://pop-music.ru/");
        webDriver.manage().window().setSize(new Dimension(2000, 1500));
    }

    @AfterEach
    void tearDown() {
        step("Логи браузера", () -> {
            webDriver.manage().logs().get(LogType.BROWSER)
                    .forEach(log -> addAttachment("logs", log.getMessage()));
        });
        webDriver.quit();
    }
}}