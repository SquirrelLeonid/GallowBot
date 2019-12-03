package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import tableBot.activityKeepers.GallowsActivityKeeper;
import tableBot.games.gallows.GallowsModel;

public class GallowsHandler implements Handler
{
    private GallowsActivityKeeper activityKeeper;

    public GallowsHandler ()
    {
        activityKeeper = new GallowsActivityKeeper();
    }

    public void handleCommand (TextChannel channel, String[] command, User user)
    {
        String userTag = user.getAsTag();
        switch (command[1].toLowerCase())
        {
            case ("start"):
            {
                start(channel, user);
                break;
            }
            case ("letter"):
            {
                if (checkCommand(command))
                    tryGuess(channel, user, command[2].charAt(0));
                else
                    channel.sendMessage("For guess a letter use command '-gallows letter <letter>'").queue();
                break;
            }
            case ("help"):
            {
                //TODO: send help info about THIS game in the chat
                break;
            }
            case ("stop"):
                if (activityKeeper.deleteActivity(user))
                    channel.sendMessage(String.format("%s, your game was stopped.", userTag)).queue();
                else
                    channel.sendMessage(String.format("%s, you don't have a created gallows game.", userTag)).queue();
                break;
            default:
                break;
        }
    }

    private void start (TextChannel channel, User user)
    {
        if (! activityKeeper.logContains(user))
        {
            activityKeeper.addActivity(user);
            byte[] image = activityKeeper.getActivity(user).gameField.getImage();
            channel.sendFile(image, "start.jpg").queue();
        }
        else
            channel.sendMessage(String.format("%s, you have unfinished game. To stop it write '-gallows stop'", user.getAsTag())).queue();
    }

    private void tryGuess (TextChannel channel, User user, char letter)
    {
        if (activityKeeper.logContains(user))
        {
            GallowsModel gallowsModel = activityKeeper.getActivity(user);
            gallowsModel.makeMove(letter);
            byte[] image = gallowsModel.gameField.getImage();
            channel.sendFile(image, "nextMove.jpg").queue();
            if (gallowsModel.isGameEnded())
                activityKeeper.deleteActivity(user);
        }
        else
            channel.sendMessage(String.format("%s, you don't have a created gallows game.", user.getAsTag())).queue();
    }

    public boolean checkCommand (@NotNull String[] command)
    {
        if (command.length >= 3 && command[2].length() == 1)
            return Character.isLetter(command[2].charAt(0));
        return false;
    }
}
