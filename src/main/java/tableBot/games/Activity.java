package tableBot.games;

import tableBot.games.gallows.GallowsModel;
import tableBot.games.cards.CardgameModel;

public class Activity
{
    public GallowsModel gallowsModel;
    public CardgameModel cardgameModel;
    private String gameName;

    public Activity (Object model)
    {
        if (model instanceof GallowsModel)
        {
            gallowsModel = (GallowsModel) model;
            gameName = "gallows";
        }
        else if (model instanceof CardgameModel)
        {
            cardgameModel = (CardgameModel) model;
            gameName = "twenty-one";
        }
    }

    public String getGameName()
    {
        return gameName;
    }
}
