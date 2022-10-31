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

  Scenario: Row 47
    Given Fortune Card as "Coin"
    When player rolls 2 "Skull", 4 "Parrot" and 2 "Sword"
    When player reroll 2 "Sword"
    Then player gets 1 "Skull" and 1 "Sword" after reroll
    And player scores 0 after Death

  Scenario: Row 48 - 49
    Given Fortune Card as "Coin"
    When player rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    And player reroll 3 "Sword"
    Then player gets 1 "Skull" and 2 "Monkey" after reroll
    When player reroll 2 "Monkey"
    Then player gets 1 "Skull" and 1 "Monkey" after reroll
    And player scores 0 after Death

  Scenario: Row 50 - 51
    Given Fortune Card as "Coin"
    When player rolls 1 "Skull", 2 "Parrot", 3 "Sword" and 2 "Gold"
    And player reroll 2 "Parrot"
    Then player gets 2 "Gold"
    When player reroll 3 "Sword"
    Then player gets 3 "Gold"
    And player scores 4800
