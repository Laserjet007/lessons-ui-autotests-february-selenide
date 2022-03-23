package ru.gb.lessons.lesson_5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//тесты по работе с окнами, алертами и т.д. (основное для понимания - переключение фокусов)

public class WindowsFramesAlertsTest extends BaseTest {
// тест по переключению фокуса по окнам
    @Test
    void windowSwitchTest() {
        webDriver.get("https://demoqa.com/browser-windows");                                                           //переходим на тестируемую страницу
        webDriver.findElement(By.id("tabButton")).click();                                                             //нажимаем кнопку на странице "New TAb"

        List<String> windowHandles = List.copyOf(webDriver.getWindowHandles());                                        // у вебдрайвера есть несколько окон, и мы получаем все

        assertThat(windowHandles).hasSize(2);                                                                  //проверка, что окна есть, в данном случае - два (hasSize(2))
        webDriver.switchTo().window(windowHandles.get(1));                                                             // переход на определенное окно (первое 0, вторая 1)

        new WebDriverWait(webDriver, 5).until(ExpectedConditions                                         //пишем эту строку второй в коде и проверяем, что тест упадет
                .presenceOfElementLocated(By.xpath("//*[text()='This is a sample page']")));              //проверка, что текста не будет на странице - This is a sample page
    }
//тест по работе с фреймам (страницам в страницах) <iframe src...
    @Test
    void frameSwitchTest() {
        webDriver.get("https://demoqa.com/frames");                                                                    //переходим на тестируемую страницу
        webDriver.switchTo().frame("frame1");                                                                       //переходим по элементам (фреймам - станице в странице)
        new WebDriverWait(webDriver, 5).until(ExpectedConditions                                         //пишем эту строку второй в коде и проверяем, что тест упадет
                .presenceOfElementLocated(By.xpath("//*[text()='This is a sample page']")));              //проверка, что текста не будет на странице - This is a sample page
    }
//тест по работе с alert
    @Test
    void alertTest() {
        webDriver.get("https://demoqa.com/alerts");                                                                    //переходим на тестируемую страницу
        webDriver.findElement(By.id("alertButton")).click();                                                           // находим элемент
        webDriver.switchTo().alert().accept();                                                                         //нажимаем закрыть - accept();
    }

}
