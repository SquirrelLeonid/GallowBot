package tableBot.games.cards;

import java.util.ArrayList;

enum Suit
{
    SPADES, CLUBS, HEARTS, DIAMONDS
}

enum Value
{

    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(2), QUEEN(3), KING(4), ACE(11);

    private int value;

    Value (int value)
    {
        this.value = value;
    }

    public int getValue ()
    {
        return value;
    }
}

class Deck
{
    private static final ArrayList<Card> deck = new ArrayList<>();

    static ArrayList<Card> getDeck ()
    {
        if (deck.size() == 0)
            createDeck();
        return new ArrayList<>(deck);
    }

    private static void createDeck ()
    {
        for (Value cardName : Value.values())
        {
            for (Suit suit : Suit.values())
                deck.add(new Card(cardName, suit, cardName.getValue()));
        }
    }

}