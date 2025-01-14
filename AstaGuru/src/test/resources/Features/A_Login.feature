Feature: Login Scenarios
 
 @SkipLogin
Scenario: Successful Login with Valid Mobile Number
    Given Launch chrome browser
    When User enters valid mobile number for valid login
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
    And Users click on Get Started
    Then User navigates to the home page for valid login
    And Closes the browser

@SkipLogin
Scenario: Login with valid Mobile No and Invalid OTP
    Given Launch chrome browser
    When User enters invalid mobile number for invalid login
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
     And Users click on Get Started
    Then User sees error message for invalid otp
    And Closes the browser

@SkipLogin
Scenario: Login with Email ID
    Given Launch chrome browser
    When User clicks on Login with Email button
    And User enters valid email
    And Clicks on Terms and Condition CheckBox
    And Clicks on Get OTP and enter OTP Manually
    And Users click on Get Started
    Then User navigates to the auctions page
    And Closes the browser
  
@SkipLogin   
Scenario: Login Without OTP
    Given Launch chrome browser
    When User enters valid mobile number for valid login
    And Clicks on Terms and Condition CheckBox
     And Clicks on Get OTP and enter OTP Manually
     And Users click on Get Started
     Then Error Message for login without entering OTP
     And Closes the browser
     
     
 @SkipLogin 
Scenario: Login Without MobileNo
      Given Launch chrome browser
      And Left blank Mobile No field
       And Clicks on Terms and Condition CheckBox
       And Clicks on Get OTP and enter OTP Manually
       Then Validate the error message for login witout Mobile No
       Then Closes the browser
      
     
