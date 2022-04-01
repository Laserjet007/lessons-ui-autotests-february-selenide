package ru.gb.lessons.lesson_8.pages;

import com.codeborne.selenide.CollectionCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CartPage extends BasePage {
 //   public CartPage(WebDriver webDriver) {
 //       super(webDriver);
  //  }

    @Step("Проверить, что в корзине продукты: {productNames}")
    public void checkCartContainsProducts(String... productNames) {
        $(By.className("cart-table"))
            .$$("./div//a[@class='cart-table']")
            .shouldHave(CollectionCondition.exactTextsCaseSensitiveInAnyOrder(productNames));

//        List<String> actualProductsInCart = webDriver.findElement(By.className("cart-table"))
//                .findElements(By.xpath("./div"))
//                .stream()
//                .map(product -> product.findElement(By.className("cart-table__name")).getText())
//                .collect(Collectors.toList());
 //       assertThat(actualProductsInCart).containsExactlyInAnyOrder(productNames);
    }
}