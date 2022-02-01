import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.Map;

public class FindTeam extends BaseTest {

    private AppiumDriver driver;
    private WebDriverWait wait;

    //Identifiers for Splash pages
    private String getStartedButtonId = "com.fivemobile.thescore:id/btn_primary";
    private String continueButtonId = "com.fivemobile.thescore:id/btn_primary";
    private String backButtonAccessibilityId = "Navigate up";
    private String allowLocationMaybeLaterTextId = "com.fivemobile.thescore:id/btn_disallow";
    private String favoriteTeamFirstElementViewGroupId = "com.fivemobile.thescore:id/follow_icon";

    //Identifies for Main pages
    private String searchBarTextViewId = "com.fivemobile.thescore:id/search_bar_text_view";
    private String searchBarTextId = "com.fivemobile.thescore:id/search_src_text";
    private String teamNameTopBarId = "com.fivemobile.thescore:id/team_name";
    private String playerStatsSubTabAccessibilityId = "Player Stats";


    /* Test Method to find a team from the main score landing page
     * Test Data Required: Hashmap
     *      Value1: Team name (String)
     *      Value2 : Expected result on the player stats Sub tab - Player name (String)
     */
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "findTeamTestData")
    public void findTeam(Map<String,String> dataMap) throws InterruptedException {

        String teamName = dataMap.get("value1");
        String expectedPlayerName = dataMap.get("value2");

        System.out.println("Starting Find Team Test with Team Name: " + teamName + " and expected Player Name: " + expectedPlayerName);

        driver = getDriver(); //Get driver from our BaseTest Class
        //WebdriverWait object to perform explicit waits
        wait = new WebDriverWait(driver, 50);

        //Splash pages section

        //Click Get Started Button
        wait.until(ExpectedConditions.elementToBeClickable(By.id(getStartedButtonId)));
        MobileElement getStartedButton = (MobileElement) driver.findElementById(getStartedButtonId);
        getStartedButton.click();

        //Click Continue Button
        wait.until(ExpectedConditions.elementToBeClickable(By.id(continueButtonId)));
        MobileElement continueButton = (MobileElement) driver.findElementById(continueButtonId);
        continueButton.click();

        //Click Maybe Later text for Allow Location
        wait.until(ExpectedConditions.elementToBeClickable(By.id(allowLocationMaybeLaterTextId)));
        MobileElement maybeLaterText = (MobileElement) driver.findElementById(allowLocationMaybeLaterTextId);
        maybeLaterText.click();

        //Click first team on list - specifically the star icon
        wait.until(ExpectedConditions.elementToBeClickable(By.id(favoriteTeamFirstElementViewGroupId)));
        ArrayList<MobileElement> favoriteTeamsList = new ArrayList<>(driver.findElementsById(favoriteTeamFirstElementViewGroupId));
        favoriteTeamsList.get(0).click();

        //Click Continue button
        wait.until(ExpectedConditions.elementToBeClickable(By.id(continueButtonId)));
        MobileElement continueButton2 = (MobileElement) driver.findElementById(continueButtonId);
        continueButton2.click();

        //Click Continue button
        wait.until(ExpectedConditions.elementToBeClickable(By.id(continueButtonId)));
        MobileElement continueButton3 = (MobileElement) driver.findElementById(continueButtonId);
        continueButton3.click();

        //Main Page Section

        //Click on Search Bar
        wait.until(ExpectedConditions.elementToBeClickable(By.id(searchBarTextViewId)));
        MobileElement searchBar = (MobileElement) driver.findElementById(searchBarTextViewId);
        searchBar.click();

        //Enter Search Text
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(searchBarTextId)));
        MobileElement searchText = (MobileElement) driver.findElementById(searchBarTextId);
        searchText.sendKeys(teamName);

        //Establish this identifier at run time as it will include our test data
        String searchResultsXpath = "//android.widget.TextView[@text='" + teamName + "']";

        //Click on entry in results that matches our search text
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(searchResultsXpath)));
        MobileElement firstItem = (MobileElement) driver.findElementByXPath(searchResultsXpath);
        firstItem.click();

        //Verify Team name on top bar
        wait.until(ExpectedConditions.presenceOfElementLocated(MobileBy.id(teamNameTopBarId)));
        MobileElement teamNameTopBar = (MobileElement) driver.findElementById(teamNameTopBarId);

        Assert.assertEquals(teamNameTopBar.getText(), teamName);

        //Click on 'Player Stats' sub-tab
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(playerStatsSubTabAccessibilityId)));
        MobileElement playerStatsSubPage = (MobileElement) driver.findElementByAccessibilityId(playerStatsSubTabAccessibilityId);
        playerStatsSubPage.click();

        //Establish this identifier at run time as it will include our test data
        String playerStatSubTabActualResultsXpath = "//android.widget.TextView[@text='" + expectedPlayerName + "']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(playerStatSubTabActualResultsXpath)));
        MobileElement playerStatSubTabActualResults = (MobileElement) driver.findElementByXPath(playerStatSubTabActualResultsXpath);

        //Verify our expected result from this page - A player that plays for this team
        Assert.assertEquals(playerStatSubTabActualResults.getText(), expectedPlayerName);

        //Go back to previous page
        wait.until(ExpectedConditions.elementToBeClickable(MobileBy.AccessibilityId(backButtonAccessibilityId)));
        MobileElement backArrow = (MobileElement) driver.findElementByAccessibilityId(backButtonAccessibilityId);
        backArrow.click();

        //Verify we're on the previous page by checking for the same search text bar we used earlier
        wait.until(ExpectedConditions.elementToBeClickable(By.id(searchBarTextId)));
        MobileElement searchText2 = (MobileElement) driver.findElementById(searchBarTextId);
        Assert.assertEquals(true, searchText2.isDisplayed());

        System.out.println("Done Find Team Test");

        //Reset the app for subsequent tests will work
        driver.resetApp();

    }
}