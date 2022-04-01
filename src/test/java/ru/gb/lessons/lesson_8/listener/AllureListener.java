package ru.gb.lessons.lesson_8.listener;

import lombok.SneakyThrows;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import static io.qameta.allure.Allure.addAttachment;
import static io.qameta.allure.Allure.step;

public class AllureListener extends AbstractWebDriverEventListener {

    @Override
    @SneakyThrows
    public void beforeClickOn(WebElement element, WebDriver driver) {
        String actionName = "Кликнуть на " + element.getText();
        step(actionName);
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        step("Успешно!");
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String actionName = "Ввести текст '" + Arrays.toString(keysToSend) + "' в поле " + element.getAttribute("name");
        step(actionName);
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        step("Успешно!");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        addAttachment("ScreenShot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}