/**
* Class defines Deck object.
* Deck is made up of Card objects.
*/
import java.util.*;
public class Deck extends Card {
  private LinkedList<Card> m_deck;

  /**
  * Constructor.
  */
  public Deck() {
    m_deck = new LinkedList<Card>();
  }

  /**
  * Accessor.
  * Returns each Card object the deck.
  *
  * @return returns the deck.
  */
  public LinkedList<Card> getDeck() {
      return m_deck;
  }

  /**
  * Accessor.
  * Prints the size of the deck.
  *
  * @return the deck of cards.
  */
  public int getDeckSize() {
    return m_deck.size();
  }

  /**
  * Modifier.
  * Sets the deck.
  * Populates the deck with 52 Card objects of unique faces and suits.
  */
  public void setDeck() {
    //arrays which hold all possible faces and suits for the cards
    String[] suits = new String[] {"Hearts", "Diamonds", "Spades", "Clubs"};
    double[] faces = new double[] {2 ,3, 4 ,5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    //populates deck
    for(String suit: suits) {
      for(double face: faces) {
        m_deck.add(new Card(suit, face));
      }
    }
    //shuffles deck
    Collections.shuffle(m_deck);
  }

  /**
  * deal method removes a random card from the deck and returns it.
  *
  * @return a random card from the deck.
  */
  public Card deal() {
    //generating random number
    Random random = new Random();
    int rand = random.nextInt(m_deck.size());
    //return card and remove it from list
    Card c = m_deck.get(rand);
    m_deck.remove(c);
    return c;
  }

  /**
  * Accessor.
  * Prints each a string of each card in the deck cards.
  *
  */
  public void deckToString() {
    for(Card card: m_deck) {
      System.out.println(card.cardToString());
    }
  }
}
