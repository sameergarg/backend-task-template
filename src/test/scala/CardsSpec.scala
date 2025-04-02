
import Cards.FaceValue.{Ace, Ten}
import Cards.Suit.{Club, Diamond}
import Cards.{Card, Deck}
import org.scalatest.Inspectors.forAll
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class CardsSpec extends AnyWordSpec with Matchers {
  "A deck of cards" when {
    "be made" should {
      "there is 1 deck" in {
        Deck.mix(1) shouldBe Deck.full
      }
      "there are 2 decks" in {
        Deck.mix(2).cards.size shouldBe 104
      }
    }

    "be shuffled randomly" in {
      val unShuffled = Deck.full
      val shuffled = unShuffled.shuffle()
      shuffled should not be unShuffled
    }

    "distributed evenly" when {
      "there is no leftover" in {
        forAll(Deck.full.deal(players = 4)) { hand =>
          hand.cards.size shouldBe 13
        }
      }
      "there is leftover" in {
        forAll(Deck.full.deal(players = 3)) { hand =>
          hand.cards.size shouldBe 17
        }
      }
    }
  }

  "A card" should {
    "identify same suit" in {
      Card(Diamond, Ace).isSameSuit(Card(Diamond, Ten)) shouldBe true
    }

    "identify different suit" in {
      Card(Club, Ace).isSameSuit(Card(Diamond, Ten)) shouldBe false
    }

    "identify same value" in {
      Card(Diamond, Ace).isSameValue(Card(Club, Ace)) shouldBe true
    }

    "identify different value" in {
      Card(Club, Ace).isSameValue(Card(Diamond, Ten)) shouldBe false
    }
  }
}
