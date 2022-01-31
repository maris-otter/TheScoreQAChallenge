# Test Specification Document

This document outlines all tests that can be run with this automation project. Also view tests.xml for the TestNG Test Suite file (it will map to methods in the src/main/java section)

## Tests

### Test Name: Find Team

 * Implemented in: FindTeam.java
 * Related Test Data: findTeamData.csv
 * Steps:
    1. Proceeds past the initial app splash pages upon first loading of app: Welcome Page/Choose favorite Leagues/Location Request Popup/Favorite Team Choice/ Never Miss a Game page
    2. Searches for Team based on a row in findTeamData.csv file. First column is the Team Name to search for (ie: 'Buffalo Bills') 
    3. Validates that test has landed on correct teams page by checking the top header contains team name text (just below team icon)
    4. Clicks on the 'Player Stats' sub page and validate data is correct by validating against the second column provided in findTeamData.csv, a player name - ie: J.Allen 
    5. Goes back one page and confirms that we're on the previous search page - by checking for the previous Search Box

