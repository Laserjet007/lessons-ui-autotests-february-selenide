package ru.gb.lessons.lesson_6;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.gb.lessons.lesson_6.pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Авторизация")
public class LoginAndLogoutTest extends BaseTest {

    @Test
    @DisplayName("Успешная авторизация и выход")
    @Severity(SeverityLevel.BLOCKER)                                                            //указываем значимость теста (BLOCKER - бесполезно тестирование без этого теста)
    @Description("Успешная авторизация под существующим юзером и выход из системы")             //анотация того, что мы делаем в тесте
    @Epic("")                                                                                   //обычно используется в связке с Jira
    @Feature("Авторизация на сайте")                                                            // дописать фичи (так же обычно с Jira)
    @Issue("")                                                                                  // можно прикрепить какие-либо проблемы связанные с этой задачей (например ссылку на баг)

    void loginAndLogout() {
//        webDriver.get("https://www.shatura.com/");                                            //в сценарии пишем страницу куда нужно перейти
//        MainPage mainPage = new MainPage(webDriver);                                          //уменьшаем код после корректировки и выноса всех методов в MainPage
//        mainPage.login();
//        mainPage.logout();
//        mainPage.checkLoginButtonIsVisible();
        new MainPage(webDriver)
                .login(LOGIN, PASSWORD)                                                         //уменьшаем код после корректировки
                .logout()
                .checkLoginButtonIsVisible();
    }

    @Test
    @DisplayName("Авторизация: Негативный сценарий: неверный пароль")
    @Severity(SeverityLevel.NORMAL)                                                             //указываем значимость теста (средняя значимость)

//    @Disabled("можно указать причину отключения теста")                                       //отключение теста
    void incorrectPasswordTest() {
        webDriver.get("https://www.shatura.com/");                                              //в сценарии пишем страницу куда нужно перейти
        webDriver.manage().window().setSize(new Dimension(1500, 1100));             //настроить размеры окна браузера
        webDriver.findElement(By.xpath                                                          //clear - почистить;.click-кликнуть по элементу;.sendKeys-вписать текст в элемент;.getText -получить текст;.getLocation- получить локацию; .findElement - получить другой элемент из элемента; .getAttribute - получить атрибут;
                        ("//div[@class='signin-link']//span[@class='signin-link__title']"))
                .click();
        WebElement modal__login = webDriver.findElement(By.xpath                                //выносим авторизацию в отдельную переменную, что бы проще было искать элементы
                ("//div[@class='modal__login']"));                                 //WebElement modal__login = webDriver.findElement(By.xpath).var

        modal__login.findElement(By.name("email")).sendKeys("laserjet007@rambler.ru");//ищем внутри созданной переменной, найти элемент по имени. sendKeys -ввод текста
        modal__login.findElement(By.name("password")).sendKeys("incorrect_password"); //то же самое с вводом пароля
        modal__login.findElement(By.className("btn__txt")).click();

        assertThat(new WebDriverWait(webDriver, 5).until(ExpectedConditions       //вариант проверки с ассертом
                        .presenceOfElementLocated(By.xpath("//span[text()='Неверный логин или пароль.']")))
                .getText()).as("Неверный логин или пароль.").isEqualTo("Неверный логин или пароль.");  // проверяем текст на соответствие текст (критично важно для тестирования)
    }
}
