@tag
Feature: Multi Player Case One

  # Part 3: Multi-player Scenarios
  Scenario: Row 132
    Given Game Logic is initialised MP
    And player 1 is initialised MP
    And player 2 is initialised MP
    And player 3 is initialised MP
    And player 1 is "Play" MP
    And player 1 draws Fortune Card as "Captain" MP
    When player 1 rolls 7 "Sword" and 1 "Skull" MP
    Then player 1 scores 4000 MP
    And player 1 ends turn MP
    And player 1 is "Waiting" MP
    And player 2 is "Play" MP
    Given player 2 draws Fortune Card as "Skull One" MP
    When player 2 rolls 7 "Sword" and 1 "Skull" MP
    Then player 2 scores 2000 MP
    And player 2 ends turn MP
    And player 2 is "Waiting" MP
    And player 3 is "Play" MP
    Given player 3 draws Fortune Card as "Coin" MP
    When player 3 rolls 3 "Skull" and 4 "Monkey" MP
    Then player 3 scores 0 MP
    And player 3 is dead MP
    And player 3 ends turn MP
    And Game declares "Cumcumber 1" as winner MP
    And player 1 is "Stop" MP
    And player 2 is "Stop" MP
    And player 3 is "Stop" MP

  Scenario: Row 140
    Given Game Logic is initialised MP
    And player 1 is initialised MP
    And player 2 is initialised MP
    And player 3 is initialised MP
    And player 1 is "Play" MP
    And player 1 draws Fortune Card as "Captain" MP
    When player 1 rolls 7 "Sword" and 1 "Skull" MP
    Then player 1 scores 4000 MP
    And player 1 ends turn MP
    And player 1 is "Waiting" MP
    And player 2 is "Play" MP
    Given player 2 draws Fortune Card as "Coin" MP
    When player 2 rolls 3 "Skull" and 5 "Monkey" MP
    Then player 2 scores 0 MP
    And player 2 is dead MP
    And player 2 ends turn MP
    And player 2 is "Waiting" MP
    And player 3 is "Play" MP
    Given player 3 draws Fortune Card as "Captain" MP
    When player 3 rolls 6 "Skull" and 2 "Parrot" MP
    Then player 3 scores 0 MP
    And player 3 is dead MP
    Then player 1 loses score due to player 3 deductions score now 2800
    And player 2 loses score due to player 3 deductions score now 0
    And player 3 ends turn MP
    And player 3 is "Waiting" MP
    Given player 1 is "Play" MP
    When player 1 draws Fortune Card as "Captain" MP
    And player 1 rolls 4 "Monkey" and 4 "Parrot" MP
    Then player 1 scores 1800 MP
    And player 1 ends turn MP
    And player 1 is "Waiting" MP
    Given player 2 is "Play" MP
    When player 2 draws Fortune Card as "Coin" MP
    And player 2 rolls 3 "Skull" and 5 "Monkey" MP
    Then player 2 scores 0 MP
    And player 2 is dead MP
    And player 2 ends turn MP
    And player 2 is "Waiting" MP
    And player 3 is "Play" MP
    Given player 3 draws Fortune Card as "Skull One" MP
    When player 3 rolls 2 "Skull" and 6 "Monkey" MP
    Then player 3 scores 0 MP
    And player 3 is dead MP
    And Game declares "Cumcumber 1" as winner MP
    And player 1 is "Stop" MP
    And player 2 is "Stop" MP
    And player 3 is "Stop" MP

  Scenario: Row 145
    Given Game Logic is initialised MP
    And player 1 is initialised MP
    And player 2 is initialised MP
    And player 3 is initialised MP
    And player 1 is "Play" MP
    And player 1 draws Fortune Card as "Captain" MP
    When player 1 rolls 3 "Skull" and 5 "Monkey" MP
    Then player 1 scores 0 MP
    And player 1 is dead MP
    And player 1 ends turn MP
    And player 1 is "Waiting" MP
    And player 2 is "Play" MP
    Given player 2 draws Fortune Card as "Captain" MP
    When player 2 rolls 7 "Sword" and 1 "Skull" MP
    Then player 2 scores 4000 MP
    And player 2 ends turn MP
    And player 2 is "Waiting" MP
    And player 3 is "Play" MP
    Given player 3 draws Fortune Card as "Skull Two" MP
    When player 3 rolls 1 "Skull" and 7 "Sword" MP
    Then player 3 scores 0 MP
    And player 3 is dead MP
    And player 3 ends turn MP
    And player 3 is "Waiting" MP
    Given player 1 is "Play" MP
    When player 1 draws Fortune Card as "Captain" MP
    And player 1 rolls 8 "Sword" MP
    Then player 1 scores 9000 MP
    And player 1 ends turn MP
    And Game declares "Cumcumber 1" as winner MP
    And player 1 is "Stop" MP
    And player 2 is "Stop" MP
    And player 3 is "Stop" MP

  Scenario: Row 150
    Given Game Logic is initialised MP
    And player 1 is initialised MP
    And player 2 is initialised MP
    And player 1 is "Play" MP
    And player 1 draws Fortune Card as "Coin" MP
    When player 1 rolls 6 "Sword" and 2 "Skull" MP
    Then player 1 scores 1100 MP
    And player 1 ends turn MP
    And player 1 is "Waiting" MP
    And player 2 is "Play" MP
    Given player 2 draws Fortune Card as "Sorceress" MP
    When player 2 rolls 7 "Skull" and 1 "Gold" MP
    Then player 2 scores 0 MP
    And player 2 is dead MP
    And player 2 reroll 1 "Gold" MP
    Then player 2 gets 1 "Skull" after reroll MP
    Then player 1 loses score due to player 2 deductions score now 300
    And player 2 ends turn MP
    And player 2 is "Waiting" MP
    And player 1 is "Stop" MP
    And player 2 is "Stop" MP
