import Cards.MatchOn

import scala.io.StdIn
import scala.util.Try

object Main {

  def main(args: Array[String]): Unit = {
    println("Welcome to the game of Snap!!")

    println(s"How many players are playing the game(at least 2): ")
    val numberOfPlayers = Try(StdIn.readInt()).getOrElse(2)

    // can be more than 1 deck
    println("How many playing card decks to play(at least 1): ")
    val decks = Try(StdIn.readInt()).getOrElse(1)

    println(s"Should cards should be matched: on suit(s), value(v), or both(b)")
    println("Enter your choice, s = suits, v = value, b = both")
    val ruleChoice = StdIn.readChar()

    // default to SuitAndValue
    val ruleToMatchOn: MatchOn =
      MatchOn.fromChoice(ruleChoice).getOrElse(MatchOn.SuitAndValue)

    Snap.startGame(numberOfPlayers, decks, ruleToMatchOn)
  }
}
