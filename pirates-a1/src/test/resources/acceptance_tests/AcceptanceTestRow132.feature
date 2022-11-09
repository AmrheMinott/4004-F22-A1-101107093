#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Multi Player Case One

  # Part 3: Multi-player Scenarios
  @tag1
  Scenario: Row 132
    Given Game Logic is initialised MP1
    And player 1 is initialised MP1
    And player 2 is initialised MP1
    And player 3 is initialised MP1
    Given player 1 draws Fortune Card as "Captain" MP1
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
#
  #@tag2
  #Scenario Outline: Title of your scenario outline
    #Given I want to write a step with <name>
    #When I check for the <value> in step
    #Then I verify the <status> in step
#
    #Examples: 
      #| name  | value | status  |
      #| name1 |     5 | success |
      #| name2 |     7 | Fail    |
