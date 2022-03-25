package ru.gb.lessons.lesson_6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class BaseTest {                                                                         //class BaseTest - шаблон для вставки во все остальные тесты, для уменьшения кода
    protected WebDriver webDriver;                                                              //protected - для выноса в отдельную область видимости для всех остальных тестов // выносим веб драйвер в отдельную переменную (так как сокращаем текс всех тестов)

    public final String LOGIN = "laserjet007@rambler.ru";
    public final String PASSWORD = "999999999";

    @BeforeEach                                                                                            //для того, что бы не повторялся лишний раз код первых трех строк каждого теста - необходимо вынести их в один метод объединяющий все тесты
    void  setUp(){                                                                              //веб драйвер переносим в данном случае из тестов (для уменьшения кода)
        ChromeOptions chromeOptions = new ChromeOptions();                                      //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");                     //создаем аргумент для capabilities, который будет блокировать загрузку фото
        webDriver = WebDriverManager.chromedriver().                                            //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
                capabilities(chromeOptions).create();                                           //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                    //неявное ожидание - по дефолту каждого действия, ждать элемент
        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти
        webDriver.manage().window().setSize(new Dimension(2000, 1500));             //настроить размеры окна браузера
    }

    @AfterEach
    void  tearDown(){
        webDriver.quit();                                                                       //выносим закрытие web driver из тестов
    }

}
