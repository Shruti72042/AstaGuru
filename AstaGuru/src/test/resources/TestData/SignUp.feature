Feature: New User Registration 

Scenario Outline: SignUp using Name,EmailID and MobileNo
    Given Launch Chrome Browser
    When User Clicks on create an account button
    And Enters Name with "<Name>"
    And Enters Email-ID with "<Email_ID>"
    And Enters Mobile-No with "<Mobile_No>"
    And Clicks on Terms and Condition check box
    And Clicks on Get OTP button
    And enter otp and Clicks on Get Started button
    And enters OTP received on email and clicks on submit button
    And user receives Successful Message"<Successful_Message>"
    And user clicks on Skip now button
    Then User navigates to Auctions page with "<Expected_Result>"
Examples:
 | Name | Email_ID | Mobile_No | Successful_Message | Expected_Result |
 | Shruti M K | shruti.katageri@roxiler.com | 9870121070 | Congratulations! | https://development.d2mp9949i73scy.amplifyapp.com/ |

 
 Scenario Outline: Check the registration without entering mobileNo
    Given Launch Chrome Browser
    When User Clicks on create an account button
    And Enters Name with "<Name>"
    And Enters Email-ID with "<Email_ID>"
    And Enters Mobile-No with "<Mobile_No>"
    And Clicks on Terms and Condition check box
    And Clicks on Get OTP button
    Then Observe the error message with "<Error_Message>"
    
Examples:
 | Name | Email_ID | Mobile_No | Error_Message |
 |Shruti |shrutikatageri184@gmail.com | | Please enter your mobile number |
 
 
 
 
 