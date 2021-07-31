package tests.ui;

import Data.FormData;
import Data.TestData;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import tests.TestBase;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Story("From tests")
public class FormTest extends TestBase {

    @BeforeAll
    static void configureBaseUrl() {
        baseUrl = TestData.getWebUrl();
    }

    @Test
    @Tag("ui")
    @Owner("Stas")
    @DisplayName("Correct fill form")
    void successfulFillPracticeFormTest() {
        String firstName = FormData.getFirstName();
        String lastName = FormData.getLastName();
        String email = FormData.getEmail();
        String gender = FormData.getGender();
        String mobilePhone = FormData.getMobilePhone();
        String monthOfBirth = "January";
        String yearOfBirth = "1990";
        String dayOfBirth = "1";
        String currentAddress = FormData.getCurrentAddress();
        String state = FormData.getState();
        String city = FormData.getCity();
        String[] subjects = new String[]{"Maths", "English", "Computer Science"};
        String[] hobbies = new String[]{"Reading", "Music"};
        String pictureName = "1623341249809.jpeg";

        step("Open form", () -> {
            open(baseUrl);
        });

        step("Set value to fields", () -> {
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $(String.format("input[value='%s'", gender)).parent().$("label").click();
            $("#userNumber").setValue(mobilePhone);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOptionContainingText(monthOfBirth);
            $(".react-datepicker__year-select").selectOptionContainingText(yearOfBirth);
            $(".react-datepicker__month").$(byText(dayOfBirth)).click();
        });

        step("Set subbject, hobbies", () -> {
            setSubjects(subjects);
            setHobbies(hobbies);
        });

        step("Upload image", () -> {
            $("#uploadPicture").uploadFromClasspath(pictureName);
        });

        step("Set value to current address, state, city", () -> {
            $("#currentAddress").setValue(currentAddress);
            $("#react-select-3-input").setValue(state).pressEnter();
            $("#react-select-4-input").setValue(city).pressEnter();
        });

        step("Submit", () -> {
            $("#submit").click();
        });

        step("Assertions for every row in results table", () -> {
            SelenideElement resultsTable = $(".table-responsive");
            resultsTable.$(byText("Student Name")).parent().shouldHave(text(firstName), text(lastName));
            resultsTable.$(byText("Student Email")).parent().shouldHave(text(email));
            resultsTable.$(byText("Gender")).parent().shouldHave(text(gender));
            resultsTable.$(byText("Mobile")).parent().$("td", 1).shouldHave(text(mobilePhone));
            resultsTable.$(byText("Date of Birth")).parent().$("td", 1).shouldHave(text(dayOfBirth), text(monthOfBirth), text(yearOfBirth));

            checkSubjects(resultsTable, subjects);
            checkHobbies(resultsTable, hobbies);

            resultsTable.$(byText("Picture")).parent().$("td", 1).shouldHave(text(pictureName));
            resultsTable.$(byText("Address")).parent().$("td", 1).shouldHave(text(currentAddress));
            resultsTable.$(byText("State and City")).parent().$("td", 1).shouldHave(text(state), text(city));
        });

        //
        step("Assertions in .modal-content", () -> {
            $(".modal-content").shouldHave(
                    text(firstName),
                    text(lastName),
                    text(email),
                    text(gender),
                    text(mobilePhone),
                    text(monthOfBirth),
                    text(yearOfBirth),
                    text(subjects[0]),
                    text(subjects[1]),
                    text(subjects[2]),
                    text(hobbies[0]),
                    text(hobbies[1]),
                    text(pictureName),
                    text(currentAddress),
                    text(state),
                    text(city)
            );
        });


        // Another approach for subjects/hobbies assertions
        for (String subject : subjects) {
            $(".modal-content").shouldHave(text(subject));
        }

        for (String hobby : hobbies) {
            $(".modal-content").shouldHave(text(hobby));
        }
    }

    private void setSubjects(String[] subjects) {
        for (String subject : subjects) {
            $("#subjectsInput").setValue(subject).pressEnter();
        }
    }

    private void setHobbies(String[] hobbies) {
        for (String hobby : hobbies) {
            $("#hobbiesWrapper").$(byText(hobby)).click();
        }
    }

    private void checkSubjects(SelenideElement resultsTable, String[] subjects) {
        for (String subject : subjects) {
            resultsTable.$(byText("Subjects")).parent().$("td", 1).shouldHave(text(subject));
        }
    }

    private void checkHobbies(SelenideElement resultsTable, String[] hobbies) {
        for (String hobby : hobbies) {
            resultsTable.$(byText("Hobbies")).parent().$("td", 1).shouldHave(text(hobby));
        }
    }
}