package ru.gb.lessons.lesson_5;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//авторизация по токинам, или кукам (тест на авторизацию через ui)

//простая авторизация
public class AuthTest extends BaseTest{
    @Test
    void authWithUiTest() {
        webDriver.get("http://the-internet.herokuapp.com/login");                                                      //заходим на сайт
        webDriver.findElement(By.id("username")).sendKeys("tomsmith");                                    //вводим валидные данные
        webDriver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        webDriver.findElement(By.xpath("//button[@type='submit']")).click();                              //кликаем
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'You logged into a secure area!')]")));  //сравниваем текс после авторизации - 'You logged into a secure area!')   (найти любой элемент = следующему 'You logged into a secure area!')
    }
//авторизация через куки
    @Test
    void authWithCookieTest() {
        webDriver.get("http://the-internet.herokuapp.com/secure");                                                     //заходим на сайт
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'You must login to view the secure area!')]")));  //проверяем что совпадает надпись на страничке под авторизованным пользователем
        webDriver.manage().addCookie(new Cookie("rack.session", "BAh7CkkiD3Nlc3Npb25faWQGOgZFVEkiRTE1YjExODE4OTg3MTRiZDY4NGFj%0ANmU3NTcxZmQ0ZjUzZWUxOTFmY2RjNDIwNDg4NTZhODYxZWY4YjdhODA3MGIG%0AOwBGSSIJY3NyZgY7AEZJIiUyNzE2YmI4MGIyOGI1ZWRkMWIzZjA0ZWZkOGMz%0AZmIzZAY7AEZJIg10cmFja2luZwY7AEZ7B0kiFEhUVFBfVVNFUl9BR0VOVAY7%0AAFRJIi0yZjhlZTQxN2FjODRhNDE3MWM3NTkxOTdlODMzMDA1ZWNlOWEzMmU4%0ABjsARkkiGUhUVFBfQUNDRVBUX0xBTkdVQUdFBjsAVEkiLWM2OWVjOTEzYTg1%0AY2UyMmNjNmM4NjJmYWRlZjdmNWFhMmM2M2JmODkGOwBGSSIKZmxhc2gGOwBG%0AewBJIg11c2VybmFtZQY7AEZJIg10b21zbWl0aAY7AFQ%3D%0A--543744f713dfceacd03d2b044d5a0b8793fe000c"));
//        webDriver.manage().deleteAllCookies();                                                                       //удаление куков
        webDriver.get("http://the-internet.herokuapp.com/secure");                                                     //подставив куки из Application -> cookie проверяем переход на страницу
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Secure Area')]")));       //проверяем совпадает ли текст (Secure Area) на странице перехода
    }

//авторизация не популярным методом basic
    @Test
    void basicAuthTest() {
        webDriver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");                                     //заходим на сайт
        new WebDriverWait(webDriver, 5)                                                                  //смысл в том, что бы не просто указать один url, а еще указать сразу логин и пароль (admin:admin)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Congratulations!')]")));
    }
}
