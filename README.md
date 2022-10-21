# COMP 4004 F22_A1_101107093

## Student Name

### Amrhe Minott

## Student Number

### 101107093

Project Structure

## constants

Contains all the constant values that are used throughout the code base.

### DieSides - the simulated dice is a dice of 6 sides. Gold, Diamond, SKull, Sword, Parrot and monkey.This class contains the values for them

### GameStatus

The server communicates to the client(s) by various commands this folder contains such commands

### PlayerCommand

In game options render to the player in the form of an int input. This file contains the various potential commands the system has for the player

## fortune_cards

This folder contains classes from the various cards that can appear in the deck that the user will have the option to play with. Options include

- Captain - double final points for player.
- Sorceress - gives the ability to re roll a skull.
- 3 types of SeaBattleCard -when at sea and win gains the additional points but if loses at sea then they loose the additional points.
- 2 types of SkullCard -adds the specified number of skulls to card.
- Chest - give the ability to add to a safe secured chest area.
- MonkeyBusiness - combines Parrots and Monkeys faces as the same.

## game_server

Handles the server for running the game and communicating the results to the players stats on the game

## pirate_logic

Handles the bulk of score calculations and score deductions

## player

handles the client side of the application where users can send back response for the server to process essential data
