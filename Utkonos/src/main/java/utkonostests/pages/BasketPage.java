package utkonostests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class BasketPage {

    OrderPage orderPage = new OrderPage();
    LoginPage loginPage = new LoginPage();

    private SelenideElement messageBasketIsEmpty = $(".empty__title");
    private SelenideElement buttonSaveBasket = $x("//div[@class='sidebar-manage__buttons']//button[contains(text(), ' Сохранить ')]");
    private SelenideElement buttonAddItem = $x("//button[contains(@class, 'product_add-to-cart-button')]");
    private SelenideElement fieldNameOfTemplate = $x("//input[@placeholder='Название']");
    private SelenideElement buttonSaveTemplate = $x("//button[contains(@class, 'cart-save__accept')]");
    private SelenideElement popupSaveTemplate = $x("//a[@tabindex='0'][@role='button']");
    private SelenideElement notificationAboutSavingOfTemplate = $x("//div[contains(text(), 'Шаблон успешно сохранён')]");
    private SelenideElement buttonClearBasket = $x("//button[contains(text(), ' Очистить ')]");
    private SelenideElement buttonRemoveItemsBasket = $x("//span[contains(text(), 'Удалить')]");
    private SelenideElement logoUtkonos = $x("(//a[@routerlink='/'])[1]");

    final ElementsCollection listOfButtonsAddToCart = $$x("//button[contains(@class, 'product_add-to-cart-button')]");

    public boolean basketIsEmpty() {

        if (messageBasketIsEmpty.isDisplayed()) {
            return true;
        } else {
            return false;
        }

    }

    @Step("добавить товары в корзину")
    public void addItemsToCart() {


        if (basketIsEmpty()) {

            Integer size = listOfButtonsAddToCart.size();
            for (int i = 0; i < size; i++) {
                for (SelenideElement button : listOfButtonsAddToCart) {
                    if (button.isDisplayed()) {
                        button.click();
                    }
                }
            }
            $x("//div[@slot='cart-action-button']").should(Condition.visible);


        } else {
            clearCart();
            orderPage.goToBasket();
            Integer size = listOfButtonsAddToCart.size();
            for (int i = 0; i < size; i++) {
                for (SelenideElement button : listOfButtonsAddToCart) {
                    if (button.isDisplayed()) {
                        button.click();
                    }
                }
            }
            $x("//div[@slot='cart-action-button']").should(Condition.visible);

        }
        //back();
        logoUtkonos.click();

    }

    @Step("сохранить корзину как шаблон")
    public void saveCart() {
        if (basketIsEmpty()) {
            buttonAddItem.click();
            buttonSaveBasket.should(Condition.exist).click();

            Random random = new Random();
            int n = random.nextInt(300) + 1;

            fieldNameOfTemplate.setValue("Шаблон " + n);
            buttonSaveTemplate.shouldBe(Condition.visible).click();
            popupSaveTemplate.should(Condition.disappear);
            notificationAboutSavingOfTemplate.shouldBe(Condition.visible);
            notificationAboutSavingOfTemplate.should(Condition.disappear);
            logoUtkonos.click();

        } else {

            buttonSaveBasket.shouldBe(Condition.visible).click();
            Random random = new Random();
            int n = random.nextInt(300) + 1;
            fieldNameOfTemplate.shouldBe(Condition.visible).setValue("Шаблон " + n);
            buttonSaveTemplate.shouldBe(Condition.visible).click();
            popupSaveTemplate.should(Condition.disappear);
            notificationAboutSavingOfTemplate.shouldBe(Condition.visible);
            notificationAboutSavingOfTemplate.should(Condition.disappear);
            logoUtkonos.click();
        }

    }


    @Step("очистить корзину")
    public void clearCart() {

        if (basketIsEmpty()) {
            buttonAddItem.click();
            buttonClearBasket.click();
            buttonRemoveItemsBasket.click();
            messageBasketIsEmpty.shouldBe(Condition.visible);
            logoUtkonos.click();
        } else {
            buttonClearBasket.click();
            buttonRemoveItemsBasket.click();
            messageBasketIsEmpty.shouldBe(Condition.visible);
            logoUtkonos.click();
        }


    }

}


