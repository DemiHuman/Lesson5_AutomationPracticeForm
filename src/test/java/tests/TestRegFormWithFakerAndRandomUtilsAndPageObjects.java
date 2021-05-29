package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.RegPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Configuration.*;


public class TestRegFormWithFakerAndRandomUtilsAndPageObjects {

    Logger logger = LoggerFactory.getLogger(TestRegFormWithFakerAndRandomUtilsAndPageObjects.class);
    RegPage regPage = new RegPage();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = regPage.getPhone(10),
            day = "10",
            month = "July",
            year = "1997",
            subject1 = "Maths",
            subject2 = "English",
            currentAddress = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal";

    @BeforeAll
    static void MainSetup() {
        startMaximized = true;
        browser = "chrome";
    }

    @Test
    void CheckRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        //Name_&_Email
        regPage.TypeFirstName(firstName);
        regPage.TypeLastName(lastName);
        regPage.TypeEmail(email);
        //Gender
        $("#gender-radio-3").parent().click();
        //Mobile
        $("#userNumber").setValue(mobile);
        //Date_Of_Birth
        regPage.setBirthDay(day, month, year);
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
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
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