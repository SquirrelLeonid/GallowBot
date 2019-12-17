package tableBot.games.cards;

import tableBot.PathGetter;

import java.io.File;

public class Card
{
    private File cardImg;
    private int cardValue;

    public Card (Value name, Suit suit, int value)
    {
        String cardImgName = String.format("%s_%s.png", name, suit);
        cardImg = loadImage(cardImgName);
        cardValue = value;
    }

    //Called when need send image to player
    public File getImage ()
    {
        return cardImg;
    }

    public int getCardValue ()
    {
        return cardValue;
    }

    private File loadImage (String cardImgName)
    {
        return new File(PathGetter.getImageFolder() + File.separator + String.format("Cards%s%s", File.separator, cardImgName));
    }
}
