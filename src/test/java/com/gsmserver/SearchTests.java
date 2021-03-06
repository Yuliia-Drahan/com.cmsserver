package com.gsmserver;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.by;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {

    @BeforeEach
    void openHomePage() {
        open("https://gsmserver.com/");
    }

    @Test
    void searchProductByTitleAndAddToCart() {

        var productName = "Z3X Easy-Jtag Plus Lite Set";
        var productId = "872993";

        $("[name='searchword']").val(productName).pressEnter();
        $(".search-title-highlight").shouldHave(text(productName));

        findProductById(productId).$(".product-info_title").shouldHave(text(productName));
        findProductById(productId).$("[data-action-click='site.cart.add']").click();
        findProductById(productId).$(".in-cart").click();

        $("#cart h1").shouldHave(text("Cart"));

        $$("#cart tr[data-product-id]").shouldHaveSize(1);
        findProductById(productId).$(".product-title").shouldHave(text(productName));
        sleep(5000);
    }

    @Test
    void searchProductByTitleTest() {
        var productName = "Z3X Easy-Jtag Plus Lite Set";

        new HomePage().searchFor(productName);
        var actualSearchResultTitle = new SearchResultPage().getSearchResultTitle();

        Assertions.assertEquals(productName,actualSearchResultTitle);
    }

    private SelenideElement findProductById(String productId) {
        return $(by("data-product-id", productId));
    }
}
