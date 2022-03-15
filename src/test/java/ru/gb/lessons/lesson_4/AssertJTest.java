package ru.gb.lessons.lesson_4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.gb.lessons.lesson_4.Colour;
import ru.gb.lessons.lesson_4.Triangle;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

//тест для просмотра работы библиотеки <groupId>org.assertj</groupId><artifactId>assertj-core</artifactId><version>3.22.0</version>
public class AssertJTest {        public static Stream<Arguments> triangles() {
    return Stream.of(Arguments.of(new Triangle(3,4,5), 12),        //метод состоит из аргументов. в аргумент Arguments.of() пишем параметры, которые хотели проверить
            Arguments.of(new Triangle(3,4,6), 13),                 //для других тестов тоже самое
            Arguments.of(new Triangle(3,3,3), 9));
}

    @ParameterizedTest(name = "Периметр треугольника: позитивный сценарий, периметр треугольника {0} == {1}")//добавляем имя, равное 0 = 1
    @DisplayName("Тест с прокидыванием параметров")                                  //название теста
    @MethodSource("triangles")                                                       //способов прокидывать много, вот один из них (берутся аргументы из методов-создаем выше метод triangles) .https:junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
    void countPerimeterPositiveTest(Triangle triangle, int expectedResult) {//прокидываем параметры: тестовое тело будет оставаться одним и тем же с TriangleTest, но параметры будут разные. int expectedResult - ожидаемый результат. int perimeter - периметр треугольника {0} == {1}
        int perimeter = triangle.countPerimeter();                                  //следующая часть - Act:
        assertThat(perimeter).as("проверяем что периметр ....")           //импортируем метод assertThat, в параметрах указывается expectedResult. далее делаем проверки: as() если тест упал выведет текст. плюс метода в том что можно накидать много проверок строчками в тело теста
                .isGreaterThanOrEqualTo(0)                                    //проверяем, что периметр больше нуля
                .isEqualTo(expectedResult);                                         //проверяем, что периметр expectedResult
    }
//тест для просмотра работы библиотеки <groupId>org.assertj</groupId> с негативным сценарием
    public static Stream<Arguments> negativeTriangles() {
        return Stream.of(Arguments.of(new Triangle(0,3,3), "Sides must be positive"), //метод состоит из аргументов. в аргумент Arguments.of() пишем параметры, которые хотели проверить
                Arguments.of(new Triangle(3,0,3), "Sides must be positive"),                 //для других тестов тоже самое
                Arguments.of(new Triangle(3,3,0), "Sides must be positive"),
                Arguments.of(new Triangle(3,3,-1), "Sides must be positive"),
                Arguments.of(new Triangle(3,-1,3), "Sides must be positive"),
                Arguments.of(new Triangle(-1,3,3), "Sides must be positive"),
                Arguments.of(new Triangle(-1,3,3), "Sides must be positive"),
                Arguments.of(new Triangle(6,1,1), "One side can' be greater than sum of others"),
                Arguments.of(new Triangle(1,2,1), "One side can' be greater than sum of others"));
    }
    @ParameterizedTest(name = "Периметр треугольника: негативный сценарий (треугольника {0} ошибка: {1}")
    @MethodSource("negativeTriangles")
    @DisplayName("проверка правильности получившихся сторон:")
    public void countPerimeterNegativeTest(Triangle triangle, String errorText) {
        assertThatThrownBy(triangle::countPerimeter)                                  //вызываем метод triangle::countPerimeter
                .isInstanceOf(IllegalArgumentException.class)                         //перечисляем что хотим проверить
                .hasMessage(errorText);
    }
//создаем тестовый метод для демонстрации возможностей библиотеки <groupId>org.assertj</groupId>
    @Test
    void similarTriangleTest() {
        Triangle triangle = new Triangle(3, 3, 3);
        Triangle similarTriangle = triangle.createSimilarTriangle(2);
        assertThat(similarTriangle).usingRecursiveComparison()                        //начинаем проверять Triangle.
                .ignoringFieldsOfTypes(Colour.class)                                  //можно проигнорировать класс
                //.ignoringFields("colour")                                           //для того что бы не проверять цвет, а только стороны - можно использовать такой метод
                .isEqualTo(new Triangle(6, 6, 6, Colour.BLUE));

}

}
