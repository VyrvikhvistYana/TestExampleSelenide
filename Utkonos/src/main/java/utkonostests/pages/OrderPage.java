package utkonostests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class OrderPage {

    private SelenideElement linkToBasket = $("[href='/basket']");
    private SelenideElement linkToCheckout = $("[href='/ordering/interval']");
    private SelenideElement titleOfCheckout = $(byText("Оформление заказа"));
    private SelenideElement firstAddress = $x("(//div[contains(@class, 'utk-small-card__address')])[1]");
    private SelenideElement intervalDay = $x("(//div[contains(@class, 'd-flex flex-column')])[6]");
    private SelenideElement selectedInterval = $x("//h4[contains  (@class, 'ordering-delivery-info-title')]");
    private SelenideElement paymentByCash = $x("(//p-card[contains(@class, 'utk-clickable-cards__card')])[4]");
    private SelenideElement buttonMakeOrder = $x("//button[@label='Оформить заказ']");
    private SelenideElement titleThanksForOrder = $(".ordering-thanks__header-info-title");
    private SelenideElement popupWithItems = $x("//a[@tabindex='0'][@role='button']");
    private SelenideElement logoUtkonos = $x("(//a[@routerlink='/'])[1]");


    @Step("перейти в корзину")
    public void goToBasket() {
        linkToBasket.click();
        linkToCheckout.shouldBe(Condition.visible);
    }

    @Step("перейти на страницу оформления заказа")
    public void goToCheckout() {
        linkToCheckout.click();
    }

    @Step("выбрать адрес доставки")
    public void chooseAddressForDelivery() {
        titleOfCheckout.shouldBe(Condition.visible);
        firstAddress.click();
    }

    @Step("выбрать интервал доставки")
    public void chooseIntervalOfTimeDelivery() {
        intervalDay.scrollIntoView(false).click();

        ElementsCollection list = $$(".utk-interval-time");
        final int count = list.size();
        for (int i = 0; i < count; i++) {
            if (list.get(i).getText().equals("08:00 - 10:00")) {
                list.get(i).click();
            } else {
                continue;
            }
        }
        selectedInterval.shouldBe(Condition.visible);
    }

    @Step("выбрать тип оплаты при получении")
    public void choosePaymentByCash() {
        paymentByCash.click();
    }

    @Step("создать заказ")
    public void makeOrder() {
        buttonMakeOrder.click();
        titleThanksForOrder.shouldBe(Condition.exist);
        popupWithItems.click();
        logoUtkonos.click();
    }
}
