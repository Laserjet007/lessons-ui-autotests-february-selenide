package ru.gb.lessons.lesson_8.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.NoSuchElementException;
public class ProductPage extends BasePage {
    @FindBy(xpath = "//*[contains(text(),'Перейти в корзину')]")
    private WebElement goToCartButton;
    public ProductPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step("Добавить товар {productName} в корзину")
    public ProductPage selectProduct(String productName) {
        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='product-card ']"));
        WebElement selectedProduct = products.stream()
                .filter(product -> product.findElement(By.xpath(".//a[@class='product-card__name']"))
                        .getText().equals(productName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("productName"));
        selectedProduct.findElement(By.className("product-card__btn")).click();
        return this;
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        goToCartButton.click();
        return new CartPage(webDriver);
    }
}