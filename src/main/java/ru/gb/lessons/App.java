package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/**
 *
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
/**    System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");     говорим библиотеке webdriver где хранится chromdriver, который запустит браузер
*      WebDriver webDriver = new ChromeDriver();                                                 создаем класс с которым будем работать
*/
        WebDriver webDriver = WebDriverManager.chromedriver().create();                         //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>

        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти

        webDriver.manage().window().setSize(new Dimension(1500, 1100));             //настроить размеры окна браузера
        //webDriver.findElement(By.xpath(""));                                                  //метод для поиска элемента (findElements  - элементов)
        //webDriver.findElement(By.id(""));                                                      //метод для поиска элемента по id
        webDriver.findElement(By.xpath                                                          //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                ("//div[@class='signin-link']//span[@class='signin-link__title']"))
                .click();
        Thread.sleep(6000);                                                               //остановка на 10 секунд проверить откроется ли страница
        webDriver.quit();                                                                       //закрываем webdriver

    }
}