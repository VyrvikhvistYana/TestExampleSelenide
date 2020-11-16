package utkonostests.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.StaleElementReferenceException;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private SelenideElement loginProfile = $x("//button[@class='header_base-button']");
    public SelenideElement getLoginProfile() {
        return loginProfile;
    }
    private SelenideElement profile_dropdown = $x("//div[@role='button'][@aria-haspopup='listbox']");
    public SelenideElement getProfile_dropdown() {
        return profile_dropdown;
    }
    private SelenideElement button_to_pass_by_password = $x("//a[contains(text(), 'паролю')]");
    private SelenideElement field_login = $x("//*[@placeholder='Телефон, email или логин']");
    private SelenideElement field_password = $x("//*[@placeholder='Пароль']");
    private SelenideElement button_enter = $x("//*[@value='Войти']");
    private SelenideElement popupWindow = $x("//a[contains(@class, 'ui-dialog-titlebar-close')]");
    private SelenideElement exitButton = $x("//div[contains(text(),'Выход')]");
    private SelenideElement buttonClosePopupAuthorization = $x("//a[@tabindex='0'][@role='button']");

    @Step("открыть сайт")
    public void open() {
        Selenide.open("/");
    }
    public void closePopupWindow() {
        popupWindow.click();
    }
    public void closePopupAuthorization() {
        buttonClosePopupAuthorization.click();
    }
    public void fillInputsAndClickEnter(String login, String password) {
        button_to_pass_by_password.click();
        field_login.setValue(login);
        field_password.setValue(password);
        button_enter.click();
    }
    public boolean retryingFindClick(SelenideElement loginProfile) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                loginProfile.click();
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
            }
            attempts++;
        }
        return result;
    }

    @Step("авторизация")
    public void login(String login, String password) {
        if (popupWindow.isDisplayed()) {
            closePopupWindow();
        }
        $x("//button[@class='header_base-button']").click();
        fillInputsAndClickEnter(login, password);
        $x("//button[@class='header_base-button']").should(Condition.disappear);
    }

    public void exit() {
        profile_dropdown.shouldBe(Condition.visible).click();
        exitButton.click();
    }
}
