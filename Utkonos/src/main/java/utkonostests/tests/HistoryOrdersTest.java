package utkonostests.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utkonostests.pages.HistoryOrdersPage;
import utkonostests.pages.LoginPage;
import utkonostests.testconfigs.BaseTest;

public class HistoryOrdersTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HistoryOrdersPage historyOrdersPage = new HistoryOrdersPage();

    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginPage.login("94816021", "7jes8i3aY!");
        historyOrdersPage.openPageHistoryOrders();
    }


    @AfterMethod(alwaysRun = true)
    public void exit() {
        loginPage.exit();

    }

    @Test(priority = 1)
    public void openPageOrder() {
        historyOrdersPage.openPageOrder();
    }

    @Test(priority = 2)
    public void findOrderCorrectNumber() {
        historyOrdersPage.enterNumberOfOrder();
        historyOrdersPage.checkOrderFound();
    }

    @Test(priority = 3)
    public void findOrderIncorrectNumber() {
        historyOrdersPage.enterIncorrectNumberOfOrder("09090909");
        historyOrdersPage.checkOrderNotFound();
    }

    @Test(priority = 4)
    public void repeatOrder() {
        historyOrdersPage.clearSearchInput();
        historyOrdersPage.repeatOrder();

    }

    @Test(priority = 5)
    public void deleteOrder() {
        historyOrdersPage.deleteOrder();

    }

    @Test(priority = 6)
    public void applyFilterOnStatus() {
        historyOrdersPage.checkOrderFound();
    }

}
