
Feature: Login with Valid credentials

Scenario Outline: Successful Login with Valid Mobile Number and Static OTP
    Given Launch chrome browser
    When User enters valid "<Mobile_No>"
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
    And Users click on Get Started
    Then User navigates to the home page with "<Expected_Result>"
    And Closes the browser
    
Examples:
| Mobile_No | Expected_Result |
| 7204278245 | https://development.d2mp9949i73scy.amplifyapp.com/ |
| 9870121070| Wrong verification code |
#| 7204278 | Not a valid mobile number. Please enter a 10-digit number.|

Scenario: Login with invalid Mobile No
Given Launch chrome browser
    When User enters valid "<Mobile_No>"
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
    Then Observe the error message "Not a valid mobile number. Please enter a 10-digit number."
    And Closes the browser

@Sanity
Scenario: Login with EmailID
Given Launch chrome browser
    When Click on Login with Email button
    When Enter Valid EmailID  "shruti.katageri@roxiler.com"
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
    And Users click on Get Started
    Then User navigates to the auctions  page with "<Expected_Result>"
    And Closes the browser
    