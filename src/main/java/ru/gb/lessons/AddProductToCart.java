package ru.gb.lessons;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AddProductToCart {
    public static void main(String[] args) throws InterruptedException {
        String productName ="9 600 Р/мес.";                                            //(для поиска элементов по div) выносим отдельную переменную для строки 35
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
                .filter(product -> product.findElement(By.xpath(".//span[@class='collection-preview__credit-link']//a[text()='9 600 Р/мес.']"))
                 .getText().equals("9 600 Р/мес."))                                     //фильтруем товары по названию, ищем текст, ищем соответствие
                .findFirst()                                                                 //находим первый элемент
                .orElseThrow(() -> new NoSuchElementException("productName"));               //если такого элемента нет - выброси ошибку
                 selectedProduct.findElement(By.className("btn__txt")).click();              //найдем внутри элемент
        Thread.sleep(16000);
        //webDriver.findElement(By.xpath(
              //  "//div[@class='collection-preview']//a[text()='Chelsea серый/туя']/@href")).click();


        webDriver.quit();
    }

}
