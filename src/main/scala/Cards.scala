import scala.util.Random

object Cards {

  sealed trait Suit {
    override def toString: String = this match {
      case Suit.Heart   => "heart"
      case Suit.Diamond => "diamond"
      case Suit.Club    => "club"
      case Suit.Spade   => "spade"
    }
  }

  object Suit {
    case object Heart extends Suit
    case object Diamond extends Suit
    case object Club extends Suit
    case object Spade extends Suit
    val all: List[Suit] = List(Heart, Diamond, Club, Spade)
  }

  sealed trait FaceValue {
    override def toString: String = this match {
      case FaceValue.Ace   => "1"
      case FaceValue.Two   => "2"
      case FaceValue.Three => "3"
      case FaceValue.Four  => "4"
      case FaceValue.Five  => "5"
      case FaceValue.Six   => "6"
      case FaceValue.Seven => "7"
      case FaceValue.Eight => "8"
      case FaceValue.Nine  => "9"
      case FaceValue.Ten   => "10"
      case FaceValue.Jack  => "11"
      case FaceValue.Queen => "12"
      case FaceValue.King  => "13"
    }
  }

  object FaceValue {
    case object Ace extends FaceValue
    case object Two extends FaceValue
    case object Three extends FaceValue
    case object Four extends FaceValue
    case object Five extends FaceValue
    case object Six extends FaceValue
    case object Seven extends FaceValue
    case object Eight extends FaceValue
    case object Nine extends FaceValue
    case object Ten extends FaceValue
    case object Jack extends FaceValue
    case object Queen extends FaceValue
    case object King extends FaceValue
    val all: List[FaceValue] = List(
      Ace,
      Two,
      Three,
      Four,
      Five,
      Six,
      Seven,
      Eight,
      Nine,
      Ten,
      Jack,
      Queen,
      King
    )
  }

  case class Card(suit: Suit, value: FaceValue) {
    def isSameSuit(other: Card): Boolean = other.suit == suit

    def isSameValue(other: Card): Boolean = other.value == value

    override def toString: String = s"$value of $suit"
  }

  sealed trait MatchOn {

    def matches(card1: Card, card2: Card): Boolean = this match {
      case MatchOn.Value => card1.isSameValue(card2)
      case MatchOn.Suit  => card1.isSameSuit(card2)
      case MatchOn.SuitAndValue =>
        card1.isSameValue(card2) && card1.isSameSuit(card2)
    }

    def matchesWithTop(card: Card, deck: Deck): Boolean =
      deck.cards.headOption.exists(matches(_, card))
  }

  object MatchOn {
    case object Value extends MatchOn
    case object Suit extends MatchOn
    case object SuitAndValue extends MatchOn

    def fromChoice(choice: Char): Option[MatchOn] = choice match {
      case 'v' | 'V' => Some(Value)
      case 's' | 'S' => Some(Suit)
      case 'b' | 'B' => Some(SuitAndValue)
      case _         => None
    }
  }

  case class Deck(cards: List[Card]) {

    def addAll(other: Deck): Deck = Deck(cards ::: other.cards)

    def addOne(card: Card): Deck = Deck(card :: cards)

    def removeOne(): Deck = cards.headOption.fold(this)(_ => Deck(cards.tail))

    def place(card: Card): Deck = Deck(card :: cards)

    def isEmpty: Boolean = cards.isEmpty

    def nonEmpty: Boolean = !isEmpty

    def shuffle(): Deck = Deck(Random.shuffle(cards))

    // extras are discarded
    def deal(players: Int): List[Deck] = {
      val shuffledDeck = shuffle()
      val deckSize = shuffledDeck.cards.size
      val cardsPerPlayer = deckSize / players
      val extraCards = deckSize % players
      shuffledDeck.cards
        .take(deckSize - extraCards)
        .grouped(cardsPerPlayer)
        .toList
        .map(Deck.apply)
    }

    override def toString: String =
      s"(${cards.size} cards): ${cards.mkString(",")}"
  }
  object Deck {

    def mix(numberOfDecks: Int): Deck =
      (0 until numberOfDecks).foldLeft(empty)((acc, next) => acc.addAll(full))

    def takeN(n: Int): Deck = Deck(Deck.full.cards.take(n))

    val empty: Deck = Deck(Nil)

    val full: Deck = Deck(for {
      suit <- Suit.all
      value <- FaceValue.all
    } yield Card(suit, value))
  }

}
