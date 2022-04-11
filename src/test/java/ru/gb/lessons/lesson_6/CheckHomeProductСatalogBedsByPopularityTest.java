package ru.gb.lessons.lesson_6;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Каталог Товаров")
public class CheckHomeProductСatalogBedsByPopularityTest extends BaseTest {

    @SneakyThrows
    @Test
    @DisplayName("Проверка вкладки: Каталог Товаров: Кровати: Подбор по параметрам")
    @Severity(SeverityLevel.MINOR)
    void checkHomeProductСatalogBedsByPopularityTest() {

        String productName = "Подбор по параметрам";

        webDriver.findElement(By.xpath(
                "//a[@class='catalog-nav__section-list-a']//span[text()='Кровати']")).click();
//        webDriver.findElement(By.xpath(
 //               "//div[@class='goods-catalog__toolbar goods-catalog__toolbar-form']")).click();

        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions                             //вариант проверки с ассертом
                        .presenceOfElementLocated(By.xpath("//div[@class='filter']//*[contains(text(),'Подбор по параметрам')]")))
                .getText()).as("Подбор по параметрам").isEqualTo("Подбор по параметрам");                      // проверяем текст на соответствие текст (критично важно для тестирования)

    }
}