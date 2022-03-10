package ru.gb.lessons;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import ru.gb.lessons.lesson_4.Triangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParametrizedTriangleTest {
    @ParameterizedTest(name = "Периметр треугольника: позитивный сценарий, периметр треугольника {0} == {1}")//добавляем имя, равное 0 = 1
    @DisplayName("Тест с прокидыванием параметров")                                  //название теста
    void countPerimeterPositiveTest(Triangle triangle, int expectedResult) {//прокидываем параметры: тестовое тело будет оставаться одним и темже с TriangleTest, но параметры будут разные. int expectedResult - ожидаемый результат
        int perimeter = triangle.countPerimeter();                                  //следующая часть - Act:
        assertEquals(expectedResult,perimeter);                                     //третья часть - Assert:
    }
}
