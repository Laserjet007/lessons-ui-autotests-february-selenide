package ru.gb.lessons.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckApartmentProjectsWithFurnitureTest extends BaseTest {                                                //для того что бы исключить в каждом тесте написания @BeforeEach и @AfterEach - можно унаследоваться от написанного ранее теста (делается это для сокращения текста теста - наглядности)(далее это возможно будет сделать через асерт)

    @Test
    @DisplayName("Проверка вкладки: Проекты квартир с мебелью")
    void checkApartmentProjectsWithFurniture () {

        String productName ="ЖК Видный Берег 2. Квартира-студия (интерьер в классическом стиле)";

        webDriver.findElement(By.xpath(
                "//div[text()='Готовые решения']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='tv-section__items row']//span[text()='ЖК Видный Берег 2. Квартира-студия (интерьер в классическом стиле)']")).click();

        List<String> actualProductsInCart = webDriver.findElement(By.className("section-title__text"))                 //берем блок элементов
                .findElements(By.xpath("//div//span[text()='ЖК Видный Берег 2. Квартира-студия (интерьер в классическом стиле)']"))
                .stream()
                .map(product -> product.findElement(By.xpath("//span[text()='ЖК Видный Берег 2. Квартира-студия (интерьер в классическом стиле)']")).getText())                   // метод map: когда в .stream() есть некая сущность, из неё можем взять некую под сущность через этот метод (с помощью метода мап мы можем и преобразовать сущность начальную в сущность которую хотим получить - например текст)
                .collect(Collectors.toList());                                                                         //преобразуем в текстовую коллекцию
//проверка ассерта:
        assertThat(actualProductsInCart).containsExactlyInAnyOrder(productName);                                       //импортируем метод Assertions.assertThat указывая с чам сравнивать actualProductsInCart / далее проверяем, что все документы в этом листе соответствуют productName

    }
}
