Feature: Login feature

  Scenario: As a user I can sign up on the application
    Given I enter "Jorge Rodriguez" into input field number 1
    And I enter "jrodri" into input field number 2
    And I enter "18" into input field number 3
    And I enter "12345_aB" into input field number 4
    And I hide the keyboard
    And I press the sign up button
    Then I should see text containing "Usuario creado con éxito"


  Scenario: As a user I can sign in on the application
    Given I press the text view "or Sign in!"
    And I enter "jrodri" into input field number 1
    And I enter "12345_aB" into input field number 2
    And I hide the keyboard
    And I press the sign in button
    And I wait for 1 second
    Then I should see text containing "Usuario logeado con exito"
