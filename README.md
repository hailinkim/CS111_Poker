# Poker Hand Evaluator

Developed a Java program that creates, evaluates and compares poker hands.

### Implementation

![Image](https://github.com/hailinkim/cs111_Poker/blob/master/screenshots/poker.png)

## `Card` class

```
public Card (int v, int s) {
        value = v; // 1=ace, ..., 11=jack, 12=queen, 13=king
        suit = s; // 0=spades, 1=hearts, 2=diamonds, 3=clubs
}
```

---

## `Deck` class

```
public Deck() { // This constructor creates a standard deck

        cards = new Card[52];

        int count=0;

        for (int s=0; s<4; ++s)
            for (int v=1; v<=13; ++v)
                cards[count++] = new Card(v, s);

        cardsLeft=0;
        shuffle();
    }
```

---

## `Poker` class

```
public Poker(){
        hand = new Card[5];
        count = new int[13];

        Deck d = new Deck();
        for(int i=0; i<5; ++i){
            hand[i]=d.drawCard();
            count[hand[i].getValue()-1]++;
        }
    }
```
