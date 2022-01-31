# The Score App Automation Project

This project contains Automated tests for the Score Android App. It is build using Java/Appium/Maven

# Prerequisites

* Java (8)
* Maven (3.8.4)
* Appium
* Local or Virtual Android device connected to Appium

# Setup Steps

* Ensure appium is running and has an android device connected
* Clone the git repository to a local directory
* Go into root of the project and execute the following maven command:
  * mvn clean compile test

This will run the standard suite of tests (outlined in tests.xml and Test spec file TestSpec.md). 

# Reports

A test report is generated via maven surefire-reports and can be viewed in /target/surefire-reports/TestSuite1 folder

