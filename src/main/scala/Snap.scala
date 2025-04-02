import Cards.{Card, Deck, MatchOn}

import scala.annotation.tailrec

object Snap {

  def startGame(
      numberOfPlayers: Int,
      decks: Int,
      rule: MatchOn
  ): Unit = {
    val deck: Deck = Deck.mix(decks).shuffle()
    val hands: List[Deck] = deck.deal(numberOfPlayers)

    hands.zipWithIndex.foreach { case (hand, index) =>
      println(
        s"Hands $index ${hands.mkString(",")}"
      )
    }

    val players: List[Player] = hands.zipWithIndex.map { case (hand, index) =>
      Player.distribute(index, hand)
    }

    println("=========== Starting the game =================")
    players.foreach { println }
    val winner = play(players, rule)
    println(
      s"!!!Winner is player ${winner.index} with ${winner.hand.cards.size + winner.opened.cards.size} cards!!!"
    )
  }

  @tailrec
  private def play(players: List[Player], rule: MatchOn): Player =
    players match {
      // TODO use NonEmptyList from cats to avoid throwing RTE
      case Nil               => throw new IllegalStateException("Players can't be empty")
      case lastPlayer :: Nil => lastPlayer
      case currentPlayer :: rest if currentPlayer.allCardsFinished =>
        println(
          s"Player: ${currentPlayer.index} ELIMINATED!!!, Players ${rest.map(_.index).mkString(",")} will continue"
        )
        play(rest, rule)
      case currentPlayer :: rest if currentPlayer.handEmpty =>
        println(
          s"In hand cards finished for player ${currentPlayer.index}, picking opened ${currentPlayer.opened.cards.size} in hand"
        )
        play(currentPlayer.fillHandWithOpened :: rest, rule)
      case currentPlayer :: rest =>
        play(snapOrNext(currentPlayer, rest, rule), rule)
    }

  private def snapOrNext(
      turn: Player,
      others: List[Player],
      rule: MatchOn
  ): List[Player] = {
    val (openedCard, me) = turn.openCard
    println(s"Player ${turn.index} opened: $openedCard")
    println("Game state before snap:")
    (me :: others).foreach(println)

    val matchingDecks: List[Deck] = others.collect {
      case player if player.cardToMatch.exists(rule.matches(openedCard, _)) =>
        player.opened
    }

    if (matchingDecks.isEmpty) {
      // add current player to the end
      println("There is no match. Game will continue")
      others :+ me
    } else {
      val myOpenedCards = me.opened
      val cardsToSnap =
        (myOpenedCards :: matchingDecks).foldLeft(Deck.empty)((acc, next) =>
          acc.addAll(next)
        )

      // randomise snapper
      val snapperIndex = util.Random.shuffle(turn :: others).head.index
      println(s"Player $snapperIndex SNAP!")
      val afterSnap = (others :+ me).map {
        case snapper if snapper.index == snapperIndex =>
          (
            if (rule.matchesWithTop(openedCard, snapper.opened))
              snapper.discardOpened()
            else snapper
          ).addToHand(cardsToSnap)
        case other if rule.matchesWithTop(openedCard, other.opened) =>
          other.discardOpened()
        case other => other
      }
      println("Game state after snap:")
      afterSnap.foreach { println }
      afterSnap
    }
  }

  case class Player(index: Int, hand: Deck, opened: Deck) {

    def cardToMatch: Option[Card] = opened.cards.headOption

    def allCardsFinished: Boolean = hand.isEmpty && opened.cards.isEmpty

    def handEmpty: Boolean = hand.isEmpty && opened.cards.nonEmpty

    def fillHandWithOpened: Player =
      copy(hand = opened, opened = Deck.empty)

    def discardOpened(): Player =
      copy(opened = Deck.empty)

    def addToHand(deck: Deck): Player =
      copy(hand = hand.addAll(deck))

    def openCard: (Card, Player) = {
      val openedCard = hand.cards.head
      (
        openedCard,
        copy(hand = hand.removeOne(), opened = opened.addOne(openedCard))
      )
    }

    override def toString: String =
      s"Player $index ${opened.cards.headOption.map(c => s"card(face up): $c").getOrElse("")}, cards(in hand) are: ${hand.cards.size}, cards(opened) are: ${opened.cards.size}"
  }

  private object Player {
    def distribute(index: Int, deck: Deck): Player =
      Player(index, hand = deck, opened = Deck.empty)
  }

}
