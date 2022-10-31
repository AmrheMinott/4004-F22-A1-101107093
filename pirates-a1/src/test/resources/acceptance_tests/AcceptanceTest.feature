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

  Scenario: Row 52
    Given Fortune Card as "Captain"
    When player rolls 2 "Monkey", 2 "Parrot", 2 "Diamond" and 2 "Gold"
    Then player scores 800

  Scenario: Row 53
    Given Fortune Card as "Coin"
    When player rolls 2 "Monkey", 2 "Skull", 2 "Sword" and 2 "Parrot"
    And player reroll 2 "Parrot"
    Then player gets 1 "Sword" and 1 "Monkey" after reroll
    And player scores 300

  Scenario: Row 54
    Given Fortune Card as "Coin"
    When player rolls 3 "Monkey", 3 "Sword" and 2 "Skull"
    Then player scores 300

  Scenario: Row 55
    Given Fortune Card as "Coin"
    When player rolls 3 "Diamond", 2 "Skull", 1 "Monkey", 1 "Sword" and 1 "Parrot"
    Then player scores 500

  Scenario: Row 56
    Given Fortune Card as "Diamond"
    When player rolls 4 "Gold", 2 "Skull" and 2 "Sword"
    Then player scores 700

  Scenario: Row 57
    Given Fortune Card as "Coin"
    When player rolls 3 "Sword", 4 "Parrot" and 1 "Skull"
    Then player scores 400

  Scenario: Row 58
    Given Fortune Card as "Coin"
    When player rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player reroll 2 "Parrot"
    Then player gets 1 "Sword" and 1 "Gold" after reroll
    Then player scores 800

  Scenario: Row 59
    Given Fortune Card as "Captain"
    When player rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player reroll 2 "Parrot"
    Then player gets 1 "Sword" and 1 "Gold" after reroll
    Then player scores 1200

  Scenario: Row 61
    Given Fortune Card as "Coin"
    When player rolls 1 "Skull", 2 "Monkey", 2 "Parrot" and 3 "Sword"
    And player reroll 2 "Monkey"
    Then player gets 1 "Skull" and 1 "Sword" after reroll
    When player reroll 2 "Parrot"
    Then player gets 1 "Sword" and 1 "Monkey" after reroll
    Then player scores 600
