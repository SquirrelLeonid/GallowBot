package TableBot.Games;

import TableBot.Games.Gallows.GallowsModel;
import TableBot.Games.Cards.CardgameModel;

public class GameModels
{
    private GallowsModel gallowsModel;
    private CardgameModel cardgameModel;
    private String gameName;

    public GameModels (Object model)
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
