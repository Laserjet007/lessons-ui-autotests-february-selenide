package ru.gb.lessons.lesson_8;

import io.qameta.allure.Allure;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.gb.lessons.lesson_8.pages.MainPage;

@DisplayName("Корзина")
public class AddProductTest extends BaseTest {

    @ParameterizedTest
    @ValueSource(strings = {"Электроакустический бас BATON ROUGE X11S/BSCE"})
    @ParameterizedTest(name = "Добавление товара {0} в корзину")
    @DisplayName("Добавление товара в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @ValueSource(strings = {"Электроакустический бас BATON ROUGE X11S/BSCE", "Бас-гитара CORT AB850F BK W_BAG"})
    void addProductTest(String productName) {
        Allure.parameter("Название продукта", productName);
        new MainPage(webDriver)
                .getHeader()
                .goToProductPage("Гитары", "Акустические бас-гитары")
                .selectProduct("Электроакустический бас BATON ROUGE X11S/BSCE")
                .selectProduct(productName)
                .goToCart()
                .checkCartContainsProducts(productName);
    }
}