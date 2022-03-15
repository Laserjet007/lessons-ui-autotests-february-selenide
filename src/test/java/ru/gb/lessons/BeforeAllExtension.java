package ru.gb.lessons;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
//Extension помогают вывести какую либо логику для тестов. так-же сделать аннотации
public class BeforeAllExtension implements BeforeAllCallback {            //наследуемся и имплементируем BeforeAllCallback. в результате чего перед всеми тестами можно будет указать необходимую нам информацию
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
         System.out.println("Hello? I`m from BeforeAllExtension");
    }
}
