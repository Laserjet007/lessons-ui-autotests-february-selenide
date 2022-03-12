package ru.gb.lessons.lesson_4;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data                                                             //добавляем из библиотеки lombok вместо прописывания переменных
@AllArgsConstructor                                               //если требуется указать все параметры конструктора
//@NoArgsConstructor                                              // случае , если нет аргументов
public class Triangle {
//  @Getter                                                       //добавляем из библиотеки lombok вместо переменных если не проставить @Data над методом
//  @Setter
    private int a;                                                //стороны
    private int b;
    private int c;
    private Colour colour;                                        //красим треугольник (создаем класс дополнительный - public enum Colour

    public Triangle(int a, int b, int c) {                        //строим конструктор для colour.
        this.a = a;
        this.b = b;
        this.c = c;
        colour = Colour.WHITE;                                    //Если не указывается какая сторона, то дефолтная сторона белая. Если указываем - то цвет берем из конструктора colour
    }

    private void checkSidesArePositive(){                         //пишем отдельный метод
        if (a <= 0 || b <= 0 || c <= 0) {                         //где указываем что a <= 0 или b <= 0 или c <= 0
            throw new IllegalArgumentException("Sides must be positive");  //то в этом случае кидаем новую ошибку
        }
    }
//проверка на невыраженность:
    private void checkOneSideIsSmallerThanSumOfOthers(){           //пишем отдельный метод
         if (a + b <= c || b + c <= a || c + a <= b) {
             throw new IllegalArgumentException("One side can' be greater than sum of others");  //то в этом случае кидаем новую ошибку
    }
}
    // TODO: 09.03.2022 реализовать метод
    public int countPerimeter() {                                  // метод
        checkSidesArePositive();                                   //проверяем ошибку из конструктора выше
        checkOneSideIsSmallerThanSumOfOthers();
             return a + b + c;                                     // с начала возвращаем 0 - не пишем реализацию
    }
    public void paint(Colour colour) {                             // метод
        if (this.colour == colour) {                               //условие, что если colour совпадает с цветом которым хотим перекрасить - тогда ошибка (для примера, если допустить, что так написано в спецификации)
            throw new IllegalArgumentException("New color must not be equal to old color");
        }
        this.colour = colour;
    }
//еще способ параметризации (метод paint с передачей строки)
        public void paint(String colour) {                          //передаем строку
            paint(Colour.valueOf(colour));                          //вызываем paint (все то же самое как в предидущем конструкторе, только передаем строку аргументов)
            }

}
