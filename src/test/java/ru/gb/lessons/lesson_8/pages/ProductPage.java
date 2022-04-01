package ru.gb.lessons.lesson_8.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.page;

public class ProductPage extends BasePage {
//    @FindBy(xpath = "//*[contains(text(),'Перейти в корзину')]")
    private final SelenideElement goToCartButton = $x("//*[contains(text(),'Перейти в корзину')]");

//    public ProductPage(WebDriver webDriver) {
//        super(webDriver);
//    }

    @Step("Добавить товар {productName} в корзину")
    public ProductPage selectProduct(String productName) {
        $x("//div[@class='product-card ']//a[@class='product-card__name' and contains(text()) '"+productName+"')]")
                .$(By.className("product-card__btn")).click();

//        List<WebElement> products = webDriver.findElements(By.xpath("//div[@class='product-card ']"));
 //       WebElement selectedProduct = products.stream()
//                .filter(product -> product.findElement(By.xpath(".//a[@class='product-card__name']"))
//                        .getText().equals(productName))
 //               .findFirst()
 //               .orElseThrow(() -> new NoSuchElementException("productName"));
//        selectedProduct.findElement(By.className("product-card__btn")).click();
        return this;
    }

    @Step("Перейти в корзину")
    public CartPage goToCart() {
        goToCartButton.click();
        return page (CartPage.class);
    }
}