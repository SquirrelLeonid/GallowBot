package tableBot.handlers;

import tableBot.games.Player;

public interface ActivityKeeper
{
    void addActivity (String userTag);

    boolean deleteActivity (String userTag);

    boolean logContains (String userTag);

    Player getPlayer (String userTag);
}
