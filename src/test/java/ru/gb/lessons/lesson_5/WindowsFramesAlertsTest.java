package ru.gb.lessons.lesson_5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//тесты по работе с окнами, алертами и т.д.

public class WindowsFramesAlertsTest extends BaseTest {

    @Test
    void windowSwitchTest() {
        webDriver.get("https://demoqa.com/browser-windows");                                                           //переходим на тестируемую страницу
        webDriver.findElement(By.id("tabButton")).click();                                                             //нажимаем кнопку на странице "New TAb"

        List<String> windowHandles = List.copyOf(webDriver.getWindowHandles());

        assertThat(windowHandles).hasSize(2);
        webDriver.switchTo().window(windowHandles.get(1));

        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[text()='This is a sample page']")));              //проверка, что текста не будет на странице - This is a sample page (тест должен упасть)
    }

    @Test
    void frameSwitchTest() {
        webDriver.get("https://demoqa.com/frames");
        webDriver.switchTo().frame("frame1");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//*[text()='This is a sample page']")));
    }

    @Test
    void alertTest() {
        webDriver.get("https://demoqa.com/alerts");
        webDriver.findElement(By.id("alertButton")).click();
        webDriver.switchTo().alert().accept();
    }

}
