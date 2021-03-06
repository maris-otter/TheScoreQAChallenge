import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/* Base Class to be inherited/extended by all new Test Classes
 *
 */
public class BaseTest {

    private AppiumDriver driver;

    private final static String APP_PACKAGE_NAME = "com.fivemobile.thescore";
    private final static String APP_ACTIVITY_NAME = "com.fivemobile.thescore.ui.MainActivity";

    /* Find the APK File, set appium capabilities, and connect to appium and start driver
     * Parameters: Boolean installApp (optional) - if true, install the app in src/java/resources, otherwise use app_package_name and app_activity_name
     */
    @BeforeSuite
    @Parameters("installApp")
    public void init(@Optional("false") boolean installApp) throws MalformedURLException {
        System.out.println("Opening Appium");

        //Get the APK file
        ClassLoader classLoader = getClass().getClassLoader();
        File apkFile = new File(classLoader.getResource("theScore_v21.16.0.apk").getFile());

        // Setup Appium with specific configuration
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("automationName","UiAutomator2");
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "ANDROID");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "25");
        //Conditional logic to install app or just open it
        if (installApp){
            cap.setCapability(MobileCapabilityType.APP,apkFile.getAbsolutePath());
        }else{
            cap.setCapability("appPackage",APP_PACKAGE_NAME);
            cap.setCapability("appActivity",APP_ACTIVITY_NAME);
        }

        //Connect to Appium and start the driver
        driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        System.out.println("Finished Setup of Appium");
    }

    //Close appium
    @AfterSuite
    public void close(){
        //Close Appium
        System.out.println("Closing Appium");
        driver.quit();
    }


    //Methods to allow our test classes to access webdriver object
    public AppiumDriver getDriver(){
        return driver;
    }

}
