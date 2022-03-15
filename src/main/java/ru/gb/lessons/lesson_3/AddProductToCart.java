package ru.gb.lessons.lesson_3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AddProductToCart {
    public static void main(String[] args) throws InterruptedException {
        String productName ="Композиция №08, спальня (2FCC+H1E+AS6Мelange996+K1C+E1C+ZFE)";        //(для поиска элементов по div) выносим отдельную переменную для строки 35
        //String composition1Name ="Композиция №08, спальня (2FCC+H1E+AS6Мelange996+K1C+E1C+ZFE)";
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--blink-settings=imagesEnabled=false");
        WebDriver webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        webDriver.get("https://www.shatura.com/");
        webDriver.manage().window().setSize(new Dimension(1500, 1100));
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //new Actions(webDriver)                                                             //способ накидать каких - либо действий (в данном случае на локатор билдер)
        //        .moveToElement(webDriver.findElement(By.xpath("//div[@class='catalog-nav__section-list']//span[@class='catalog-nav__section-list-li-title']")))  //взять элемент
        //        .clickAndHold()                                                            //сказать что хотим его нажать.  либо использовать трекинтроп
        //        .build()                                                                   // в нашем билдере
        //       .perform();                                                                 //выполнить

        webDriver.findElement(By.xpath(
                "//div[@class='catalog-nav__section-list']//span[@class='catalog-nav__section-list-li-title']")).click();

//findElements:(найти все элементы в данном div(Найдем все карточки, а потом будем с ними работать))
        List<WebElement> products = webDriver.findElements(By.xpath(                         //findElement (найти все элементы в данном div(Найдем все карточки, а потом будем с ними работать))
                "//div[@class='goods-catalog__collection col-lg-6']"));
        WebElement selectedProduct = products.stream()                                       //пробрасываем запрос
                .filter(product -> product.findElement(By.xpath(".//div[text()='144 700 Р']"))
                 .getText().equals("144 700 Р"))                                             //фильтруем товары по названию, ищем текст, ищем соответствие
                .findFirst()                                                                 //находим первый элемент
                .orElseThrow(() -> new NoSuchElementException("productName"));               //если такого элемента нет - выброси ошибку
                 selectedProduct.findElement(By.className("btn__txt")).click();              //найдем внутри элемент


        webDriver.findElement(By.xpath(
                "//button[@onclick='add2basket(119543)']")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='offer-block']//button")).click();
        webDriver.findElement(By.xpath(
                "//div[@class='min-basket__icon-wrap']")).click();

        /**   пример просмотра xpath на исчезающем элементе (остановка для решения различных задач) - пишется в консоли
 *                setTimeout(function() {debugger;}, 3000)
*/
//вывод текста в терминал (типа асерта)
        System.out.print("Actual products: ");

        webDriver.findElement(By.className("items-in-basket__item-inner"))                 //берем блок элементов
                .findElements(By.xpath("./a"))                                // взять детей элемента и вывести внутренний
                .forEach(product -> System.out.print(product.getText() + " "));            //+ " "  добавить пробел, что бы не писать вместе
        System.out.println();
        System.out.println("Expected product: " + productName);

        //Thread.sleep(16000);
        webDriver.quit();
    }

}
