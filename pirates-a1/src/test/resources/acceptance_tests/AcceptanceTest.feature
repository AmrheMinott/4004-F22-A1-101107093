Feature: Acceptance Tests for Assignment 2

  Scenario: Row 45
    Given Fortune Card as "Coin"
    When player rolls 3 "Skull" and 5 "Sword"
    Then player scores 0 after Death

  Scenario: Row 46
    Given Fortune Card as "Coin"
    When player rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    When player reroll 3 "Sword"
    Then player gets 2 "Skull" and 1 "Sword" after reroll
    And player scores 0 after Death
