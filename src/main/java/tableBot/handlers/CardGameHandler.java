package tableBot.handlers;

import tableBot.games.Player;

public class CardGameHandler implements ActivityKeeper, Handler
{


    @Override
    public void addActivity (String userTag)
    {

    }

    @Override
    public boolean deleteActivity (String userTag)
    {
        return false;
    }

    @Override
    public boolean logContains (String userTag)
    {
        return false;
    }

    @Override
    public Player getPlayer (String userTag)
    {
        return null;
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}
