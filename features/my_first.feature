Feature: Login feature

  Scenario: As a user I can sign up on the application
    When I enter "Jorge Rodriguez" into input field number 1
    Then I enter "jrodri" into input field number 2
    Then I enter "18" into input field number 3
    Then I enter "12345_aB" into input field number 4
    Then I hide the keyboard
    Then I press the "SIGN UP" button
    Then I should see text containing "Usuario creado con éxito"



  Scenario: As a user I can sign in on the application
    When I press the text view "or Sign in!"
    Then I enter "jrodri" into input field number 1
    Then I enter "12345_aB" into input field number 2
    Then I hide the keyboard
    Then I press the "SIGN IN" button
    Then I wait for 1 second
    Then I should see text containing "Usuario logeado con exito"