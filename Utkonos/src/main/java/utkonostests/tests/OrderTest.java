package utkonostests.tests;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utkonostests.pages.HistoryOrdersPage;
import utkonostests.pages.LoginPage;
import utkonostests.pages.OrderPage;
import utkonostests.testconfigs.BaseTest;


public class OrderTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HistoryOrdersPage historyOrdersPage = new HistoryOrdersPage();
    OrderPage orderPage = new OrderPage();

    @BeforeMethod(alwaysRun = true)
    public void login() {

        loginPage.open();
        loginPage.login("94816021", "7jes8i3aY!");
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        loginPage.exit();
    }

    @Test
    public void makeOrderToHome() {
        historyOrdersPage.repeatOrder();
        orderPage.goToBasket();
        orderPage.goToCheckout();
        orderPage.chooseAddressForDelivery();
        orderPage.chooseIntervalOfTimeDelivery();
        orderPage.choosePaymentByCash();
        orderPage.makeOrder();
    }

}
