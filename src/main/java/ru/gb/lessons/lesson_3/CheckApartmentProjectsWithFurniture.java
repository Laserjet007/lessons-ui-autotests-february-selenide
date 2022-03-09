package ru.gb.lessons.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class CheckApartmentProjectsWithFurniture {
    public static void main(String[] args) throws InterruptedException {
        String productName ="Шкаф 1дв. с зерк. (5 полок)";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
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

        webDriver.quit();
    }

}