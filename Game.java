/**
* This class uses the classes Player, Deck, and Card to carry out the rules of the Game of War.
* The class also records the statistics of each game.
*/
import java.util.*;
public class Game extends Player{
  private Player player1;
  private Player player2;
  private double m_numWar;
  private double m_numDoubleWar;
  private double m_numBattle;
  private int m_gameNum;

  /**
  * Default constructor.
  */
  public Game() {
    player1 = new Player(1);
    player2 = new Player(2);
    m_numWar = 0;
    m_numDoubleWar = 0;
    m_numBattle = 0;
  }

  /**
  * Accessor.
  * Returns the number of Wars in a game.
  *
  * @return number of Wars.
  */
  public double getNumWar() {
    return m_numWar;
  }

  /**
  * Accessor.
  * Returns the number of Double Wars in a game.
  *
  * @return number of Double Wars.
  */
  public double getNumDoubleWar() {
    return m_numDoubleWar;
  }

  /**
  * Accessor.
  * Returns the number of Battles in a game.
  *
  * @return number of Battles.
  */
  public double getNumBattle() {
    return m_numBattle;
  }

  /**
  * Accessor.
  * Returns the game number.
  *
  * @return number of Battles.
  */
  public int getGameNum() {
    return m_gameNum;
  }

  /**
  * Carries out the rules of the game of war.
  * The function consists of 4 steps coded in while loops:
  * 1/2) Players 1/2 draw their cards.
  * 3) The players compare cards and the winner collects the cards.
  * 4) The player with 52 cards wins the game.
  */
  public void play(int gameNum) {
    //updating member variable
    m_gameNum = gameNum;
    //creating new deck
    Deck deck = new Deck();
    deck.setDeck();
    //list that will hold the cards once they are drawn by each player until all cards in the list are given to the winner
    LinkedList<Card> cardsInPlay = new LinkedList<Card>();
    //deals 26 unique cards from the deck for player 1
    for (int i = 0; i < 26; ++i) {
      cardsInPlay.add(deck.deal());
    }
    //adds cards to player 1's list of owned cards
    player1.setOwnedCards(cardsInPlay);
    cardsInPlay.clear();
    //deals 26 unique cards from the deck for player 2
    for (int i = 0; i < 26; ++i) {
      cardsInPlay.add(deck.deal());
    }
    //adds cards to player 2's list of owned cards
    player2.setOwnedCards(cardsInPlay);
    cardsInPlay.clear();
    //prints at start of game
    System.out.println("\n");
    System.out.println("                       GAME " + gameNum + ":");
    //control variables to determine the turn number and the game status
    int turn = 1;
    boolean startGame = true;
    //average/max value of cards drawn by the player
    double player1Hand = 0;
    double player2Hand = 0;
    //loops through the Game of War
    while (startGame == true) {
      //prints battle number
      System.out.println("========================================================");
      System.out.println("Battle " + (m_numBattle + 1) + ":\n");
      /**
      * player 1 draws cards
      */
      while (turn == 1) {
        if (player1.getNumCards() >= 3) {
          //flips players cards and adds them to the list which will be given to the winner of the battle
          Card c1 = player1.flip();
          Card c2 = player1.flip();
          Card c3 = player1.flip();
          System.out.println("Player 1 hand:\n" + c1.cardToString()+ ", " + c2.cardToString() + ", " + c3.cardToString());
          cardsInPlay.add(c1);
          cardsInPlay.add(c2);
          cardsInPlay.add(c3);
          player1Hand = ((c1.getFace() + c2.getFace() + c3.getFace())/3);
          Card[] p1Hand = {c1, c2, c3};

        }
        //flips two cards if the player only has two cards left
        else if (player1.getNumCards() == 2) {
          Card c1 = player1.flip();
          Card c2 = player1.flip();
          System.out.println("Player 2 hand:\n" + c1.cardToString() + ", " + c2.cardToString());
          cardsInPlay.add(c1);
          cardsInPlay.add(c2);
          //takes max value of 2 cards
          if (c1.getFace() > c2.getFace()) {
            player1Hand = c1.getFace();
          }
          else {
            player1Hand = c2.getFace();
          }
          Card[] p1Hand = {c1, c2};
        }
        //flips one card if the player only has one card left
        else if (player1.getNumCards() == 1) {
          Card c1 = player1.flip();
          System.out.println("Player 2 hand:\n" + c1.cardToString());
          cardsInPlay.add(c1);
          player1Hand = c1.getFace();
          Card[] p1Hand = {c1};
        }
        System.out.println("Player 1 score : " + String.format("%.2f", player1Hand) + "\n");
        turn = 2;
      }
      /**
      * player 2 draws cards
      */
      while (turn == 2) {
        if (player2.getNumCards() >= 3) {
          //flips players cards and adds them to the list which will be given to the winner of the battle
          Card c1 = player2.flip();
          Card c2 = player2.flip();
          Card c3 = player2.flip();
          System.out.println("Player 2 hand:\n" + c1.cardToString()+ ", " + c2.cardToString() + ", " + c3.cardToString());
          cardsInPlay.add(c1);
          cardsInPlay.add(c2);
          cardsInPlay.add(c3);
          player2Hand = ((c1.getFace() + c2.getFace() + c3.getFace())/3);
          Card[] p2Hand = {c1, c2, c3};
        }
        //flips two cards if the player only has two cards left
        else if (player2.getNumCards() == 2) {
          Card c1 = player2.flip();
          Card c2 = player2.flip();
          System.out.println("Player 2 hand:\n" + c1.cardToString()+ ", " + c2.cardToString());
          cardsInPlay.add(c1);
          cardsInPlay.add(c2);
          //takes max value of 2 cards
          if (c1.getFace() > c2.getFace()) {
            player2Hand = c1.getFace();
          }
          else {
            player2Hand = c2.getFace();
          }
          Card[] p2Hand = {c1, c2};
        }
        //flips one card if the player only has one card left
        else if (player2.getNumCards() == 1) {
          Card c1 = player2.flip();
          System.out.println("Player 2 hand:\n" + c1.cardToString());
          cardsInPlay.add(c1);
          player2Hand = c1.getFace();
          Card[] p2Hand = {c1};
        }
        System.out.println("Player 2 score: " + String.format("%.2f", player2Hand) + "\n");
        turn = 3;
      }
      /**
      * compares player1 and player 2's cards
      * adds all of the cards that are drawn to the winner's deck
      */
      while (turn == 3) {
        //if player 1 wins the battle
        if(player1Hand > player2Hand){
          player1.collect(cardsInPlay);
          cardsInPlay.clear();
        }
        //if player 2 wins the battle
        else if (player1Hand < player2Hand) {
          player2.collect(cardsInPlay);
          cardsInPlay.clear();
          Card[] wonCards = cardsInPlay.toArray(new Card[cardsInPlay.size()]);
        }
        //if the players tie, a war will ensue
        else if (player1Hand == player2Hand){
          //if either player has no more cards to draw in a war, then the other player will win and collect all cards that have been drawn
          if (player1.getNumCards() > 3 && player2.getNumCards() <= 3) {
            player1.collect(cardsInPlay);
            cardsInPlay.clear();
          }
          else if (player1.getNumCards() <= 3 && player2.getNumCards() > 3) {
            player2.collect(cardsInPlay);
            cardsInPlay.clear();
          }
          //if both players have more than 3 cards, a war ensues
          else {
            m_numWar++;
            m_numDoubleWar += Player.war(cardsInPlay, player1, player2, m_numWar);
            cardsInPlay.clear();

          }
        }
        //prints the number of cards left in each player's deck
        System.out.println("Player 1: " + player1.getNumCards() + " cards in deck.");
        System.out.println("Player 2: " + player2.getNumCards() + " cards in deck.");
        m_numBattle++;
        turn = 4;
      }
      /**
      * checks the number of cards in each player's hand and declares a winner or runs back through the loop
      */
      while(turn == 4){
        //if player 1 wins
        if (player1.getNumCards() == 52) {
          System.out.println("========================================================\n");
          System.out.println("                       Game Over");
          System.out.println("                     Player 1 wins!\n");
          System.out.println("      Battles: " + m_numBattle + " Wars: " + m_numWar + " Double Wars: " + m_numDoubleWar + "\n");
          System.out.println("========================================================");
          turn = 0;
          startGame = false;
        }
        //if player 2 wins
        else if (player2.getNumCards() == 52) {
          System.out.println("========================================================\n");
          System.out.println("                       Game Over");
          System.out.println("                     Player 2 wins!\n");
          System.out.println("      Battles: " + m_numBattle + " Wars: " + m_numWar + " Double Wars: " + m_numDoubleWar + "\n");
          System.out.println("========================================================");
          turn = 0;
          startGame = false;
        }
        //if no player has won, starts a new battle*/
        else {
          turn = 1;
        }
      }
    }
  }
}
