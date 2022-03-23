package ru.gb.lessons.lesson_5;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

//тест на загрузку файла
public class UploadFileTest extends BaseTest{
    @SneakyThrows
    @Test
    void uploadFileTest() {
        webDriver.get("http://the-internet.herokuapp.com/upload");                                                     //переходим на тестируемую страницу
        webDriver.findElement(By.xpath("//input[@type='file']"))   //находим элемент
                .sendKeys("C:\\java\\lessons-ui-autotests-february\\src\\test\\resources\\logback.xml");  //вставляем путь файла который нужно загрузить
        Thread.sleep(5000);   //подождать до появления файла
    }

}
