package ru.gb.lessons.lesson_5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class CheckApartmentProjectsWithFurnitureTest {
    WebDriver webDriver;                                                                        // выносим веб драйвер в отдельную переменную (так как сокращаем текс всех тестов)

    @BeforeEach
        //для того, что бы не повторялся лишний раз код первых трех строк каждого теста - необходимо вынести их в один метод объединяющий все тесты
    void  setUp(){                                                                              //веб драйвер переносим в данном случае из тестов (для уменьшения кода)
        ChromeOptions chromeOptions = new ChromeOptions();                                      //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");                     //создаем аргумент для capabilities, который будет блокировать загрузку фото
        webDriver = WebDriverManager.chromedriver().                                  //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
                capabilities(chromeOptions).create();                                           //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
    }

    @AfterEach
    void  tearDown(){
        webDriver.quit();                                                                       //выносим закрытие web driver из тестов
    }

    @Test
    void checkApartmentProjectsWithFurniture () {
        String productName ="Шкаф 1дв. с зерк. (5 полок)";
        webDriver.get("https://www.shatura.com/");
        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath(
                "//div[text()='Готовые решения']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='tv-section__items row']//span[text()='ЖК Видный Берег 2. Квартира-студия (интерьер в классическом стиле)']")).click();

        //вывод текста в терминал (типа асерта)
        System.out.print("Actual products: ");
        webDriver.findElement(By.className("product-tile__title"))
                .findElements(By.xpath("//a[text()='Шкаф 1дв. с зерк. (5 полок)']"))
                .forEach(product -> System.out.print(product.getText() + " "));
        System.out.println();
        System.out.println("Expected product: " + productName);

    }





}
