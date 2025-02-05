Feature: Auctions Page

Background: 
  And Mouse hover on Auctions tab and click on Auctions Link
  
 
Scenario: Navigate to Auctions Page and list the lots from Testing the Auctions sections
  And Click on Testing The Auction Section's View Catalogue Button
  And Observe the lot list
  Then Navigate to home page

  @Regression
 Scenario: Check the lots based on its Status like Bid is closed and You Have own Bid
  And Click on View Catalogue button and store all the lots with respect to it's status
  Then Navigate to home page
 

Scenario: Check for successful Bid Placing
  And Click on first View catalogue button
  And Select the lot and place the bid and do validation
  Then Navigate to home page
 

Scenario: Check the status of the lot
  And Click on first View catalogue button
  And Check the status of the lot
  Then Navigate to home page


Scenario: To check the bidding on lots with multiple accounts
  And Click on first View catalogue button
  And Select the lot and place the bid and do validation
 And Store the status
  And Logout 
  Given Login with other account
  And  Mouse hover on Auctions tab and click on Auctions Link
  And Click on first View catalogue button
  And Select the lot and place the bid and do validation
  And  Logout 
  And Clear the cookies
  Given  User logs in with valid credentials
  And Mouse hover on Auctions tab and click on Auctions Link
  And Click on first View catalogue button
  And Check the status of the lot
  And Validation for the final status of the lot after multiple bidding
   Then Navigate to home page
 
Scenario: Checking the Proxy Bid Placing  
  And Click on first View catalogue button
  And Select the lot and make proxy bid and do validation
  Then Navigate to home page
  
