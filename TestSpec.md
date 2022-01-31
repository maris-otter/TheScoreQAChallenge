# Test Specification Document

This document outlines all tests that can be run with this automation project. Also view tests.xml for the TestNG Test Suite file (it will map to methods in the src/main/java section)

## Features Tested

 * Validation of app splash pages upon first loading of app: 
 * Test1: Team Search 
   1. Search for Team based on default team name ("Buffalo Bills") or if parameter input ("-DteamName")
   2. Validate that test has landed on correct teams page by checking the top header contains team name text
   3. Click on the 'Team Stats' sub page and validate data is correct (team name exists on top header and data is correct)
   4. Go back to home page and verify page is correct

Open a league, team, or player page of your choice (bonus points for using a data driven
or parameterized approach).
2. Verify that the expected page opens correctly.
3. Tap on a sub-tab of your choice, eg: league table / standings / leaders, or stats tab of the
   league, team, or player.
4. Verify that you are on the correct tab and that the data is displayed correctly and
   corresponds to the league, team, or player from step 1.
5. Verify that back navigation returns you to the previous page correctly.