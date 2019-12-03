package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import tableBot.InfoGetter;
import tableBot.activityKeepers.ActivityKeeper;
import tableBot.games.cards.CardgameModel;

import java.util.HashMap;
import java.util.Map;

public class CardGameHandler implements Handler
{
    public CardGameHandler ()
    {
    }

    @Override
    public void handleCommand (TextChannel channel, String[] command, User user)
    {

    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}
