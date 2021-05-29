package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;


public class TestRegForm {

    String firstName = "Тест",
            lastName = "Тестов",
            email = "test@testov.com",
            mobile = "9111111111",
            day = "10",
            month = "July",
            year = "1997",
            subject1 = "Maths",
            subject2 = "English",
            currentAddress = "Планета \"Мир тестов\", г.Тестминд, уд.Тестовая, д. 1",
            state = "Haryana",
            city = "Karnal";

    Logger logger = LoggerFactory.getLogger(TestRegForm.class);

    @BeforeAll
    static void MainSetup() {
        startMaximized = true;
        browser = "chrome";
    }

    @Test
    void CheckRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        //Name_&_Email
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        //Gender
        $("#gender-radio-3").parent().click();
        //Mobile
        $("#userNumber").setValue(mobile);
        //Date_Of_Birth
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(byText(month)).click();
        $(".react-datepicker__year-select").click();
        $(byText(year)).click();
        $(byText(day)).click();
        //Subjects
        $("#subjectsInput").setValue(subject1).pressEnter();
        $("#subjectsInput").setValue(subject2).pressEnter();
        //Hobbies
        $("#hobbies-checkbox-1").parent().click();
        $("#hobbies-checkbox-2").parent().click();
        $("#hobbies-checkbox-3").parent().click();
        //Address
        $("#currentAddress").setValue(currentAddress);
        //State_&_City
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        //Button_"Submit"
        $("#submit").click();
        logger.info("The filling was completed successfully!");


        //Checking_After_The_Filling_Reg_Form
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text("Other"),
                text(mobile),
                text(day + " " + month + "," + year),
                text(subject1 + ", " + subject2),
                text("Sports, Reading, Music"),
                text(currentAddress),
                text(state + " " + city)
        );

        $("#closeLargeModal").click();
        $(".modal-content").shouldBe(disappear);
        logger.info("The check was completed successfully!");
    }
}