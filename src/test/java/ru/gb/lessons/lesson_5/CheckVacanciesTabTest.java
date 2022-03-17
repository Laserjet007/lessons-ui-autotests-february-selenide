package ru.gb.lessons.lesson_5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.util.concurrent.TimeUnit;

public class CheckVacanciesTabTest extends BaseTest{                                      //для того что бы исключить в каждом тесте написания @BeforeEach и @AfterEach - можно унаследоваться от написанного ранее теста (делается это для сокращения текста теста - наглядности) (далее это возможно будет сделать через асерт)

    @Test
    void  checkVacanciesTab() {
        String productName ="Вакансия";

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
    }
}
