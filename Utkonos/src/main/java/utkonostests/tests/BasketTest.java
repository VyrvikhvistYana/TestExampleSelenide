package utkonostests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utkonostests.pages.BasketPage;
import utkonostests.pages.LoginPage;
import utkonostests.pages.OrderPage;
import utkonostests.testconfigs.BaseTest;

public class BasketTest extends BaseTest {


    BasketPage basketPage = new BasketPage();
    LoginPage loginPage = new LoginPage();
    OrderPage orderPage = new OrderPage();


    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginPage.login("94816021", "7jes8i3aY!");
        orderPage.goToBasket();
    }

    @AfterMethod(alwaysRun = true)
    public void exit() {
        loginPage.exit();
    }

    @Test(priority= 3)
    public void addItemsToCart() {
        basketPage.addItemsToCart();
    }

    @Test(priority =1)
    public void saveCart() {
        basketPage.saveCart();
    }

    @Test(priority = 2)
    public void clearCart() {
        basketPage.clearCart();
    }

}
