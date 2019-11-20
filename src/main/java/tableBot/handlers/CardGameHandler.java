package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import tableBot.InfoGetter;
import tableBot.games.Player;
import tableBot.games.cards.CardgameModel;

import java.util.HashMap;
import java.util.Map;

public class CardGameHandler implements ActivityKeeper, Handler
{
    private HashMap<Player, CardgameModel> activityLog;

    public CardGameHandler ()
    {
        activityLog = new HashMap<>();
    }

    @Override
    public void handleCommand (TextChannel channel, String[] command, String userTag)
    {
        switch (command[1].toLowerCase())
        {
            case ("start"):
                if (! logContains(userTag))
                {
                    addActivity(userTag);
                    channel.sendMessage("Create was successful").queue();
                }
                else
                    channel.sendMessage(String.format("%s, you have unfinished game. To stop it write '-gallows stop'", userTag)).queue();
                break;
            case ("help"):
                break;
            case ("take"):
                break;
            case ("enough"):
                break;
            case ("stop"):
                if (deleteActivity(userTag))
                    channel.sendMessage(String.format("%s, your game was stopped.", userTag)).queue();
                else
                    channel.sendMessage(String.format("%s, you don't have a created gallows game.", userTag)).queue();
                break;
            default:
                break;
        }
    }

    @Override
    public void addActivity (String userTag)
    {
        activityLog.put(new Player(userTag), new CardgameModel());
        InfoGetter.addRecord(userTag, "cards");
    }

    @Override
    public boolean deleteActivity (String userTag)
    {
        for (Map.Entry<Player, CardgameModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
            {
                activityLog.remove(entry.getKey());
                InfoGetter.removeRecord(userTag, "cards");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logContains (String userTag)
    {
        for (Map.Entry<Player, CardgameModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
                return true;
        }
        return false;
    }

    @Override
    public Player getPlayer (String userTag)
    {
        for (Map.Entry<Player, CardgameModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
                return entry.getKey();
        }

        return null;
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}
