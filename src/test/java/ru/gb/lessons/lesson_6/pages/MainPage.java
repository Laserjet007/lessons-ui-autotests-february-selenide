package ru.gb.lessons.lesson_6.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
//рефакторинг текущих авто тестов проекта в соответствии с PageObject
    private WebDriver webDriver;                                                                  // вешаем вебдрайвер

    public MainPage(WebDriver webDriver) {                                                        //добавляем конструктор
        this.webDriver = webDriver;
    }

    public MainPage login(String login, String password) {                                                                     //создаем метод - логин (в место void возвращаем MainPage)
         webDriver.findElement(By.xpath                                                           //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                 ("//div[@class='signin-link']//span[@class='signin-link__title']")).click();
         WebElement modal__login = webDriver.findElement(By.xpath                                  //выносим авторизацию в отдельную переменную, что бы проще было искать элементы
                 ("//div[@class='modal__login']"));                                   //WebElement modal__login = webDriver.findElement(By.xpath).var

         modal__login.findElement(By.name("email")).sendKeys(login);//ищем внутри созданной переменной, найти элемент по имени. sendKeys -ввод текста
         modal__login.findElement(By.name("password")).sendKeys(password);         //то же самое с вводом пароля
         modal__login.findElement(By.className("btn__txt")).click();

         webDriver.findElement(By.xpath("//div[@class='signin-link']//span[@class='signin-link__title']")).click();
         return new MainPage(webDriver);                                                           //возвращаемся на страницу
    }

    public MainPage logout() {

         webDriver.findElement(By.xpath("//*[@id=\"personal_menu\"]/a[text()='Выйти']")).click();
         return new MainPage(webDriver);                                                           //возвращаемся на страницу
    }

    public MainPage checkLoginButtonIsVisible() {

         webDriver.findElement(By.xpath("//*[@id=\"login_btn\"]//span[@class='signin-link__title']")).click();
         assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions        //вариант проверки с ассертом
                .presenceOfElementLocated(By.xpath("//div[@class='login__inner']")))
                .getText()).as("Вход\n" + "E-MAIL*\n" + "ПАРОЛЬ*\n" + "Забыли пароль?\n" + "ВОЙТИ")
                .isEqualTo("Вход\n" + "E-MAIL*\n" + "ПАРОЛЬ*\n" + "Забыли пароль?\n" + "ВОЙТИ");                                                                        // проверяем текст на соответствие текст (критично
         return this;                                                                              //остаемся на странице
     }

}


