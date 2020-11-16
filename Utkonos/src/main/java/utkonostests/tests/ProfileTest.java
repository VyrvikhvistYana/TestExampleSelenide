package utkonostests.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utkonostests.pages.LoginPage;
import utkonostests.pages.ProfilePage;
import utkonostests.testconfigs.BaseTest;
import java.util.Random;

public class ProfileTest extends BaseTest {


   LoginPage loginPage = new LoginPage();
   ProfilePage profilePage = new ProfilePage();


    @BeforeMethod(alwaysRun = true)
    public void login() {
        loginPage.login("94816021", "7jes8i3aY!");
    }


    @AfterMethod(alwaysRun = true)
    public void exit() {
        loginPage.exit();

    }

    @BeforeMethod(alwaysRun = true)
    public void openProfile() {
        profilePage.openProfile();
    }


    @Test(priority = 1)
    public void editFamilyField() {
        profilePage.editFamilyField("Вырвихвист");
    }

    @Test(priority = 2)
    public void editNameField() {
       // profilePage.openProfile();
        profilePage.editNameField("Яна");
    }

    @Test(priority = 3)
    public void clearFieldFamily() {
        profilePage.clearFieldFamily();
    }

    @Test(priority = 4)
    public void clearFieldName() {
        profilePage.clearFieldName();
    }

    @Test(priority = 5)
    public void changeGender() {
        profilePage.changeGender();
    }

    @Test(priority = 6)
    public void createAddress() {
        Random random = new Random();
        int n = random.nextInt(300) + 1;
        profilePage.createAddress("Печорская, 3", n);
    }

    @Test(priority = 7)
    public void uploadAvatar() {
        //profilePage.openProfile();
        profilePage.uploadAvatar();
    }
}
