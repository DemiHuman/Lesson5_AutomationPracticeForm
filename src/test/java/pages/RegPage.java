package pages;

import com.codeborne.selenide.*;
import components.CalendarComponent;
import utils.RandomUtils;

import static com.codeborne.selenide.Selenide.$;

public class RegPage {

    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lasttNameInput = $("#lastName");
    private SelenideElement emailInput = $("#userEmail");
    private SelenideElement randomPhoneInput = $("#userNumber");
    private SelenideElement dateOfBirthInput = $("#dateOfBirthInput");

    public void TypeFirstName(String firstName) {
        firstNameInput.val(firstName);
    }

    public void TypeLastName(String lasttName) {
        lasttNameInput.val(lasttName);
    }

    public void TypeEmail(String email) {
        emailInput.val(email);
    }

    /**
     * Генерирует номер телефона заданного размера одной строкой без символов
     *
     * @param value длина номера телефона
     * @return номер телефона строкой
     */
    public String getPhone(int value) {
        return new RandomUtils().getRandomIntString(value);
    }

    public void setBirthDay(String day, String month, String year) {
        new CalendarComponent().setDate(day, month, year);
    }
}
