Feature: Acceptance Tests for Assignment 2

  Scenario: Row 45
    Given Fortune Card as "Coin"
    When player 1 rolls 3 "Skull" and 5 "Sword"
    Then player 1 scores 0 after Death

  Scenario: Row 46
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    When player 1 reroll 3 "Sword"
    Then player 1 gets 2 "Skull" and 1 "Sword" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 47
    Given Fortune Card as "Coin"
    When player 1 rolls 2 "Skull", 4 "Parrot" and 2 "Sword"
    When player 1 reroll 2 "Sword"
    Then player 1 gets 1 "Skull" and 1 "Sword" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 48 - 49
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Skull" and 2 "Monkey" after reroll
    When player 1 reroll 2 "Monkey"
    Then player 1 gets 1 "Skull" and 1 "Monkey" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 50 - 51
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Parrot", 3 "Sword" and 2 "Gold"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Gold"
    When player 1 reroll 3 "Sword"
    Then player 1 gets 3 "Gold"
    And player 1 scores 4800

  Scenario: Row 52
    Given Fortune Card as "Captain"
    When player 1 rolls 2 "Monkey", 2 "Parrot", 2 "Diamond" and 2 "Gold"
    Then player 1 scores 800

  Scenario: Row 53
    Given Fortune Card as "Coin"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Monkey" after reroll
    And player 1 scores 300

  Scenario: Row 54
    Given Fortune Card as "Coin"
    When player 1 rolls 3 "Monkey", 3 "Sword" and 2 "Skull"
    Then player 1 scores 300

  Scenario: Row 55
    Given Fortune Card as "Coin"
    When player 1 rolls 3 "Diamond", 2 "Skull", 1 "Monkey", 1 "Sword" and 1 "Parrot"
    Then player 1 scores 500

  Scenario: Row 56
    Given Fortune Card as "Diamond"
    When player 1 rolls 4 "Gold", 2 "Skull" and 2 "Sword"
    Then player 1 scores 700

  Scenario: Row 57
    Given Fortune Card as "Coin"
    When player 1 rolls 3 "Sword", 4 "Parrot" and 1 "Skull"
    Then player 1 scores 400

  Scenario: Row 58
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Gold" after reroll
    And player 1 scores 800

  Scenario: Row 59
    Given Fortune Card as "Captain"
    When player 1 rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Gold" after reroll
    And player 1 scores 1200

  Scenario: Row 61
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Monkey", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Monkey"
    Then player 1 gets 1 "Skull" and 1 "Sword" after reroll
    When player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Monkey" after reroll
    And player 1 scores 600

  Scenario: Row 62
    Given Fortune Card as "Coin"
    When player 1 rolls 6 "Monkey" and 2 "Skull"
    Then player 1 scores 1100

  Scenario: Row 63
    Given Fortune Card as "Coin"
    When player 1 rolls 7 "Parrot" and 1 "Skull"
    Then player 1 scores 2100

  Scenario: Row 64
    Given Fortune Card as "Coin"
    When player 1 rolls 8 "Gold"
    Then player 1 scores 5400

  Scenario: Row 65
    Given Fortune Card as "Diamond"
    When player 1 rolls 8 "Gold"
    Then player 1 scores 5400

  Scenario: Row 66
    Given Fortune Card as "Captain"
    When player 1 rolls 8 "Sword"
    Then player 1 scores 9000

  Scenario: Row 67
    Given Fortune Card as "Coin"
    When player 1 rolls 6 "Monkey" and 2 "Sword"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 2 "Monkey" after reroll
    And player 1 scores 4600

  Scenario: Row 68
    Given Fortune Card as "Diamond"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Diamond" after reroll
    And player 1 scores 400

  Scenario: Row 69
    Given Fortune Card as "Coin"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword", 1 "Diamond" and 1 "Parrot"
    And player 1 reroll 2 "Monkey"
    Then player 1 gets 2 "Diamond" after reroll
    And player 1 scores 500

  Scenario: Row 70
    Given Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Gold", 1 "Monkey", 1 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Gold", 1 "Monkey" and 1 "Parrot" after reroll
    And player 1 scores 600

  Scenario: Row 71
    Given Fortune Card as "Diamond"
    When player 1 rolls 1 "Skull", 2 "Gold", 1 "Monkey", 1 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Gold", 1 "Monkey" and 1 "Parrot" after reroll
    And player 1 scores 500

  Scenario: Row 72
    Given Fortune Card as "Coin"
    When player 1 rolls 4 "Monkey", 2 "Gold" and 2 "Skull"
    And player 1 scores 600

  Scenario: Row 77
    Given Fortune Card as "Sorceress"
    When player 1 rolls 2 "Diamond", 1 "Sword", 1 "Monkey", 1 "Gold" and 3 "Parrot"
    And player 1 reroll 3 "Parrot"
    Then player 1 gets 1 "Skull" and 2 "Monkey" after reroll
    And player 1 activates Sorceress getting a "Monkey"
    And player 1 scores 500

  Scenario: Row 78
    Given Fortune Card as "Sorceress"
    When player 1 rolls 3 "Skull", 3 "Parrot" and 2 "Sword"
    And player 1 activates Sorceress getting a "Parrot"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 2 "Parrot" after reroll
    And player 1 scores 1000

  Scenario: Row 79
    Given Fortune Card as "Sorceress"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Monkey"
    And player 1 reroll 3 "Monkey"
    Then player 1 gets 1 "Skull" and 2 "Parrot" after reroll
    And player 1 activates Sorceress getting a "Parrot"
    And player 1 scores 2000
