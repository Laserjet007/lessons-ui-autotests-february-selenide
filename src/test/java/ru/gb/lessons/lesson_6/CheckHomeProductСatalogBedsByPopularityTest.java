package ru.gb.lessons.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckHomeProductСatalogBedsByPopularityTest extends BaseTest {

    @Test
    @DisplayName("Проверка вкладки: Каталог Товаров: Кровати: по популярности")
    void checkHomeProductСatalogBedsByPopularityTest() {

        String productName = "По популярности";

        webDriver.findElement(By.xpath(
                "//li[@class='catalog-nav__section-list-li']//span[text()='Кровати']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='goods-catalog__toolbar goods-catalog__toolbar-form']")).click();

        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions                             //вариант проверки с ассертом
                        .presenceOfElementLocated(By.xpath("//div[@class='goods-catalog__toolbar goods-catalog__toolbar-form']")))
                .getText()).as("По популярности").isEqualTo("По популярности");                      // проверяем текст на соответствие текст (критично важно для тестирования)
        webDriver.quit();
    }
}