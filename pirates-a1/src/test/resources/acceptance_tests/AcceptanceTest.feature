Feature: Acceptance Tests for Assignment 2

  Scenario: Row 45
    Given Fortune Card as "Coin"
    When player rolls 3 "Skull" and 5 "Sword"
    Then player scores 0 after Death
    