public class Poker{

    public Card[] hand;
    public int[] count;

    public Poker(){
        hand = new Card[5];
        count = new int[13];

        Deck d = new Deck();
        for(int i=0; i<5; ++i){
            hand[i]=d.drawCard();
            count[hand[i].getValue()-1]++;
        }
    }

    public void sortbyValue(Card[] c){
        int i;
        int j;
        int minPos;
        for(i=0; i<c.length; ++i){
            minPos=i;
            for(j=i+1;j<c.length;++j)
                if(c[j].getValue()<c[minPos].getValue())
                    minPos=j;
            Card temp = c[i];
            c[i]=c[minPos];
            c[minPos]=temp;
        }
    }

    public void sortbySuit(Card[] c){
        int i;
        int j;
        int minPos;
        for(i=0; i<c.length; ++i){
            minPos=i;
            for(j=i+1;j<c.length;++j)
                if(c[j].getSuit()<c[minPos].getSuit())
                    minPos=j;
            Card temp = c[i];
            c[i]=c[minPos];
            c[minPos]=temp;
        }
    }

    public boolean StraightFlush(){
        sortbySuit(hand);
        if(hand[0].getSuit()!=hand[4].getSuit())
            return false;
        if(count[0]==1 && count[9]==1 && count[10]==1 && count[11]==1 && count[12]==1)
            return true;   //accounts for 10-Jack-Queen-King-Ace
        for(int i=0; i<count.length-4;++i)
            if(count[i]==1 && count[i+1]==1 && count[i+2]==1 && count[i+3]==1 && count[i+4]==1)
                return true;
        return false;
    }

    public boolean FourofaKind(){
        sortbyValue(hand);
        for(int i=0; i<count.length; ++i)
            if(count[i]==4)
                return true;
        return false;
    }

    public boolean FullHouse(){
        sortbyValue(hand);

        for(int i=0; i<count.length; ++i)
            if(count[i]==3)
                for(i=0; i<count.length; ++i)
                    if(count[i]==2)
                        return true;
        return false;
    }

    public boolean Flush(){
        if(StraightFlush())
            return false;

        sortbySuit(hand);
        if(hand[0].getSuit()==hand[4].getSuit())
            return true;
        return false;
    }

    public boolean Straight(){
        if(StraightFlush())
            return false;

        sortbyValue(hand);
        if(count[0]==1 && count[9]==1 && count[10]==1 && count[11]==1 && count[12]==1)
            return true;    //accounts for 10, Jack, Queen, King, Ace
        for(int i=0; i<count.length-4;++i)
            if(count[i]==1 && count[i+1]==1 && count[i+2]==1 && count[i+3]==1 && count[i+4]==1)
                return true;
        return false;
    }

    public boolean ThreeofaKind(){
        sortbyValue(hand);

        for(int i=0; i<count.length; ++i)
            if(count[i]==3)
                for(i=0; i<count.length; ++i)
                    if(count[i]==1)
                        return true;
        return false;
    }

    public boolean TwoPair(){
        for(int i=0; i<count.length; ++i)
            if(count[i]==2)
                for(int j=i+1; j<count.length; ++j)
                    if(count[j]==2)
                        return true;
        return false;
    }


    public boolean OnePair(){
        if(TwoPair()||FullHouse())
            return false;

        for(int i=0; i<count.length;++i)
            if(count[i]==2)
                return true;
        return false;
    }

    public int getHand(){
        if(StraightFlush())
            return 0;
        if(FourofaKind())
            return 1;
        if(FullHouse())
            return 2;
        if(Flush())
            return 3;
        if(Straight())
            return 4;
        if(ThreeofaKind())
            return 5;
        if(TwoPair())
            return 6;
        if(OnePair())
            return 7;
        return 8;
    }

    public String toString() {
        int rank = getHand();
        switch(rank){
            case 0:
                return "Straight flush";
            case 1:
                return "Four of a kind";
            case 2:
                return "Full house";
            case 3:
                return "Flush";
            case 4:
                return "Straight";
            case 5:
                return "Three of a kind";
            case 6:
                return "Two pair";
            case 7:
                return "One pair";
            default:
                return "Something else";
        }
    }

    public static final String[] HANDNAME = {"Straight flush", "Four of a kind", "Full house", "Flush", "Straight", "Three of a kind", "Two pair", "One pair", "Something else"};

    public static void main(String[] args){
        int[] handCount = new int[9];

        for(int j=0; j<100000; ++j){
            Poker p = new Poker();
            int rank = p.getHand();
            handCount[rank]++;

	    /*	    if(p.StraightFlush()||p.Straight()){
		for(int i=0; i<p.hand.length;++i)
		    System.out.println(p.hand[i]);
		System.out.println();
		}*/
        }

        for(int i=0; i<handCount.length; ++i)
            System.out.println(HANDNAME[i] + " occured " + handCount[i] + " times.");
    }
}