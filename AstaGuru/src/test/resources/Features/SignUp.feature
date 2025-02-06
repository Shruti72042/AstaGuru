Feature: New User Registration

 
Background:
    Given Launch Chrome Browser
    When Click on create an account button
    And Enter Name
    And Enter Email-ID
    And Click on Terms and Condition check box
    
@Smoke, @SkipLogin
Scenario: SignUp using Name, Email ID, and Mobile Number
    #Given Launch Chrome Browser
    #When Click on create an account button
    #And Enter Name
    #And Enter Email-ID
    And Enter Mobile-No
    #And Click on Terms and Condition check box
    And Click on Get OTP button
    And Enter OTP and Clicks on Get Started button
    And Enter OTP received on email and clicks on submit button
    And Observe Successful SignUp Message
    And click on Skip now button
    Then Navigation to Auctions page
    And Close Browser
    
@Sanity, @SkipLogin
Scenario: Check the registration without entering Mobile Number
    #Given Launch Chrome Browser
    #When Click on create an account button
    #And Enter Name
    #And Enter Email-ID
    And Left Blank Mobile No
    And Click on Get OTP button
    Then Observe the error message received for without Mobile No
    And Close Browser
    
@SignInCheck, @SkipLogin
Scenario: Check the Sign link present in the SignUp Page
     #Given Launch Chrome Browser
     #When Click on create an account button
    #And Enter Name
    #And Enter Email-ID
    And Enter Mobile-No
   # And Click on Terms and Condition check box
    And Click on Get OTP button
     And Enter OTP and Clicks on Get Started button
    And Click on Sign In button
    Then observe navigation to Sign In page
    And Close Browser