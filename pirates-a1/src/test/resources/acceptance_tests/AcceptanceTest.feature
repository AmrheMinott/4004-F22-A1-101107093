Feature: Acceptance Tests for Assignment 2

  # Part 1
  Scenario: Row 45
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Skull" and 5 "Sword"
    Then player 1 scores 0 after Death

  Scenario: Row 46
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 2 "Skull" and 1 "Sword" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 47
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 2 "Skull", 4 "Parrot" and 2 "Sword"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 1 "Skull" and 1 "Sword" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 48 - 49
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Skull" and 2 "Monkey" after reroll
    When player 1 reroll 2 "Monkey"
    Then player 1 gets 1 "Skull" and 1 "Monkey" after reroll
    And player 1 scores 0 after Death

  Scenario: Row 50 - 51
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Parrot", 3 "Sword" and 2 "Gold"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Gold"
    When player 1 reroll 3 "Sword"
    Then player 1 gets 3 "Gold"
    And player 1 scores 4800

  Scenario: Row 52
    Given player 1 Fortune Card as "Captain"
    When player 1 rolls 2 "Monkey", 2 "Parrot", 2 "Diamond" and 2 "Gold"
    Then player 1 scores 800

  Scenario: Row 53
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Monkey" after reroll
    And player 1 scores 300

  Scenario: Row 54
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Monkey", 3 "Sword" and 2 "Skull"
    Then player 1 scores 300

  Scenario: Row 55
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Diamond", 2 "Skull", 1 "Monkey", 1 "Sword" and 1 "Parrot"
    Then player 1 scores 500

  Scenario: Row 56
    Given player 1 Fortune Card as "Diamond"
    When player 1 rolls 4 "Gold", 2 "Skull" and 2 "Sword"
    Then player 1 scores 700

  Scenario: Row 57
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Sword", 4 "Parrot" and 1 "Skull"
    Then player 1 scores 400

  Scenario: Row 58
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Gold" after reroll
    And player 1 scores 800

  Scenario: Row 59
    Given player 1 Fortune Card as "Captain"
    When player 1 rolls 1 "Skull", 2 "Gold", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Gold" after reroll
    And player 1 scores 1200

  Scenario: Row 61
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Monkey", 2 "Parrot" and 3 "Sword"
    And player 1 reroll 2 "Monkey"
    Then player 1 gets 1 "Skull" and 1 "Sword" after reroll
    When player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Monkey" after reroll
    And player 1 scores 600

  Scenario: Row 62
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 6 "Monkey" and 2 "Skull"
    Then player 1 scores 1100

  Scenario: Row 63
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 7 "Parrot" and 1 "Skull"
    Then player 1 scores 2100

  Scenario: Row 64
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 8 "Gold"
    Then player 1 scores 5400

  Scenario: Row 65
    Given player 1 Fortune Card as "Diamond"
    When player 1 rolls 8 "Gold"
    Then player 1 scores 5400

  Scenario: Row 66
    Given player 1 Fortune Card as "Captain"
    When player 1 rolls 8 "Sword"
    Then player 1 scores 9000

  Scenario: Row 67
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 6 "Monkey" and 2 "Sword"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 2 "Monkey" after reroll
    And player 1 scores 4600

  Scenario: Row 68
    Given player 1 Fortune Card as "Diamond"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Diamond" after reroll
    And player 1 scores 400

  Scenario: Row 69
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 2 "Monkey", 2 "Skull", 2 "Sword", 1 "Diamond" and 1 "Parrot"
    And player 1 reroll 2 "Monkey"
    Then player 1 gets 2 "Diamond" after reroll
    And player 1 scores 500

  Scenario: Row 70
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 1 "Skull", 2 "Gold", 1 "Monkey", 1 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Gold", 1 "Monkey" and 1 "Parrot" after reroll
    And player 1 scores 600

  Scenario: Row 71
    Given player 1 Fortune Card as "Diamond"
    When player 1 rolls 1 "Skull", 2 "Gold", 1 "Monkey", 1 "Parrot" and 3 "Sword"
    And player 1 reroll 3 "Sword"
    Then player 1 gets 1 "Gold", 1 "Monkey" and 1 "Parrot" after reroll
    And player 1 scores 500

  Scenario: Row 72
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 4 "Monkey", 2 "Gold" and 2 "Skull"
    And player 1 scores 600

  # PART 2: Miscellaneous Fortune Cards and Full Chest bonus
  # Sorceress
  Scenario: Row 77
    Given player 1 Fortune Card as "Sorceress"
    When player 1 rolls 2 "Diamond", 1 "Sword", 1 "Monkey", 1 "Gold" and 3 "Parrot"
    And player 1 reroll 3 "Parrot"
    Then player 1 gets 1 "Skull" and 2 "Monkey" after reroll
    And player 1 activates Sorceress getting a "Monkey"
    And player 1 scores 500

  Scenario: Row 78
    Given player 1 Fortune Card as "Sorceress"
    When player 1 rolls 3 "Skull", 3 "Parrot" and 2 "Sword"
    And player 1 activates Sorceress getting a "Parrot"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 2 "Parrot" after reroll
    And player 1 scores 1000

  Scenario: Row 79
    Given player 1 Fortune Card as "Sorceress"
    When player 1 rolls 1 "Skull", 4 "Parrot" and 3 "Monkey"
    And player 1 reroll 3 "Monkey"
    Then player 1 gets 1 "Skull" and 2 "Parrot" after reroll
    And player 1 activates Sorceress getting a "Parrot"
    And player 1 scores 2000

  # Monkey Business
  Scenario: Row 82
    Given player 1 Fortune Card as "Monkey Business"
    When player 1 rolls 3 "Monkey", 3 "Parrot", 1 "Gold" and 1 "Skull"
    And player 1 scores 1100

  Scenario: Row 83
    Given player 1 Fortune Card as "Monkey Business"
    When player 1 rolls 2 "Monkey", 2 "Sword", 2 "Parrot" and 2 "Gold"
    And player 1 reroll 2 "Sword"
    Then player 1 gets 1 "Monkey" and 2 "Parrot" after reroll
    And player 1 scores 1700

  Scenario: Row 84
    Given player 1 Fortune Card as "Monkey Business"
    When player 1 rolls 3 "Monkey", 3 "Skull" and 2 "Parrot"
    And player 1 scores 0

  # Treasure Chest
  Scenario: Row 90
    Given player 1 Fortune Card as "Chest"
    When player 1 rolls 3 "Parrot", 2 "Sword", 2 "Diamond" and 1 "Gold"
    And player 1 puts 2 "Diamond" and 1 "Gold" in chest
    And player 1 reroll 2 "Sword"
    Then player 1 gets 2 "Parrot" after reroll
    When player 1 puts 5 "Parrot" in chest
    And player 1 takes out 2 "Diamond" and 1 "Gold" in chest
    And player 1 reroll 2 "Diamond"
    Then player 1 gets 1 "Parrot" and 1 "Skull" after reroll
    And player 1 reroll 1 "Gold"
    Then player 1 gets 1 "Gold" after reroll
    And player 1 scores 1100

  Scenario: Row 94
    Given player 1 Fortune Card as "Chest"
    When player 1 rolls 2 "Skull", 3 "Parrot" and 3 "Gold"
    And player 1 puts 3 "Gold" in chest
    And player 1 reroll 3 "Parrot"
    Then player 1 gets 2 "Diamond" and 1 "Gold" after reroll
    When player 1 puts 1 "Gold" in chest
    And player 1 reroll 2 "Diamond"
    Then player 1 gets 1 "Gold" and 1 "Skull" after reroll
    And player 1 scores 600

  # Full Chest
  Scenario: Row 97
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Monkey", 3 "Sword", 1 "Diamond" and 1 "Parrot"
    Then player 1 scores 400

  Scenario: Row 98
    Given player 1 Fortune Card as "Captain"
    When player 1 rolls 3 "Monkey", 3 "Sword" and 2 "Gold"
    Then player 1 scores 1800

  Scenario: Row 99
    Given player 1 Fortune Card as "Coin"
    When player 1 rolls 3 "Monkey", 4 "Sword" and 1 "Diamond"
    Then player 1 scores 1000

  Scenario: Row 102
    Given player 1 Fortune Card as "Sea Battle One"
    When player 1 rolls 4 "Monkey", 1 "Sword", 2 "Parrot" and 1 "Gold"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Sword" and 1 "Gold" after reroll
    And player 1 scores 1200

  Scenario: Row 103
    Given player 1 Fortune Card as "Monkey Business"
    When player 1 rolls 2 "Monkey", 1 "Parrot", 2 "Gold" and 3 "Diamond"
    Then player 1 scores 1200

  # Skulls Island and Skull Fortune Cards
  Scenario: Row 106
    Given player 1 Fortune Card as "Skull Two"
    When player 1 rolls 1 "Skull" and 7 "Sword"
    Then player 1 scores 0 after Death

  Scenario: Row 107
    Given player 1 Fortune Card as "Skull One"
    When player 1 rolls 2 "Skull" and 6 "Sword"
    Then player 1 scores 0 after Death

  Scenario: Row 109
    Given player 1 Fortune Card as "Skull Two"
    When player 1 rolls 2 "Skull", 3 "Parrot" and 3 "Monkey"
    And player 1 reroll 3 "Parrot"
    Then player 1 gets 2 "Skull" and 1 "Sword" after reroll
    When player 1 reroll 3 "Monkey"
    Then player 1 gets 3 "Skull" after reroll
    When player 1 reroll 1 "Sword"
    Then player 1 gets 1 "Sword" after reroll
    And player 1 scores 0 after Death
    And player 1 will cause deductions of -900

  Scenario: Row 110
    Given player 1 Fortune Card as "Captain"
    When player 1 rolls 5 "Skull" and 3 "Monkey"
    And player 1 reroll 3 "Monkey"
    Then player 1 gets 2 "Skull" and 1 "Gold" after reroll
    And player 1 scores 0 after Death
    And player 1 will cause deductions of -1400

  Scenario: Row 111
    Given player 1 Fortune Card as "Skull Two"
    When player 1 rolls 3 "Skull" and 5 "Sword"
    And player 1 reroll 5 "Sword"
    Then player 1 gets 5 "Gold" after reroll
    And player 1 scores 0 after Death
    And player 1 will cause deductions of -500

  # Sea Battles
  Scenario: Row 114
    Given player 1 Fortune Card as "Sea Battle One"
    When player 1 rolls 4 "Monkey", 3 "Skull" and 1 "Sword"
    Then player 1 scores an additional -300 losing at sea

  Scenario: Row 115
    Given player 1 Fortune Card as "Sea Battle Two"
    When player 1 rolls 2 "Sword", 2 "Skull" and 4 "Parrot"
    And player 1 reroll 4 "Parrot"
    Then player 1 gets 4 "Skull" after reroll
    And player 1 scores an additional -500 losing at sea

  Scenario: Row 116
    Given player 1 Fortune Card as "Sea Battle Three"
    When player 1 rolls 2 "Monkey", 3 "Skull" and 3 "Sword"
    Then player 1 scores an additional -1000 losing at sea

  Scenario: Row 117
    Given player 1 Fortune Card as "Sea Battle One"
    When player 1 rolls 3 "Monkey", 2 "Sword", 1 "Gold" and 2 "Parrot"
    Then player 1 scores 500 after winning at sea

  Scenario: Row 119
    Given player 1 Fortune Card as "Sea Battle One"
    When player 1 rolls 4 "Monkey", 1 "Sword", 1 "Skull" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 1 "Sword" and 1 "Skull" after reroll
    And player 1 scores 500 after winning at sea

  Scenario: Row 120
    Given player 1 Fortune Card as "Sea Battle Two"
    When player 1 rolls 3 "Monkey", 4 "Sword" and 1 "Skull"
    Then player 1 scores 800 after winning at sea

  Scenario: Row 122
    Given player 1 Fortune Card as "Sea Battle Two"
    When player 1 rolls 4 "Monkey", 2 "Sword" and 2 "Skull"
    And player 1 reroll 4 "Monkey"
    Then player 1 gets 2 "Sword" and 2 "Skull" after reroll
    And player 1 scores an additional -500 losing at sea

  Scenario: Row 123
    Given player 1 Fortune Card as "Sea Battle Three"
    When player 1 rolls 3 "Monkey", 4 "Sword" and 1 "Skull"
    Then player 1 scores 1300 after winning at sea

  Scenario: Row 126
    Given player 1 Fortune Card as "Sea Battle Three"
    When player 1 rolls 3 "Monkey", 1 "Sword", 1 "Skull", 1 "Diamond" and 2 "Parrot"
    And player 1 reroll 2 "Parrot"
    Then player 1 gets 2 "Sword" after reroll
    When player 1 reroll 3 "Monkey"
    Then player 1 gets 1 "Sword" and 2 "Parrot" after reroll
    And player 1 scores 1300 after winning at sea
