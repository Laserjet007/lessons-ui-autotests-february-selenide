package ru.gb.lessons.lesson_5;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
public class ActionsTestTest extends BaseTest {

//Actions - способ сбилдить какое - либо действие (в данном случае перемещение элементов) - проверка расположения элементов

    @Test
    void dragAndDropTest() {
        webDriver.get("https://demoqa.com/sortable");

        List<WebElement> elements = webDriver.findElement(By.xpath("//a[@id='demo-tab-list']"))                           //создаем отдельную переменную, находим элемент по уверенному id (хотя лучше не использовать id)
                .findElements(By.xpath(".//div[@class='list-group-item list-group-item-action']"));//находим элемент внутри по ч пасу (. - обязательна)


        SoftAssertions softAssertions = new SoftAssertions();                                                   //утилита для проверки всего списка - вне зависимости где он упадет
        softAssertions.assertThat(elements.get(0).getText()).isEqualTo("One");;                                    //падает тест при наличии ошибки сразу и дальше не идет - ошибка может начинаться с первой линии
        softAssertions.assertThat(elements.get(1).getText()).isEqualTo("Two");
        softAssertions.assertThat(elements.get(2).getText()).isEqualTo("Three");                                  //проверка данных на корректность по очереди(АССЕРТ С ЧИСЛОМ ДОЛЖЕН РОВНЯТЬСЯ ПОРЯДКОВОМУ НОМЕРУ)
        softAssertions.assertThat(elements.get(3).getText()).isEqualTo("Four");
        softAssertions.assertThat(elements.get(4).getText()).isEqualTo("Five");
        softAssertions.assertThat(elements.get(5).getText()).isEqualTo("Six");
        softAssertions.assertAll();                                                                             //важно указать, что для всех, иначе тест пройдет нормально (важно запускать тест с ошибкой - для понимания, что он видит область тестирования)

//тест: - смысл в перемещении обьектов
        Actions actions = new Actions(webDriver);                                                               //создаем обьект класса Actions, что бы затем его переиспользовать

        actions.dragAndDrop(elements.get(0), elements.get(5))                                                   //метод перетаскивающий один элемент на другой
//        actions.moveToElement(elements.get(0))                                                                //выстраиваем actions, первое - наводим курсор moveToElement. elements.get() -на этот элемент
//                .clickAndHold()                                                                               //нажать на элемент и не отпускать мышь
//                .pause(Duration.ofSeconds(1))                                                                 //сделать паузу на секунду
//                .moveToElement(elements.get(5))                                                               //перенести элемент на другое место
//                .release()                                                                                    //отпустить мышку
                .build()                                                                                        //нажать билд (собрать)
                .perform();                                                                                     //выполнить

// пишем ассерт
        softAssertions.assertThat(elements.get(0).getText()).isEqualTo("Three");
        softAssertions.assertThat(elements.get(1).getText()).isEqualTo("Four");                                  //проверка данных на корректность по очереди(АССЕРТ С ЧИСЛОМ ДОЛЖЕН РОВНЯТЬСЯ ПОРЯДКОВОМУ НОМЕРУ)
        softAssertions.assertThat(elements.get(2).getText()).isEqualTo("Five");
        softAssertions.assertThat(elements.get(3).getText()).isEqualTo("Six");
        softAssertions.assertThat(elements.get(4).getText()).isEqualTo("One");
        softAssertions.assertThat(elements.get(5).getText()).isEqualTo("Two");
        softAssertions.assertAll();

    }
}