package utkonostests.testconfigs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    @BeforeClass(alwaysRun = true)
    public void open() {
        Selenide.open("/");
    }

    {
        Configuration.baseUrl = "https://stage.utkonos.ru/?UtkVfmP=ng";
    }
}