/**
* Class defines the player object.
* Each player (1 or 2), has a list of 26 unique cards in the list ownedCards.
*/
import java.util.*;
public class Player extends Deck {
  private int m_playerNum;
  private LinkedList<Card> m_ownedCards;

  /**
  * Default constructor.
  * Default player is 1.
  */
  public Player() {
    m_playerNum = 1;
  }

  /**
  * Overloaded constructor.
  *
  * @param playerNum the player number (1 or 2).
  */
  public Player(int playerNum) {
    m_playerNum = playerNum;
    m_ownedCards = new LinkedList<Card>();
  }

  /**
  * Accessor.
  * Returns the player number.
  *
  * @return the player number (1 or 2).
  */
  public int getPlayerNum() {
    return m_playerNum;
  }

  /**
  * Accessor.
  * Prints each card in the deck of owned cards.
  *
  * @return the deck of owned cards.
  */
  public LinkedList<Card> getOwnedCards() {
    for(Card card: m_ownedCards) {
      System.out.println(card.cardToString());
    }
    return m_ownedCards;
  }

  /**
  * Accessor.
  * Prints the size of the player's deck.
  * This is necessary for the war method incase a player has no more cards to put down.
  *
  * @return the deck of owned cards.
  */
  public int getNumCards() {
    return m_ownedCards.size();
  }

/**
* Modifier.
* Sets the player's origianl 26 cards for their deck.
*
* @param ownedCards a list of the player's 26 unique cards.
*/
  public void setOwnedCards(LinkedList<Card> ownedCards) {
    for(Card card: ownedCards){
      m_ownedCards.add(card);
    }
  }

/**
* Modifier.
* Sets the player number.
*
* @param playerNum an int (1 or 2), determining the player number.
*/
  public void setPlayerNum(int playerNum) {
    m_playerNum = playerNum;
  }

  /**
  * Flips the first card in the players deck and removes it from the deck.
  *
  * @return first card in the deck.
  */
  public Card flip() {
    Card c = m_ownedCards.get(0);
    m_ownedCards.remove(c);
    return c;
  }

  /**
  * Takes an external list of cards and adds them to the players list of owned cards.
  * This function will take in any new cards they win during the game.
  * Cards are added in a random order from the external list of drawn cards to avoid an infinite loop.
  *
  * @param collectedCards A linkedList of cards drawn.
  */
  public void collect(LinkedList<Card> collectedCards) {
    //random object
    Random random = new Random();
    //list to store the index's used in order to avoid indexing from the same place twice
    LinkedList<Integer> storeRand = new LinkedList<Integer>();
    //generates non-repeating random numbers and adds cards from external list to list of owned cards in a random order
    while (storeRand.size() < collectedCards.size()) {
      int rand = random.nextInt(collectedCards.size());
      Card c = collectedCards.get(rand);
      if (!storeRand.contains(rand)) {
        c.toString();
        m_ownedCards.add(c);
        storeRand.add(rand);
      }
    }
  }

  /**
  * Check if a plyer has won by checking to see if a plyaer has all 52 cards.
  *
  * @return true if a plyer has won.
  * @return if they have not won yet.
  */
  public boolean hasWon() {
    if (m_ownedCards.size() == 52) {
      return true;
    }
    else {
      return false;
    }
  }

  /**
  * Method for handling wars
  * A war is when two players draw three cards with the same mean value and they must draw another.
  * Whichever player draws the card with the greater face value wins the war and collects all cards.
  *
  * @param cardsInPlay A linked list of the cards that each player put down prior to the war.
  * @param player1 Player 1.
  * @param player2 Player 2.
  */
  public static int war(LinkedList<Card> cardsInPlay, Player player1, Player player2, double numWar) {
    System.out.println("\n========================= WAR! ==========================\n");
    //list of the cards each player draws during the war
    LinkedList<Card> warCardsInPlay = new LinkedList<Card>();
    //control variable
    boolean war = true;
    //record and return number of double wars
    int doubleWar = 0;
    //loop so that the war can be repeated if there is a double war
    while (war == true) {
      //flips player's cards and adds them to list
      Card c1 = player1.flip();
      Card c2 = player2.flip();
      warCardsInPlay.add(c1);
      warCardsInPlay.add(c2);
      System.out.println("Player 1: " + c1.cardToString());
      System.out.println("Player 2: " + c2.cardToString());
      //if player 1 wins they collect all cards and the war stops.
      if (c1.getFace() > c2.getFace()) {
        System.out.println("\n================ Player 1 wins the War! =================\n");
        player1.collect(warCardsInPlay);
        player1.collect(cardsInPlay);
        war = false;
      }
      //if player 2 wins they collect all cards and the war stops.
      else if (c1.getFace() < c2.getFace()){
        System.out.println("\n================ Player 2 wins the War! =================\n");
        player2.collect(warCardsInPlay);
        player2.collect(cardsInPlay);
        war = false;
     }
     //if there is a tie we have a double war and it loops through another time.
      else {
        System.out.println("\n====================== Double War! ======================\n");
        war = true;
        doubleWar++;
      }
    }
    return doubleWar;
  }
}
