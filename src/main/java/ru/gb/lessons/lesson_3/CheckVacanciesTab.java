package ru.gb.lessons.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class CheckVacanciesTab {
    public static void main(String[] args) throws InterruptedException {
        String productName ="Вакансия";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.get("https://www.shatura.com/");
        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath(
                "//a[@class='nav-link dropdown-toggle']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='dropdown-menu show']//a[text()='Вакансии']")).click();

        //вывод текста в терминал (типа асерта)
        System.out.print("Actual products: ");

        webDriver.findElement(By.className("table"))
                .findElements(By.xpath("//table[@class='table']//th[@class='vacancy-table__head'][text()='Вакансия']"))
                .forEach(product -> System.out.print(product.getText() + " "));
        System.out.println();
        System.out.println("Expected product: " + productName);

        webDriver.quit();
    }

}