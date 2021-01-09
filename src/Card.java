import java.util.Scanner;

public class Card {

    private int value;         // 1=ace, ..., 11=jack, 12=queen, 13=king
    private int suit;          // 0=spades, 1=hearts, 2=diamonds, 3=clubs

    public Card (int v, int s) {
        value = v;
        suit = s;
    }

    /********************** non-static methods **********************************/

    public int getValue() {
        return value;
    }

    public int getSuit() {
        return suit;
    }

    public String toString() { // returns a string for the card, e.g., King of Spades

        return getValueString(value) + " of " + getSuitString(suit);
    }

    /********************** static variable and methods *************************/

    private static final String[] SUITS = {"Spades", "Hearts", "Diamonds", "Clubs" };

    public static String getSuitString(int i) {

        if (i >= 0 && i <= 3)
            return SUITS[i];
        else
            throw new RuntimeException
                    ("Tried to use getSuitString on an incorrect value");
    }

    public static String getValueString(int i) {

        // This method should return a string for the given value, e.g., 2, 3, 4,
        // ..., 10, Jack, Queen, King

        if (i < 1 || i > 13)
            throw new RuntimeException
                    ("Tried to use getValueString on an incorrect value");

        else switch (i) {
            case 1: return "Ace";
            case 11: return "Jack";
            case 12: return "Queen";
            case 13: return "King";
            default: return "" + i;
        }
    }

    /********************** main, for testing purposes only ********************/

    public static void main (String[] args) {

        Scanner keyboard = new Scanner(System.in);

        while (true) {

            System.out.print ("Enter suit number: ");
            int s = keyboard.nextInt();

            System.out.print ("Enter value number: ");
            int v = keyboard.nextInt();

            System.out.println ("The suit is " + getSuitString(s));
            System.out.println ("The value is " + getValueString(v));

            Card c = new Card(v, s);
            System.out.println ("This card is the " + c);
        }
    }
}
