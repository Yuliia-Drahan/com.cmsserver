package com.gsmserver;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {
    public void searchFor(String productName) {
        $("[name='searchword']").val(productName).pressEnter();

    }
}
