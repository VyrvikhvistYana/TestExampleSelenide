package utkonostests.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import java.io.File;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
public class ProfilePage {

    LoginPage loginPage = new LoginPage();

    private SelenideElement linkToProfile = $x("//*[contains(text(),'Личный кабинет')]");
    private SelenideElement componentOfProfile = $(".profile__pages");
    private SelenideElement titleProfile = $(".profile-update__title .profile-update__title-text");
    private SelenideElement fieldSurname = $(By.id("profileSurnameInput"));
    private SelenideElement notificationChangesSaved = $x("//div[contains(text(), 'Изменения профиля успешно сохранены')]");
    private SelenideElement fieldName = $(By.id("profileNameInput"));
    private SelenideElement firstAddress = $x("(//ul[@role='listbox']//li[1])");
    private SelenideElement autocomplete = $x("//input[@aria-autocomplete='list']");
    private SelenideElement newAddress = $x("(//span[contains(text(), 'Новый адрес')])[2]");
    private SelenideElement showAllAddresses = $x("//span[contains(text(), 'Показать все адреса')]");
    private SelenideElement fieldNumberOfApartment = $("#inputFlatNumber");
    private SelenideElement buttonConfirmAddress = $x("//button[@label='Подтвердить адрес']");
    private ElementsCollection listOfAddresses = $$(".utk-small-card__address");
    private SelenideElement fieldFoFile = $x("//input[@type='file']");
    private SelenideElement maleGender = $x("//div[@aria-label='Мужчина']");
    private SelenideElement femaleGender = $x("//div[@aria-label='Женщина']");
    private SelenideElement textFieldNameIsEmpty = $x("//div[@class='validation-error']//span[contains(text(), 'Поле не заполнено')]");

    @Step("открыть страницу профайла")
    public void openProfile() {
        loginPage.getProfile_dropdown().click();
        linkToProfile.click();
        componentOfProfile.shouldBe(visible);
        titleProfile.shouldHave(text("Профиль"));
    }

    @Step("изменить фамилию")
    public void editFamilyField(String surname) {
        fieldSurname.setValue(surname).sendKeys(Keys.TAB);
        notificationChangesSaved.shouldBe(visible);
    }

    @Step("изменить имя")
    public void editNameField(String name) {
        fieldName.setValue(name).sendKeys(Keys.TAB);
        notificationChangesSaved.shouldBe(visible);

    }

    @Step("очистить поле 'Фамилия'")
    public void clearFieldFamily() {
        fieldSurname.setValue("").sendKeys(Keys.TAB);
        notificationChangesSaved.shouldBe(visible);
    }

    @Step("очистить поле 'Имя'")
    public void clearFieldName() {
        fieldName.setValue(" ").pressEnter();
        Selenide.actions().moveToElement(fieldName, 1000, 1000);
        textFieldNameIsEmpty.shouldBe(visible);
    }


    @Step("изменить пол")
    public void changeGender() {
        String statusWoman = femaleGender.getAttribute("aria-pressed");
        String statusMan = maleGender.getAttribute("aria-pressed");
        if (statusMan.equals("false")) {
            maleGender.click();
            notificationChangesSaved.shouldBe(visible);
        }
        if (statusMan.equals("true")) {
            femaleGender.click();
            notificationChangesSaved.shouldBe(visible);

        }
    }

    @Step("создать адрес доставки")
    public void createAddress(String address, int numberOfApartment) {
        showAllAddresses.click();
        Integer numberOfAdresses = listOfAddresses.size();
        newAddress.click();
        autocomplete.setValue(address);
        firstAddress.click();
        fieldNumberOfApartment.setValue(Integer.toString(numberOfApartment));
        buttonConfirmAddress.click();
        listOfAddresses.shouldHaveSize(numberOfAdresses + 1);
    }

    @Step("загрузить аватар")
    public void uploadAvatar() {
        fieldFoFile.uploadFile(new File("src\\main\\resources\\Ellipse_30.jpg"));


    }

}
