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

The task is to simulate a game of snap between two or more players using standard playing card decks.
These requirements should drive your solution in a way that will show us multiple facets of your coding style:

* The application should ask the user how many playing card decks to play with (also possibly how
many players, but can also default to just 2)
* The application should ask the user whether cards should be matched: on suit, value, or both.
* The application should shuffle the decks before play commences
* The application should simulate a game of snap according to the rules below
* The application should output the winner
* You may choose whether all snaps are called by players or whether they can "miss" a snap
* You may choose single or multithreaded approach whichever suits you better, but do include some
form of randomness

### Rules of Snap
* First, cards are split equally between players, discarding any excess cards
* Then, each player takes turns placing a card from their hand onto a stack in front of them
* At any time when there are top cards of stacks matching, any player may call 'Snap!'
* A player is eliminated when they run out of cards
* The game ends either when the first player is eliminated or there is only one player left - whichever is easier / makes more sense to you
* The winner is the player who scored the most cards

## Guidelines
* We recommend you spend no more than 2 hours on the task.
* Feel free to get it back to us however you like (GitHub, email, ...)

Don’t hesitate to get in touch If anything Is unclear, or if you have any questions.
