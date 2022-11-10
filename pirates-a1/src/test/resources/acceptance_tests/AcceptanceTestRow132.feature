@tag
Feature: Multi Player Case One

  # Part 3: Multi-player Scenarios
  @tag1
  Scenario: Row 132
    Given Game Logic is initialised MP1
    And player 1 is initialised MP1
    And player 2 is initialised MP1
    And player 3 is initialised MP1
    And player 1 is "Play" MP1
    And player 1 draws Fortune Card as "Captain" MP1
    When player 1 rolls 7 "Sword" and 1 "Skull" MP1
    Then player 1 scores 4000 MP1
    And player 1 ends turn MP1
    And player 1 is "Waiting" MP1
    And player 2 is "Play" MP1
    Given player 2 draws Fortune Card as "Skull One" MP1
    When player 2 rolls 7 "Sword" and 1 "Skull" MP1
    Then player 2 scores 2000 MP1
    And player 2 ends turn MP1
    And player 2 is "Waiting" MP1
    And player 3 is "Play" MP1
    Given player 3 draws Fortune Card as "Coin" MP1
    When player 3 rolls 3 "Skull" and 4 "Monkey" MP1
    Then player 3 scores 0 MP1
    And player 3 is dead MP1
    And player 3 ends turn MP1
    And Game declares "Cumcumber 1" as winner MP1
    And player 1 is "Play" MP1
    And player 2 is "Play" MP1
    And player 3 is "Play" MP1

  @tag2
  Scenario: Row 140
    Given Game Logic is initialised MP1
    And player 1 is initialised MP1
    And player 2 is initialised MP1
    And player 3 is initialised MP1
    And player 1 is "Play" MP1
    And player 1 draws Fortune Card as "Captain" MP1
    When player 1 rolls 7 "Sword" and 1 "Skull" MP1
    Then player 1 scores 4000 MP1
    And player 1 ends turn MP1
    And player 1 is "Waiting" MP1
    And player 2 is "Play" MP1
    Given player 2 draws Fortune Card as "Coin" MP1
    When player 2 rolls 3 "Skull" and 5 "Monkey" MP1
    Then player 2 scores 0 MP1
    And player 2 is dead MP1
    And player 2 ends turn MP1
    And player 2 is "Waiting" MP1
    And player 3 is "Play" MP1
    Given player 3 draws Fortune Card as "Captain" MP1
    When player 3 rolls 6 "Skull" and 2 "Parrot" MP1
    Then player 3 scores 0 MP1
    And player 3 is dead MP1
    Then player 1 loses score due to player 3 deductions
    And player 2 loses score due to player 3 deductions
    And player 3 ends turn MP1
    Given player 1 is "Play" MP1
    When player 1 draws Fortune Card as "Captain" MP1
    And player 1 rolls 4 "Monkey" and 4 "Parrot" MP1
    Then player 1 scores 1800 MP1
    And player 1 ends turn MP1
    And player 1 is "Waiting" MP1
    Given player 2 is "Play" MP1
    When player 2 draws Fortune Card as "Coin" MP1
    And player 2 rolls 3 "Skull" and 5 "Monkey" MP1
    Then player 2 scores 0 MP1
    And player 2 is dead MP1
    And player 2 ends turn MP1
    And player 2 is "Waiting" MP1
    And player 3 is "Play" MP1
    Given player 3 draws Fortune Card as "Skull One" MP1
    When player 3 rolls 2 "Skull" and 6 "Monkey" MP1
    Then player 3 scores 0 MP1
    And player 3 is dead MP1
    And Game declares "Cumcumber 1" as winner MP1
    And player 1 is "Play" MP1
    And player 2 is "Play" MP1
    And player 3 is "Play" MP1
