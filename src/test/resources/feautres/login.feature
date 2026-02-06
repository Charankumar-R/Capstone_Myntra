Feature: Login operation

  Scenario : Valid Login
    Given user is on the home page of Myntra
    When user hovers to profile and clicks on login
    And user enters mobile number and clicks continue
    And user verify's with otp
    Then user should be successfully logged into Myntra account

  Scenario: Invalid Login
    Given user is on the home page of Myntra
    When user hovers to profile and clicks on login
    And user enters mobile number and clicks continue
    And user enter's incorrect otp
    Then user should be successfully logged into Myntra account