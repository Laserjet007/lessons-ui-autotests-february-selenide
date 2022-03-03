package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

public class AddProductToCart {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.get("https://www.shatura.com/");
        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        new Actions(webDriver)                                                              //способ накидать каких - либо действий (в данном случае на локатор билдер)
                .moveToElement(webDriver.findElement(By.xpath("//div[@class='catalog-nav__section-list']//span[@class='catalog-nav__section-list-li-title']")))  //взять элемент
                //.clickAndHold()                                                             //сказать что хотим его нажать.  либо использовать трекинтроп
                .build()                                                                    // в нашем билдере
                .perform();                                                                 //выполнить

        webDriver.findElement(By.xpath("//ul[@class='catalog-nav__section-sub-menu-list catalog-nav__section-sub-menu-list_col-2']//a")).click();



        Thread.sleep(36000);

        webDriver.quit();
    }

}
