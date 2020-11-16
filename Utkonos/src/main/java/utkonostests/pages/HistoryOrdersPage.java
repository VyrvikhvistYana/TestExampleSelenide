package utkonostests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class HistoryOrdersPage {

    LoginPage loginPage = new LoginPage();

    private SelenideElement myOrders = $x("//*[contains(text(),'Мои заказы')]");
    private SelenideElement titleOfMyOrdersPage = $(".profile-orders__title");
    private SelenideElement linkToTheOrderPage = $x("(//a[contains(@href, '/my-account/order/')])[1]");
    private SelenideElement numberOrder = $x("//div[@class='profile-order-detail-main__orderNubmer']");
    private SelenideElement placeHolderSearchOfOrder = $("[placeholder='Поиск по номеру заказа']");
    private SelenideElement titleOrdersNotFound = $(".orders-noResult h1");
    private SelenideElement buttonRepeatOrder = $x("(//button[@label='Повторить'])[1]");
    private SelenideElement buttonAddOrder = $x("//p-dialog//div//button//span[contains(text(), 'Добавить')]");
    private SelenideElement disclamer18 = $x("//div[@class='age-disclaimer']");
    private SelenideElement buttonYesDisclamer18 = $x("//div[@class='age-disclaimer']//button[contains(text(), 'Да')]");
    private SelenideElement notificationItemsAdded = $x("//div[contains(text(), 'Товары из заказа добавлены в корзину')]");
    private SelenideElement buttonSearchOrders = $x("//div[@class='profile-orders__input-wrap']//button[@type='submit']");
    private SelenideElement buttonDeleteOrder = $x("(//button[@label='Удалить'])[1]");
    private SelenideElement buttonDeleteOrderDialog = $x("//p-dialog//div//button//span[contains(text(), 'Удалить')]");
    private SelenideElement filterStatus = $x("//p-multiselect[@formcontrolname='Status']//div[contains(@class, 'ui-multiselect-trigger')]");
    private SelenideElement statusDelivered = $x("//ul[@role='listbox']//p-multiselectitem//li[@aria-label='Доставлен']");
    private ElementsCollection ordersStatus = $$x("//div[contains(@class, 'profile-orders__order-status')]");



    @Step("открыть страницу заказов")
    public void openPageHistoryOrders() {
        loginPage.getProfile_dropdown().click();
        myOrders.click();
        titleOfMyOrdersPage.shouldHave(text(" История заказов "));

    }
    @Step("перейти на страницу заказа")
    public void openPageOrder() {
        linkToTheOrderPage.click();
        numberOrder.shouldBe(visible);
    }

    @Step("ввести номер заказа в поисковую строку")
    public void enterNumberOfOrder() {
        placeHolderSearchOfOrder.clear();
        placeHolderSearchOfOrder.shouldBe(empty);
        openPageHistoryOrders();
        String numberOfOrder = $x("(//a//div[contains (@class, 'profile-orders__order-number')])[1]").text();
        placeHolderSearchOfOrder.shouldBe(enabled).setValue(numberOfOrder).pressEnter();

    }

    @Step("проверить, что заказ найден")
    public void checkOrderFound() {
        $$x("//utk-profile-orders-search").shouldHave(size(1));

    }

    @Step("проверить, что заказ не найден")
    public void checkOrderNotFound() {
        titleOrdersNotFound.shouldHave(text(" ничего не найдено. "));
        placeHolderSearchOfOrder.clear();
        titleOrdersNotFound.should(disappear);
    }

    @Step("ввести несуществующий номер заказа")
    public void enterIncorrectNumberOfOrder(String s) {
        placeHolderSearchOfOrder.shouldBe(enabled).setValue(s).pressEnter();

    }

    @Step("повторить заказ")
    public void repeatOrder() {
        openPageHistoryOrders();
        buttonRepeatOrder.click();
        buttonAddOrder.click();

        if (disclamer18.isDisplayed()) {
            buttonYesDisclamer18.click();
            notificationItemsAdded.waitUntil(visible, 10000);
        } else {
            notificationItemsAdded.waitUntil(visible, 10000);
        }
    }

    @Step("очистить строку поиска заказа")
    public void clearSearchInput() {
        placeHolderSearchOfOrder.sendKeys(Keys.CONTROL + "A");
        placeHolderSearchOfOrder.sendKeys(Keys.BACK_SPACE);
        buttonSearchOrders.click();
        refresh();

    }

    @Step("удалить заказ из списка заказов")
    public void deleteOrder() {
        String numberOfOrder = $x("(//a//div[contains (@class, 'profile-orders__order-number')])[1]").text();

        SelenideElement numberOfDeletedOrder = $x("//div[contains(@class, 'profile-orders__order-number') and contains(text(), '" + numberOfOrder + "')]");
        System.out.println(numberOfOrder);
        buttonDeleteOrder.click();
        buttonDeleteOrderDialog.click();
        refresh();
        numberOfDeletedOrder.shouldNot(exist);
    }

    @Step("отсортировать заказы по статусу")
    public void chooseFilterStatus() {
        filterStatus.click();
        statusDelivered.click();
        ordersStatus.shouldHave(texts(" Доставлен "));
    }
}

