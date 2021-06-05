package tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.RegPage;
import utils.RandomUtils;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class TestRegFormWithFakerAndRandomUtilsAndPageObjects {

    Logger logger = LoggerFactory.getLogger(TestRegFormWithFakerAndRandomUtilsAndPageObjects.class);
    RegPage regPage = new RegPage();
    RandomUtils getPhone = new RandomUtils();
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = getPhone.getRandomIntString(10),
            gender = "Other",
            day = "30",
            month = "July",
            year = "1997",
            subject1 = "Maths",
            subject2 = "English",
            currentAddress = faker.address().fullAddress(),
            state = "Haryana",
            city = "Karnal";

    String[] hobby = new String[]{"Sports", "Reading", "Music"};

    @BeforeAll
    static void mainSetup() {
        Configuration.startMaximized = true;
    }

    @Test
    void checkRegistrationForm() {
        open("https://demoqa.com/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(
                text("Student Registration Form"));

        regPage.addFirstName(firstName);
        regPage.addLastName(lastName);
        regPage.addEmail(email);
        regPage.selectGender(gender);
        regPage.addPhone(mobile);
        regPage.setBirthDay(day, month, year);
        regPage.addSubject(subject1);
        regPage.addSubject(subject2);
        regPage.selectHobby(hobby[0]);
        regPage.selectHobby(hobby[1]);
        regPage.selectHobby(hobby[2]);
        regPage.addAddress(currentAddress);
        regPage.selectState(state);
        regPage.selectCity(city);
        $("#submit").click();
        logger.info("The filling was completed successfully!");

        //Checking_After_The_Filling_Reg_Form
        $("#example-modal-sizes-title-lg").shouldHave(
                text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text("Other"),
                text(mobile),
                text(day + " " + month + "," + year),
                text(subject1 + ", " + subject2),
                text(hobby[0] + ", " + hobby[1] + ", " + hobby[2]),
                text(currentAddress),
                text(state + " " + city)
        );

        $("#closeLargeModal").click();
        $(".modal-content").shouldBe(disappear);
        logger.info("The check was completed successfully!");
    }
}