package com.speedup.qa.stepdefinition;

import com.speedup.qa.questions.SelectAdult;
import com.speedup.qa.questions.SelectBaby;
import com.speedup.qa.questions.SelectChild;
import com.speedup.qa.questions.SelectGroup;
import com.speedup.qa.task.*;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.Cast;
import net.serenitybdd.screenplay.actors.OnStage;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;


import static net.serenitybdd.screenplay.actors.OnStage.*;

public class passagerStepDefinition extends PageObject {
    @Managed
    private WebDriver myBrowser;

    @Before
    public void serUpOnstage() {
        setTheStage(Cast.whereEveryoneCan(BrowseTheWeb.with(myBrowser)));
        theActorCalled("Miguel Hugo");
    }

    @Given("^enter the volotea official page$")
    public void enterTheVoloteaOfficialPage() {
        OnStage.theActorInTheSpotlight().wasAbleTo(OpenBrowser.browser());
    }

    @And("^accept cookies and selct the button Passagers$")
    public void acceptCookiesAndSelctTheButtonPassagers() {
        OnStage.theActorInTheSpotlight().attemptsTo(AcceptCookies.acceptCookies());

    }

    @When("^the user clicks the plus button to select more passengers '(\\d+)', '(\\d+)', '(\\d+)'$")
    public void theUserClicksThePlusButtonToSelectMorePassengers(int arg1, int arg2, int arg3) {

        for (int i = 1; i < arg1; i++) {
            OnStage.theActorInTheSpotlight().attemptsTo(Adult.adult());
        }

        for (int i = 0; i < arg2; i++) {
            OnStage.theActorInTheSpotlight().attemptsTo(Child.child());
        }


        for (int i = 0; i < arg3; i++) {
            OnStage.theActorInTheSpotlight().attemptsTo(Baby.baby());
        }
    }

    @Then("^validate that the number of passengers is well selected (.*)$")
    public void validateThatTheNumberOfPassengersIsWellSelected(String reserva) {
        System.out.println("Selected Adults on the website " + SelectAdult.nessageSelectAdult().answeredBy(theActorInTheSpotlight()));
        System.out.println("Selected Children on the website " + SelectChild.messageSelectChild().answeredBy(theActorInTheSpotlight()));
        System.out.println("Selected Babies on the website " + SelectBaby.messageSelectBaby().answeredBy(theActorInTheSpotlight()));
        if (reserva == reserva) {
            OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(SelectGroup.messageSelectGroup(), org.hamcrest.Matchers.is(reserva)));
            System.out.println("It is possible to make a group reservation");
        }
    }
}