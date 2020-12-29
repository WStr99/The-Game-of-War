/**
* Programming mastery project 3
* This program simulates the card game "War" n times and collects/returns statistics from the simulation.
*
* @author Will Strauss
* @version 1.0
*/

/**
* This class simlautes the game of war n number of times.
* It has methods which record the statistics stored in static variables and reports them.
*/
import java.util.*;
public class Simulation extends Game {
  private int m_numGames;
  private ArrayList<Double> m_battles;
  private ArrayList<Double> m_wars;
  private ArrayList<Double> m_doubleWars;
  private static double m_avgBattle;
  private static double m_avgWar;
  private static double m_avgDoubleWar;
  private static double m_maxBattle;
  private static double m_minBattle;
  private static double m_maxWar;
  private static double m_minWar;

  /**
  * Default Constructor.
  */
  public Simulation() {
    m_numGames = 1;
  }

  /**
  * Overloaded Constructor.
  *
  * @param numGames the number of games to be simulated.
  */
  public Simulation(int numGames) {
    m_numGames = numGames;
    m_battles = new ArrayList<Double>();
    m_wars = new ArrayList<Double>();
    m_doubleWars = new ArrayList<Double>();
  }

  /**
  * Accessor.
  * Returns number of games.
  *
  * @return number of games.
  */
  public int getNumGames() {
    return m_numGames;
  }

  /**
  * Modifier.
  * Sets number of games.
  *
  * @param numGames number of games.
  */
  public void setNumGames(int numGames) {
    m_numGames = numGames;
  }

  /**
  * Simulates n number of games.
  */
  public void simulate() {
    //simulates games
    for (int i = 0; i < m_numGames; i++) {
      Game game = new Game();
      game.play(i + 1); //i is the current game number
      //adds statistics from each game to list member variables
      m_battles.add(game.getNumBattle());
      m_wars.add(game.getNumWar());
      m_doubleWars.add(game.getNumDoubleWar());
    }
    System.out.println("SIMULATION COMPLETE:" + "\n");
    System.out.println("Simulations: " + m_numGames + "\n");
  }

  /**
  * Calculates the statistics from all of the games simulated.
  */
  public void calculate() {
    //average number of battles
    double sumBattles = 0;
    for (double battle: m_battles) {
      sumBattles += battle;
    }
    m_avgBattle = (sumBattles/m_battles.size());
    //average number of wars
    double sumWar = 0;
    for (double war: m_wars) {
      sumWar += war;
    }
    m_avgWar = (sumWar/m_wars.size());
    //average number of double wars
    double sumDoubleWars = 0;
    for (double doubleWar: m_doubleWars) {
      sumDoubleWars += doubleWar;
    }
    m_avgDoubleWar = (sumDoubleWars/m_doubleWars.size());
    //min/max number of battles and wars from all of the simulated games
    m_maxBattle = Collections.max(m_battles);
    m_minBattle = Collections.min(m_battles);
    m_maxWar = Collections.max(m_wars);
    m_minWar = Collections.min(m_wars);
  }

  /**
  * Prints out the statistics calculated in calculate().
  * Variables are static so that there is only one copy of each variable which means that the values are stored
  * in them permenantly and can then be accessed via this static method.
  */
  public static void report() {
    System.out.println("Average number of battles:         " + String.format("%.2f", m_avgBattle));
    System.out.println("Average number of wars:            " + String.format("%.2f", m_avgWar));
    System.out.println("Average number of double wars:     " + String.format("%.2f", m_avgDoubleWar));
    System.out.println("Max number of battles:             " + m_maxBattle);
    System.out.println("Min number of battles:             " + m_minBattle);
    System.out.println("Max number of wars:                " + m_maxWar);
    System.out.println("Min number of wars:                " + m_minWar);
    System.out.println("========================================================\n");

  }

/**
 * Prompts the user for the number of simulations they would like to run and calculates/reports the statistics of each game.
 *
 * @param args command-line argument
 */
  public static void main(String[] args) {
    //prompts the user for n number of simulations
    Scanner scnr = new Scanner(System.in);
    System.out.println("\n========================================================\n");
    System.out.println("               WELCOME TO THE GAME OF WAR!\n");
    System.out.println("\n");
    System.out.println("Enter the number of games would you like to simulate below \n");
    System.out.println("\n========================================================\n");
    System.out.print("Simulate: ");
    int n = scnr.nextInt();
    //simulates and calculates results
    Simulation sim = new Simulation(n);
    sim.simulate();
    sim.calculate();
    Simulation.report();
  }
}
