package ru.gb.lessons.lesson_4;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Extension помогают вывести какую либо логику для тестов. так-же сделать аннотации
public class TimerTestExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {     //наследуемся и имплементируем BeforeTestExecutionCallback что бы перед каждым тестом производить какое-либо действие.  AfterTestExecutionCallback - заканчивать запись после теста
    private static Logger logger = LoggerFactory.getLogger(TimerTestExtension.class);                    //настройка уровня логирования для файла logback.xml (Logger  из библиотеки org.slf4j)


    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = (long) extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).get("startTime");  //получаем время остановки теста (берем время "startTime")
        logger.info("End test" + extensionContext.getDisplayName() + " with duration: " + (endTime - startTime) + "ms"); //указываем время которое боло на тест затрачено
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
         long startTime = System.currentTimeMillis();                                                    //перед тестом нужно начать отсчет (засекаем текущие время startTime)
         extensionContext.getStore(ExtensionContext.Namespace.GLOBAL).put("startTime", startTime);       //глобальное хранилище для сбора определенных данных. в нашем случае - startTime сохраняем в startTime
        logger.info("Start test" + extensionContext.getDisplayName()); //указываем в логире.инфо "Start test" - тест начался + какой тест начался
    }

    //    @Override
//    public void beforeAll(ExtensionContext context) throws Exception {                                 //наследуемся и имплементируем BeforeAllCallback. в результате чего перед всеми тестами можно будет указать необходимую нам информацию
//         System.out.println("Hello? I`m from BeforeAllExtension");
 //   }

   //extensionContext.getStore();                     //сохранить время в хранилище контекста
}
