package utkonostests.tests;

import org.testng.annotations.Test;
import utkonostests.pages.LoginPage;
import utkonostests.testconfigs.BaseTest;

public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage();

    @Test(alwaysRun = true)
    public void loginTest() {

        loginPage.open();
        loginPage.login("94816021", "7jes8i3aY!");
    }

    @Test(dependsOnMethods = "loginTest")
    public void exit() {
        loginPage.exit();
    }

}
