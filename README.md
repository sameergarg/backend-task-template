# Backend Technical Task

## Overview
The goal of this task is not to produce the best implementation of the game, but for us to see your coding style
and structure. How you form your code to solve the task, how easy it is to follow and reason from it.

Please focus your time on making sure that the code is well structured and logically split, and that the flow is
easy to understand and follow. To put it short, that it is in a form that you yourself would like to inherit a
codebase.

## The Assignment

We would like you to write a command line app in Scala or Java. You are free to use just standard libraries, or
any combination of others that will make the task easier for you, or cleaner. The problem is deliberately not
too complex to focus on the things mentioned above.

The task is to simulate a game of snap between two computer players using standard playing card decks.
These requirements should drive your solution in a way that will show us multiple facets of your coding style:

* The application should ask the user how many playing card decks to play with (also possibly how
many players, but can also default to just 2)
* The application should ask the user whether cards should be matched: on suit, value, or both.
* The application should shuffle the decks before play commences.
* Games of snap should then be simulated between players. Cards are played one at a time in order by
each player in the game, onto their stack. When a player deals a card that matches a card at the top
of another player's stack, the first player to 'shout' snap 'takes' the two piles.
* The stop condition can be either after the first round when all cards were dealt, with the winner being
the player who scored more cards, or after multiple rounds once one player holds all the cards.
Whichever is easier / makes more sense to you.
* You may choose single or multithreaded approach whichever suits you better, but do include some
form of randomness.

## Guidelines

* We recommend you spend no more than 2 hours on the task.
* Feel free to get it back to us however you like (GitHub, email, ...)

Don’t hesitate to get in touch if anything is unclear, or if you have any questions.

## Your thoughts

We find it useful if you can document some of your thinking, including what you would improve given more time. known issues and how you would resolve them given more time. Technical decisions, design decisions, compromises.

Please also add some comments to this file with instructions on how to run your project, and to document what platform and SDK versions you have tested it with.

## This Template project

This project is provided as a skeleton with some basic setup, to save you time, but you are free to add, modify or replace anything, as you prefer.

It currently runs on Scala 2.13.8, and uses [ScalaTest](https://www.scalatest.org/), which supports multiple testing styles - feel free to use any style you prefer.

### Running the project

Run `sbt run` to run the Main class.

You can also run `sbt ~run` to rerun on every file save.

Running `sbt test` will run all tests.
