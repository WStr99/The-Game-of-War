/**
* Class defines Card object.
*/
public class Card{
  private String m_suit;
  private double m_face;

  /**
  * default constructor.
  * default card is a 2 of hearts.
  */
  public Card(){
    m_suit = "Hearts";
    m_face = 2;
  }

  /**
  * Overloaded constructor.
  *
  * @param suit the suit of the card.
  * @param face the face of the card.
  */
  public Card(String suit, double face){
    m_suit = suit;
    m_face = face;
  }

  /**
  * Accessor.
  * Returns suit of card.
  *
  * @return suit of the card.
  */
  public String getSuit(){
    return m_suit;
  }

  /**
  * Accesor.
  * Returns face of card.
  *
  * @return face of the card.
  */
  public double getFace(){
    return m_face;
  }

  /**
  * Modifier.
  * Sets the suit of the card to a given value.
  *
  * @param suit suit of the card.
  */
  public void setSuit(String suit){
    m_suit = suit;
  }

  /**
  * Modifier.
  * Sets the face of the card to a given value.
  *
  * @param face suit of the card.
  */
  public void setFace(double face){
    m_face = face;
  }

  /**
  * Returns a string which is a description of the card.
  * This is done so the correct face can be returned rather than just a number of 1-14.
  *
  * @return String description of the card.
  */
  //if you call this method toString it automatically returns any card as a string without even calling this method on the card object
  public String cardToString() {
    //creating string version of m_face
    String s_face = null;
    //replacing values above 10 with their appropriate face name
    if (m_face == 11) {
      s_face = "Jack";
    }
    else if (m_face == 12) {
      s_face = "Queen";
    }
    else if (m_face == 13) {
      s_face = "King";
    }
    else if (m_face == 14) {
      s_face = "Ace";
    }
    else {
      //converting to an int then a string
      int i_face = (int)m_face;
      s_face = Integer.toString(i_face);
    }
    //returning string description of card.
    return s_face + " of " + m_suit;
  }
}
