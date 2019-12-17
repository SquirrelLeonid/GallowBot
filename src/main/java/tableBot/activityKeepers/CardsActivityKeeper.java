package tableBot.activityKeepers;

import net.dv8tion.jda.api.entities.User;
import tableBot.InfoGetter;
import tableBot.games.cards.Player;

import java.util.HashSet;

public class CardsActivityKeeper implements ActivityKeeper
{
    private HashSet<Player> activePlayers;

    public CardsActivityKeeper()
    {
        activePlayers = new HashSet<>();
    }

    @Override
    public void addActivity (User user)
    {
        activePlayers.add(new Player(user));
        InfoGetter.addRecord("cards", user);
    }

    @Override
    public boolean deleteActivity (User user)
    {
        if (activePlayers.removeIf(player -> player.getAsTag().compareTo(user.getAsTag()) == 0))
        {
            InfoGetter.removeRecord("cards", user);
            return true;
        }
        return false;
    }

    @Override
    public boolean logContains (User user)
    {
        for (Player player: activePlayers)
        {
            if (player.getAsTag().compareTo(user.getAsTag()) == 0)
                return true;
        }
        return false;
    }
}
