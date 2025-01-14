Feature: Auctions Page

  Scenario: Navigate to Auctions Page and list the lots from Testing the Auctions sections
   # Given User logs in with valid credentials
   Then Mouse hover on Auctions tab and click on Auctions Link
    And Click on first View catalogue button
    And Observe the lot list
    Then Navigate to home page
    #And Close the bro
    
 Scenario: Check the lots based on its Status like Bid is closed and You Have own Bid
#Given  User logs in with valid credentials
And Mouse hover on Auctions tab and click on Auctions Link
And Click on View Catalogue button and store all the lots with respect to it's Status
 Then Navigate to home page
 
    @Regression 
Scenario: Check for successful Bid Placing
#Given  User logs in with valid credentials
And Mouse hover on Auctions tab and click on Auctions Link
And Click on first View catalogue button
And Select the lot and place the bid and do validation
  Then Navigate to home page
 
   @Regression 
Scenario: Check the status of the lot
#Given  User logs in with valid credentials
And Mouse hover on Auctions tab and click on Auctions Link
And Click on first View catalogue button
And Check the status of the lot
#Then Close the browser
  Then Navigate to home page

 
  Scenario: To check the bidding on lots with multiple accounts
  #Given  User logs in with valid credentials
  And Mouse hover on Auctions tab and click on Auctions Link
  And Click on first View catalogue button
  #And Check the status of the lot
  And Select the lot and place the bid and do validation
  And Logout 
  #Then Close the browser
  Given Login with other account
  And  Mouse hover on Auctions tab and click on Auctions Link
  And Click on first View catalogue button
  And Select the lot and place the bid and do validation
  #And Check the status of the lot
  #And Validate for the status of the lot
  And  Logout 
  And Clear the cookies
  Given  User logs in with valid credentials
  And Mouse hover on Auctions tab and click on Auctions Link
  And Click on first View catalogue button
  And Check the status of the lot
  And Check the final status of the lot after bidding
  #Then Close the browser
   Then Navigate to home page
 
Scenario: Checking the Proxy Bid Placing  
Given  User logs in with valid credentials
And Mouse hover on Auctions tab and click on Auctions Link
And Click on first View catalogue button
And Select the lot and make proxy bid and do validation
  Then Navigate to home page
  
