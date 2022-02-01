# The Score App Automation Project

This project contains Automated tests for the Score Android App. It is build using Java/Appium/Maven/TestNG

# Prerequisites

* Java (tested with version 8)
* Maven (tested with version 3.8.4)
* Appium (tested with version 1.22.0)
* Local or Virtual Android device connected to Appium (project has been tests with Android 11 and 12 devices)

# Setup Steps

* Ensure appium is running locally and has an android device connected
* Clone the git repository to a local directory
* Setup your Test Data (see Test Data section/Test Spec)
* In command line, go into root of the project and execute either of the following maven commands:
  * mvn clean compile test
  * mvn clean compile test -Dinstallapp (If target device does not have the Score app)
* Maven Surefire-plugin is added to the project so that tests will execute after above commands

This will run the standard suite of tests (outlined in tests.xml and Test spec file TestSpec.md). 

## Additional Command Line Parameters
 * -Dinstallapp : This will install the score apk (/src/main/resources/theScore_v21.16.0.apk) if the testing device does not already have the application

# Test Data

 * This test project utilizes CSV files to populate test data for a test. Multiple rows can be entered and each row will be run as a test run for that particular test
 * Sample file/initial file - src/main/resources/testData/findTeamData.csv
 * Format is dependent on the test, but for FindTeam its "TeamName,ExpectedPlayerName"

# Reports

A test report is generated via maven surefire-reports and can be viewed in /target/surefire-reports/TestSuite1 folder

## Creating New Tests

This Test Project is built to be scalable for  new tests. The steps to create a new test, similar to FindTeam, is as follows:
 * Create a new Java class under src/main/java, extending BaseClass.java (so that you can use the same appium driver)
   * Create your test method, using appropriate TestNG notation
   * Add the test to testNG tests.xml file as needed
 * Create a new test data csv file under /src/resources/testData
 * Create a new TestNG data provider in CSVDataProvider that can read your csv file, and link it to your test via testNG annotations

