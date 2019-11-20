package tableBot.handlers;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.jetbrains.annotations.NotNull;
import tableBot.InfoGetter;
import tableBot.games.Player;
import tableBot.games.gallows.GallowsModel;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GallowsHandler implements ActivityKeeper, Handler
{
    private HashMap<Player, GallowsModel> activityLog;

    public GallowsHandler ()
    {
        activityLog = new HashMap<>();
    }

    public void handleCommand (TextChannel channel, String[] command, String userTag)
    {
        switch (command[1].toLowerCase())
        {
            case ("start"):
            {
                if (! logContains(userTag))
                {
                    addActivity(userTag);
                    byte[] image = activityLog.get(getPlayer(userTag)).gameField.getImage();
                    channel.sendFile(image, "start.jpg").queue();
                }
                else
                    channel.sendMessage(String.format("%s, you have unfinished game. To stop it write '-gallows stop'", userTag)).queue();
                break;
            }
            case ("letter"):
            {
                if (logContains((userTag)))
                {
                    if (checkCommand(command))
                    {
                        GallowsModel gallowsModel = activityLog.get(getPlayer(userTag));
                        gallowsModel.makeMove(command[2].charAt(0));
                        byte[] image = gallowsModel.gameField.getImage();
                        channel.sendFile(image, "nextMove.jpg").queue();
                        if (gallowsModel.isGameEnded())
                            deleteActivity(userTag);
                    }
                    else
                        channel.sendMessage("For guess a letter use command '-gallows letter <letter>'").queue();
                }
                else
                    channel.sendMessage(String.format("%s, you don't have a created gallows game.", userTag)).queue();
                break;
            }
            case ("help"):
            {
                //TODO: send help info about THIS game in the chat
                break;
            }
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

    public boolean checkCommand (@NotNull String[] command)
    {
        if (command.length >= 3 && command[2].length() == 1)
            return Character.isLetter(command[2].charAt(0));
        return false;
    }

    @Override
    public void addActivity (String userTag)
    {
        activityLog.put(new Player(userTag), new GallowsModel());
        InfoGetter.addRecord(userTag, "gallows");
    }

    @Override
    public boolean deleteActivity (String userTag)
    {
        for (Map.Entry<Player, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
            {
                activityLog.remove(entry.getKey());
                InfoGetter.removeRecord(userTag, "gallows");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logContains (String userTag)
    {
        for (Map.Entry<Player, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
                return true;
        }
        return false;
    }

    public Player getPlayer (String userTag)
    {
        for (Map.Entry<Player, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getPlayerTag().compareTo(userTag) == 0)
                return entry.getKey();
        }

        return null;
    }
}
