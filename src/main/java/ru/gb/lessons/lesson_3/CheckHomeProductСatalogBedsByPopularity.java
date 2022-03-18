package ru.gb.lessons.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckHomeProductСatalogBedsByPopularity {
    public static void main(String[] args) throws InterruptedException {
        String productName = "По популярности";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.get("https://www.shatura.com/");
        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        webDriver.findElement(By.xpath(
                "//li[@class='catalog-nav__section-list-li']//span[text()='Кровати']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='goods-catalog__toolbar goods-catalog__toolbar-form']")).click();

        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions                    //вариант проверки с ассертом
                        .presenceOfElementLocated(By.xpath("//div[@class='goods-catalog__toolbar goods-catalog__toolbar-form']")))
                .getText()).as("По популярности").isEqualTo("По популярности");  // проверяем текст на соответствие текст (критично важно для тестирования)
        webDriver.quit();
    }
}