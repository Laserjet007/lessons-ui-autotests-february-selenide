package ru.gb.lessons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");  //говорим библиотеке webdriver где хранится chromdriver, который запустит браузер

        WebDriver webDriver = new ChromeDriver(); {//создаем класс с которым будем работать

            webDriver.get("https://www.shatura.com/");//в сценарии пишем страницу куда нужно перейти
        System.out.println( "Hello World!" );
    }
}
