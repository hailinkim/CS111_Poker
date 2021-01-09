public class Deck {

    /******************************************************************************

     A Deck object represents a deck of cards.

     ******************************************************************************/

    private Card[] cards;             // The cards
    private int cardsLeft;           // The number of cards not yet drawn.
    // Make sure that the undrawn cards are in
    // positions 0 through cardsLeft-1.
    /************************ constructors ***************************************/

    public Deck() {                  // This constructor creates a standard deck

        cards = new Card[52];

        int count=0;

        for (int s=0; s<4; ++s)
            for (int v=1; v<=13; ++v)
                cards[count++] = new Card(v, s);

        cardsLeft=0;
        shuffle();
    }

    /***************************************************************************
     drawCard(): This method returns a Card from the deck.  It should
     return null if the deck is empty.
     ****************************************************************************/

    public Card drawCard() {
        // To draw a card, it isn't necessary to change the contents of the array.
        // But you do want to make sure that each card can be drawn only once.
        // Do you see how to do this?  (Hint: it's easiest to draw cards from the
        // high end of the array.)

        if (cardsLeft <= 0)
            throw new RuntimeException("The deck is empty");
        else
            return cards[--cardsLeft];
    }


    /***************************************************************************
     getCardsRemaining(): This method tells how many cards remain in the deck.
     ****************************************************************************/

    public int getCardsRemaining() {

        return cardsLeft;

    }

    /***************************************************************************
     shuffle(): This method shuffles the deck.
     ****************************************************************************/

    public void shuffle() {

        for (int i=cards.length-1; i>=cardsLeft; --i) {

            int r = (int)(Math.random()*(i+1));  // pick a random pos <= i

            Card t = cards[i];
            cards[i] = cards[r];
            cards[r] = t;

        }
        cardsLeft = cards.length;
    }

    /***************************************************************************
     main(): This method is used for testing the Deck class
     ****************************************************************************/

    public static void main (String[] args) {

        Deck d = new Deck();

        for (int i=0; i<20; ++i) {
            for (int j=0; j<5; ++j)
                System.out.println(d.drawCard());
            System.out.println();
            d.shuffle();
        }

    }
}