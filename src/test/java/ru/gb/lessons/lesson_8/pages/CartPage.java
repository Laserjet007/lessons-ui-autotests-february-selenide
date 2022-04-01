package ru.gb.lessons.lesson_8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.assertThat;
public class CartPage extends BasePage {
    public CartPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Проверить, что в корзине продукты: {productNames}")
    public void checkCartContainsProducts(String... productNames) {
        List<String> actualProductsInCart = webDriver.findElement(By.className("cart-table"))
                .findElements(By.xpath("./div"))
                .stream()
                .map(product -> product.findElement(By.className("cart-table__name")).getText())
                .collect(Collectors.toList());
        assertThat(actualProductsInCart).containsExactlyInAnyOrder(productNames);
    }
}