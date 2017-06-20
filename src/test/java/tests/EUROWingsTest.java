package tests;

import basetest.BaseTest;
import com.epam.automation.pages.EuroWingsHomePage;
import com.epam.automation.pages.YourSearchPage;
import com.epam.automation.util.CommonUtility;
import com.epam.automation.util.Waits;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * Created by Vikas_Sharma on 6/19/2017.
 */
public class EUROWingsTest extends BaseTest {

    final String departureAirport = "London Heathrow";
    final String departureAirportValue = "LHR";
    final String destinationAirport = "Rome Fiumicino";
    final String destinationAirportValue = "FCO";
    final String outGoingFlightDate = "CDATE_TODAY#2";
    final String returnFlightDate = "CDATE_TODAY#6";

    @Test
    public void EuroWingsTest() {
        EuroWingsHomePage euroWingsHomePage = new EuroWingsHomePage();
        YourSearchPage yourSearchPage = new YourSearchPage();
        assertions.setSoftAssert(new SoftAssert());

        euroWingsHomePage.open();

        assertions.softAssertTrue(euroWingsHomePage.isCurrentPage(),"Euro Wings home page is current page");

        assertions.softAssertTrue(euroWingsHomePage.isDepartureAirportInputFieldVisible(),
                "Departure Airport Field is visible");
        assertions.softAssertTrue(euroWingsHomePage.isDestinationAirportInputFieldVisible(),
                "Destination Airport Field is visible");
        assertions.softAssertTrue(euroWingsHomePage.isOutGoingFlightInputFieldVisible(),
                "Outgoing Flight Field is visible");
        assertions.softAssertTrue(euroWingsHomePage.isReturnFlightInputFieldVisible(),
                "Return Flight Field is visible");
        assertions.softAssertTrue(euroWingsHomePage.isPassengerSelectDivVisible(),
                "Passenger Select Field is visible");
        assertions.softAssertTrue(euroWingsHomePage.isSearchForFlightButton(),
                "Search for Flight Field is visible");

        euroWingsHomePage
                .setDepartureAirport(departureAirport)
                .selectDepartureAirport()
                .setDestinationAirport(destinationAirport)
                .selectDestinationAirport();

        String departureAirport = euroWingsHomePage.getDepartureAirportValue();
        assertions.softAssertEquals(departureAirport,departureAirportValue,"Departure Airport value is ",
                "Departure Airport value is not ");

        String destinationAirport = euroWingsHomePage.getDestinationAirportValue();
        assertions.softAssertEquals(destinationAirport,destinationAirportValue,"Destination Airport value is ",
                "Destination Airport value is not correct");
        euroWingsHomePage
                .setOutgoingFlightDate(outGoingFlightDate)
                .setReturnFlightDate(returnFlightDate);


        euroWingsHomePage.clickSearchForFlight();

        Waits.waitForPageLoadJS();

        String departureAirportYourSearchPage = yourSearchPage.getDepartureAirportValue();
        assertions.softAssertEquals(departureAirportYourSearchPage,departureAirportValue,"Departure Airport value is ",
                "Departure Airport value is not correct");

        String destinationAirportYourSearchPage = yourSearchPage.getDestinationAirportValue();
        assertions.softAssertEquals(destinationAirportYourSearchPage,destinationAirportValue,"Destination Airport value is ",
                "Destination Airport value is not correct");

        String outGoingFlightDate = yourSearchPage.getOutgoingFlightDate();
        assertions.softAssertEquals(CommonUtility.getDate("dd.MM.yy",outGoingFlightDate),outGoingFlightDate,"Outgoing flight date is 2 days from today's date",
                "Outgoing flight date is not 2 days from today's date");

        String returnFlightDate = yourSearchPage.getReturnFlightDate();
        assertions.softAssertEquals(CommonUtility.getDate("dd.MM.yy",returnFlightDate),returnFlightDate,"Return flight date is 6 days from today's date",
                "Return flight date is not 6 days from today's date");

        assertions.softAssertTrue(yourSearchPage.isPassengerSelectVisibleOnYourSearchPage(),
                "Passenger Select Field is visible on Your Search Page");
        assertions.softAssertTrue(yourSearchPage.isChangeSearchButtonVisible(),
                "Change Search Field is visible");

        assertions.assertAll();

    }
}
