package ru.gb.lessons.lesson_4;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * неудобство таких тестов в том, что не покрывают все позитивные сценарии (классы эквивалентности). так в общем не пишут
 */

@Disabled("Перенесены в параметризированные тесты")                //отключение теста всех тестов
public class TriangleTest {
    //@RepeatedTest(10)                                            //проверка стабильности теста, после проверки можно убрать
    @Test
    @Disabled("Перенесены в параметризированные тесты")            //отключение теста
    @DisplayName("Периметр треугольника 3, 4, 5 должен быть равен 12")   //название теста
    public void countPerimeterEgyptTriangleSuccessfulTest() {      //указываем метод, который хотим протестировать. countPerimeterEgyptTriangleSuccessfulTest - площадь периметра... - так писать не обязательно, зависит от проэкта
       // new Actions(new ChromeDriver());                         заглушка до написания самих тестов, что бы код не ломался
        // assertTrue( true );                                     //assert - проверяет условия
//реализация теста: - первая составляющая из трех - Arrange:
        Triangle triangle = new Triangle(3, 4, 5);        //готовим данные(неудобства такого метода в том, что данные могут содержать очень много строчек кода поэтому существует метод beforeEach, который готовит данные для всего раздела тестов) пример в parametrizedTriangletest
        int perimeter = triangle.countPerimeter();                 //следующая часть - Act:
        assertEquals(12,perimeter);                        //третья часть - Assert:
    }
//unit tests - положительные: первые три теста почти одинаковые (отличаются только входные данные и ожидаемые результаты)
    @Test
    @Disabled("Перенесены в параметризированные тесты")
    @DisplayName("Периметр треугольника 3, 4, 6 должен быть равен 13")
    public void countPerimeterGreater90TriangleSuccessfulTest() {
        Triangle triangle = new Triangle(3, 4, 6);
        int perimeter = triangle.countPerimeter();
        assertEquals(13,perimeter);
    }
    @Test
    @Disabled("Перенесены в параметризированные тесты")
    @DisplayName("Периметр треугольника 3, 3, 3 должен быть равен 9")
    public void countPerimeterWithEqualSidesTriangleSuccessfulTest() {
        Triangle triangle = new Triangle(3, 3, 3);
        int perimeter = triangle.countPerimeter();
        assertEquals(9,perimeter);
    }
//unit tests -  отрицательные:
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 1")
    public void countPerimeter1sideIsZeroFailedTest() {
        Triangle triangle = new Triangle(0, 3, 3);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("Sides must be positive",illegalArgumentException.getMessage());                                        //проверить текст
    }
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 2")
    public void countPerimeter2sideIsZeroFailedTest() {
        Triangle triangle = new Triangle(1, 0, 2);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("Sides must be positive", illegalArgumentException.getMessage());
    }
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 3")
    public void countPerimeter3sideIsZeroFailedTest() {
         Triangle triangle = new Triangle(1, 1, 0);
         IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
         assertEquals("Sides must be positive", illegalArgumentException.getMessage());

        }
//unit tests - отрицательные что и с другими сторонами так же все будет работать:
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 1")
    public void countPerimeter1sideIsNegativeFailedTest() {
        Triangle triangle = new Triangle(-3, 3, 3);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("Sides must be positive",illegalArgumentException.getMessage());                                        //проверить текст
    }
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 2")
    public void countPerimeter2sideIsNegativeFailedTest() {
        Triangle triangle = new Triangle(1, -1, 1);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("Sides must be positive", illegalArgumentException.getMessage());
    }
    @Test
    @DisplayName("Периметр треугольника негатив сценарий 3")
    public void countPerimeter3sideIsNegativeFailedTest() {
        Triangle triangle = new Triangle(1, 1, -1);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("Sides must be positive", illegalArgumentException.getMessage());
    }
//проверка правильности получившихся сторон: одна сторона не может быть ровна сумме двух других
    @Test
    @DisplayName("проверка правильности получившихся сторон:")
    public void countPerimeterInvalidTriangleFailedTest() {
        Triangle triangle = new Triangle(1, 1, 6);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("One side can' be greater than sum of others", illegalArgumentException.getMessage());
    }
//проверка для других сторон
    @Test
    @DisplayName("проверка правильности получившихся других сторон:")
    public void countPerimeterInvalidTriangle2FailedTest() {
        Triangle triangle = new Triangle(1, 2, 1);
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::countPerimeter);  //понять, что есть ошибка - пишем какую ошибку ожидаем
        assertEquals("One side can' be greater than sum of others", illegalArgumentException.getMessage());
    }


}