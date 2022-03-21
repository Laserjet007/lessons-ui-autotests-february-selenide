package ru.gb.lessons.lesson_5;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ActionsTestTest extends BaseTest {

//Actions - способ сбилдить какое - либо действие (в данном случае перемещение элементов) - проверка расположения элементов

    @Test
    void dragAndDropTest() {
        webDriver.get("https://demoqa.com/sortable");

        List<WebElement> elements = webDriver.findElement(By.id("demo-tabpane-list"))                           //создаем отдельную переменную, находим элемент по уверенному id (хотя лучше не использовать id)
                .findElements(By.xpath(".//div[@class='list-group-item list-group-item-action']"));//находим элемент внутри по ч пасу (. - обязательна)


        SoftAssertions softAssertions = new SoftAssertions();                                                   //утилита для проверки всего списка - вне зависимости где он упадет
        softAssertions.assertThat(elements.get(0)).isEqualTo("One");                                    //падает тест при наличии ошибки сразу и дальше не идет - ошибка может начинаться с первой линии
        softAssertions.assertThat(elements.get(1)).isEqualTo("Two");
        softAssertions.assertThat(elements.get(2)).isEqualTo("Three");                                  //проверка данных на корректность по очереди(АССЕРТ С ЧИСЛОМ ДОЛЖЕН РОВНЯТЬСЯ ПОРЯДКОВОМУ НОМЕРУ)
        softAssertions.assertThat(elements.get(3)).isEqualTo("Four");
        softAssertions.assertThat(elements.get(4)).isEqualTo("Five");
        softAssertions.assertThat(elements.get(5)).isEqualTo("Six");
        softAssertions.assertAll();                                                                             //важно указать, что для всех, иначе тест пройдет нормально (важно запускать тест с ошибкой - для понимания, что он видит область тестирования)

//тест: - смысл в перемещении обьектов
        //



    }
}