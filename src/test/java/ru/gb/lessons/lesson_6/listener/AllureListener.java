package ru.gb.lessons.lesson_6.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

//реализация методов из родительского метода
public class AllureListener extends AbstractWebDriverEventListener {

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
//      addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));    //записать скриншот в этом действии (делают в случае падения
        String actionName = "Кликнуть на " + element.getText();                                                        //записываем как будет называться действие (нажмем на кнопку и укажем на какую)
        step(actionName);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        step("Успешно!");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
//      addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));    //записать скриншот в этом действии
        String actionName = "Ввести текст " + Arrays.toString(keysToSend) + " в поле " + element.getAttribute("id");  //записываем как будет называться действие (ввести текст)
        step(actionName);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        step("Успешно!");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));   // при возникновении ошибки, при проверке асерта - будет выводить скриншот

    }
}
