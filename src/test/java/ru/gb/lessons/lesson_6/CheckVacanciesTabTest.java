package ru.gb.lessons.lesson_6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckVacanciesTabTest extends BaseTest {                                                                  //для того что бы исключить в каждом тесте написания @BeforeEach и @AfterEach - можно унаследоваться от написанного ранее теста (делается это для сокращения текста теста - наглядности) (далее это возможно будет сделать через асерт)

    @Test
    @DisplayName("Проверка вкладки: Вакансии")
    void  checkVacanciesTab() {
        String productName ="Вакансия";

        webDriver.findElement(By.xpath(
                "//a[@class='nav-link dropdown-toggle']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='dropdown-menu show']//a[text()='Вакансии']")).click();

        List<String> actualProductsInCart = webDriver.findElement(By.className("table")) //берем блок элементов
                .findElements(By.xpath("//th[text()='Вакансия']"))
                .stream()
                .map(product -> product.findElement(By.xpath("//th[text()='Вакансия']")).getText())       // метод map: когда в .stream() есть некая сущность, из неё можем взять некую под сущность через этот метод (с помощью метода мап мы можем и преобразовать сущность начальную в сущность которую хотим получить - например текст)
                .collect(Collectors.toList());                                                                         //преобразуем в текстовую коллекцию
//проверка ассерта:
        assertThat(actualProductsInCart).containsExactlyInAnyOrder(productName);                                       //импортируем метод Assertions.assertThat указывая с чам сравнивать actualProductsInCart / далее проверяем, что все документы в этом листе соответствуют productName

    }
}
