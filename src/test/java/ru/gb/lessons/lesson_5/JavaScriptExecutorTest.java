package ru.gb.lessons.lesson_5;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

//способ через java скрипт закрыть (не воспроизводить) какое-либо действие на странице (в данном случае - быстро закрывает окно)

public class JavaScriptExecutorTest extends BaseTest{
    @SneakyThrows
    @Test
    void javaScriptExecutorTest() {                                                                                    //тест на закрытие всплывающего окна
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;                                        //инициализируем обьект javascriptExecutor //используется в java синхронный метод (последовательное выполнение действий)

        webDriver.get("https://demoqa.com/modal-dialogs");                                                             //переходим на url
        WebElement showSmallModalButton = webDriver.findElement(By.id("showSmallModal"));                              //выделяем в отдельный элемент showSmallModalButton
        javascriptExecutor.executeScript("arguments[0].click()", showSmallModalButton);                             // нажимаем на кнопку (выплывает окно)  //нажать на кнопку с помощью java скрипка
        WebElement modalWindow = webDriver.findElement(By.xpath("//div[contains(@class, 'modal-dialog')]"));  //нажимаем закрыть (кнопку в окне)
        javascriptExecutor.executeScript("arguments[0].remove()", modalWindow);                                     //передаем в java скрипке удалить -"arguments[0].remove()", modalWindow
        Thread.sleep(5000);                                                                                      // остановка для просмотра того что элемент исчез
    }

}
