package ru.gb.lessons.lesson_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {                                                                         //class BaseTest - шаблон для вставки во все остальные тесты, для уменьшения кода
    protected WebDriver webDriver;                                                              //protected - для выноса в отдельную область видимости для всех остальных тестов
                                                                                                // выносим веб драйвер в отдельную переменную (так как сокращаем текс всех тестов)

    @BeforeEach
                                                                                                //для того, что бы не повторялся лишний раз код первых трех строк каждого теста - необходимо вынести их в один метод объединяющий все тесты
    void  setUp(){                                                                              //веб драйвер переносим в данном случае из тестов (для уменьшения кода)
        ChromeOptions chromeOptions = new ChromeOptions();                                      //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");                     //создаем аргумент для capabilities, который будет блокировать загрузку фото
        webDriver = WebDriverManager.chromedriver().                                            //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
                capabilities(chromeOptions).create();                                           //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
    }

    @AfterEach
    void  tearDown(){
        webDriver.quit();                                                                       //выносим закрытие web driver из тестов
    }

}
