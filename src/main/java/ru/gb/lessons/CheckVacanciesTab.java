package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class CheckVacanciesTab {
    public static void main(String[] args) throws InterruptedException {
        String productName ="Вакансия";                                                 //(для поиска элементов по div) выносим отдельную переменную для строки 35
        //String composition1Name ="Композиция №08, спальня (2FCC+H1E+AS6Мelange996+K1C+E1C+ZFE)";
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

        webDriver.findElement(By.className("table"))                 //берем блок элементов
                .findElements(By.xpath("//table[@class='table']//th[@class='vacancy-table__head'][text()='Вакансия']"))                                // взять детей элемента и вывести внутренний
                .forEach(product -> System.out.print(product.getText() + " "));            //+ " "  добавить пробел, что бы не писать вместе
        System.out.println();
        System.out.println("Expected product: " + productName);

        //Thread.sleep(16000);
        webDriver.quit();
    }

}