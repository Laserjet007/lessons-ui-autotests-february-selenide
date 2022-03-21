package ru.gb.lessons.lesson_5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

//тест на перемещение слайдера (ползунка)
public class PriceFilterTest extends BaseTest {
    @Test
    void priceFilterTest() {
        webDriver.get("https://pop-music.ru/catalog/gitaryi/akusticheskie-bas-gitary/");   //для проверки ползунка можно использовать одну страницу (нет необходимости проверять его на каждой странице отдельно(проверить бизнес идею))
//получаем данные стартовые данные (обычно они идут с документацией) поэтому они не пишутся в тесте таким образом
        int startPrice = Integer.parseInt(webDriver.findElement(By.id("arrFilterElement_P1_MIN")).getAttribute("value").replaceAll("([^0-9]*)",""));// берем элемент по id, replasceAll()) - регулярное выражение которое заменяет все элементы от 0 до 9 и вместо них втавлять пробел
        int toPrice = Integer.parseInt(webDriver.findElement(By.id("arrFilterElement_P1_MAX")).getAttribute("value").replaceAll("([^0-9]*)",""));  //берем сумму наибольшею от возможной цены
        int quarter = (toPrice - startPrice) / 4;                                          //ожидаемый результат (расчитываем движение ползунка на четверть)

       List<WebElement> sliders = webDriver.findElements(By.xpath("//div[@role='slider']"));   // обозначаем элементы которые необходимо двигать

        Actions actions = new Actions(webDriver);                                          //выносим вебдрайвер в отдельную переменную

        Point slider1point = sliders.get(0).getLocation();                                 //померить расстояние между элементами, что бы отсчитать четверть и на нее передвинуть, задействуя координаты
        Point slider2point = sliders.get(0).getLocation();

        System.out.println(startPrice);

    }
}
