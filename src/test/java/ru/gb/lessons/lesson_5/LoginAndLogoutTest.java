package ru.gb.lessons.lesson_5;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginAndLogoutTest extends BaseTest{                                                 //для того что бы исключить в каждом тесте написания @BeforeEach и @AfterEach - можно унаследоваться от написанного ранее теста (делается это для сокращения текста теста - наглядности)(далее это возможно будет сделать через асерт)
    //WebDriver webDriver;                                                                        // выносим веб драйвер в отдельную переменную (так как сокращаем текс всех тестов)

    //@BeforeEach                                                                                 //для того, что бы не повторялся лишний раз код первых трех строк каждого теста - необходимо вынести их в один метод объединяющий все тесты
    //void  setUp(){                                                                              //веб драйвер переносим в данном случае из тестов (для уменьшения кода)
    //    ChromeOptions chromeOptions = new ChromeOptions();                                      //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
    //    chromeOptions.addArguments("--blink-settings=imagesEnabled=false");                     //создаем аргумент для capabilities, который будет блокировать загрузку фото
    //    webDriver = WebDriverManager.chromedriver().                                            //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
    //            capabilities(chromeOptions).create();                                           //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
    //}

    //@AfterEach
    //void  tearDown(){
    //    webDriver.quit();                                                                       //выносим закрытие web driver из тестов (остальные функции в теле теста лучше не писать - тест должен включать именно тест)
    //}

    @Test
    void loginAndLogout(){
        //ChromeOptions chromeOptions = new ChromeOptions();                                    //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
        //.addArguments("--blink-settings=imagesEnabled=false");                                //создаем аргумент для capabilities, который будет блокировать загрузку фото
        //WebDriver webDriver = WebDriverManager.chromedriver().                                //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
        //        capabilities(chromeOptions).create();                                         //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти
        webDriver.manage().window().setSize(new Dimension(1500, 1100));             //настроить размеры окна браузера
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                    //неявное ожидание - по дефолту каждого действия, ждать элемент
        //webDriver.findElement(By.xpath(""));                                                  //метод для поиска элемента (findElements  - элементов)
        //webDriver.findElement(By.id(""));                                                     //метод для поиска элемента по id
        webDriver.findElement(By.xpath                                                          //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                 ("//div[@class='signin-link']//span[@class='signin-link__title']"))
                .click();
        WebElement modal__login = webDriver.findElement(By.xpath                               //выносим авторизацию в отдельную переменную, что бы проще было искать элементы
                ("//div[@class='modal__login']"));                                //WebElement modal__login = webDriver.findElement(By.xpath).var

        //By authModal__Login_Locator = By.xpath("//div[@class='modal__login']");               //создали патч что бы вставить в следующую строку
        //new WebDriverWait(webDriver,5).until(                                                 //остановка в случае ошибки, для возможности открытия (до нажатия клика). тайм аут 5 сек в течении которых идут попытки дождаться действия через каждые 5 милисек. дальше указываем элемент который нужно обнаружить. ExpectedConditions. elementToBeClickable -ждять пока элемент станет кликабельным, yextToBe - пока не появится текст,
        //      ExpectedConditions.presenceOfElementLocated(authModal__Login_Locator));         //эта конструкция называется - явное ожидание
        //new WebDriverWait(webDriver,5,500).until(                                             //ожидание , когда элемент станет видимым (именно элемент, а не как в предидущей строке - локатор)
        //      ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[@class='modal__login']"))));
        modal__login.findElement(By.name("email")).sendKeys("laserjet007@rambler.ru");//ищем внутри созданной переменной, найти элемент по имени. sendKeys -ввод текста
        modal__login.findElement(By.name("password")).sendKeys("999999999");       //то же самое с вводом пароля
        modal__login.findElement(By.className("btn__txt")).click();
        //modal__login.findElement(By.xpath(".//button[span[text()='Войти']]")).click           .// - точка означает найти элемент в созданной в идеи мной - классе элементов modal__login.)
        webDriver.findElement(By.xpath("//div[@class='signin-link']//span[@class='signin-link__title']")).click();
        //new WebDriverWait(webDriver,5).until(                                                 //подождать когда исчезнет элемент  в случае если он висит
        //        ExpectedConditions.invisibilityOf(modal__login));
        //webDriver.findElement(By.xpath("//div[@class='dropdown-menu show']//a[text()='Выйти']")).click();
        // Thread.sleep(36000);                                                                 // остановка на 10 секунд проверить откроется ли страница
//вариант проверки с ассертом:
        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions       //вариант проверки с ассертом
                        .presenceOfElementLocated(By.xpath("//a[@data-toggle='dropdown']//span[@class='signin-link__title']")))
                .getText()).as(" Александр 1 ").isEqualTo(" Александр 1 ");  // проверяем текст на соответствие текст (критично важно для тестирования)
    }

//второй тест на негативный сценарий (при нажатии на "Войти" будет выводиться определенная ошибка, которую необходимо найти)

    @Test
    void incorrectPasswordTest() {
        //ChromeOptions chromeOptions = new ChromeOptions();                                    //создаем переменную куда добавим условие capabilities() - не загружать фото сайта во время теста для ускорения
        //chromeOptions.addArguments("--blink-settings=imagesEnabled=false");                   //создаем аргумент для capabilities, который будет блокировать загрузку фото
        //WebDriver webDriver  = WebDriverManager.chromedriver().                               //safaridriver().create(); упрощаем создание веб драйва добавляя в мавин депенденси <!-- https://mvnrepository.com/artifact/io.github.bonigarcia/webdrivermanager -->//<dependency>//    <groupId>io.github.bonigarcia</groupId>//    <artifactId>webdrivermanager</artifactId>//    <version>5.1.0</version>//</dependency>
        //        capabilities(chromeOptions).create();                                         //capabilities() добавляем chromeOptions что бы исключить загрузку фото при тестировании
        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти
        webDriver.manage().window().setSize(new Dimension(1500, 1100));             //настроить размеры окна браузера
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);                    //неявное ожидание - по дефолту каждого действия, ждать элемент
        //webDriver.findElement(By.xpath(""));                                                  //метод для поиска элемента (findElements  - элементов)
        //webDriver.findElement(By.id(""));                                                     //метод для поиска элемента по id
        webDriver.findElement(By.xpath                                                          //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                 ("//div[@class='signin-link']//span[@class='signin-link__title']"))
                .click();
        WebElement modal__login = webDriver.findElement(By.xpath                                //выносим авторизацию в отдельную переменную, что бы проще было искать элементы
                ("//div[@class='modal__login']"));                                 //WebElement modal__login = webDriver.findElement(By.xpath).var

        //By authModal__Login_Locator = By.xpath("//div[@class='modal__login']");               //создали патч что бы вставить в следующую строку
        //new WebDriverWait(webDriver,5).until(                                                 //остановка в случае ошибки, для возможности открытия (до нажатия клика). тайм аут 5 сек в течении которых идут попытки дождаться действия через каждые 5 милисек. дальше указываем элемент который нужно обнаружить. ExpectedConditions. elementToBeClickable -ждять пока элемент станет кликабельным, yextToBe - пока не появится текст,
        //      ExpectedConditions.presenceOfElementLocated(authModal__Login_Locator));         //эта конструкция называется - явное ожидание
        //new WebDriverWait(webDriver,5,500).until(                                             //ожидание , когда элемент станет видимым (именно элемент, а не как в предидущей строке - локатор)
        //      ExpectedConditions.visibilityOf(webDriver.findElement(By.xpath("//div[@class='modal__login']"))));
        modal__login.findElement(By.name("email")).sendKeys("laserjet007@rambler.ru");//ищем внутри созданной переменной, найти элемент по имени. sendKeys -ввод текста
        modal__login.findElement(By.name("password")).sendKeys("incorrect_password"); //то же самое с вводом пароля
        modal__login.findElement(By.className("btn__txt")).click();
        //new WebDriverWait(webDriver, 5).until(ExpectedConditions                              //вариант проверки без ассерта - ожидаем пока появится текст, затем проверяем что есть //div[@id='sendPasswordError'
        //       .presenceOfElementLocated((By.xpath("//span[text()='Неверный логин или пароль.']")))Неверный логин или пароль.
        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions       //вариант проверки с ассертом
                .presenceOfElementLocated(By.xpath("//span[text()='Неверный логин или пароль.']")))
                .getText()).as("был указан неверный пароль").isEqualTo("Неверный логин или пароль.");  // проверяем текст на соответствие текст (критично важно для тестирования)
    }
}
