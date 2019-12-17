package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import tableBot.Constants;
import tableBot.InfoGetter;
import tableBot.PathGetter;
import tableBot.activityKeepers.GallowsActivityKeeper;
import tableBot.games.gallows.GameModel;

public class GallowsHandler implements Handler
{
    private GallowsActivityKeeper activityKeeper;

    public GallowsHandler ()
    {
        activityKeeper = new GallowsActivityKeeper();
    }

    public void handleCommand (TextChannel channel, String[] command, User user, String messageId)
    {
        String userTag = user.getAsTag();
        if (! checkCommand(command))
            channel.sendMessage(String.format(Constants.WRONG_ARGUMENTS_NUMBER, "gallows")).queue();
        else
        {
            switch (command[1].toLowerCase())
            {
                case ("start"):
                {
                    start(channel, user);
                    break;
                }
                case ("letter"):
                {
                    if (command.length != 3)
                        channel.sendMessage(String.format(Constants.WRONG_ARGUMENTS_NUMBER, "gallows")).queue();
                    else if (! activityKeeper.logContains(user))
                        channel.sendMessage(String.format(Constants.PLAYER_ALREADY_IN_GAME, user.getAsTag(), "gallows")).queue();
                    else if (! isLetter(command[2]))
                        channel.sendMessage((String.format(Constants.WRONG_ARGUMENTS_FORMAT, "gallows"))).queue();
                    else
                        tryGuess(channel, user, command[2].charAt(0));
                    break;
                }
                case ("help"):
                {
                    String path = PathGetter.getTextFolder() + "GallowsHelp.txt";
                    InfoGetter.showHelp(channel, path);
                    break;
                }
                case ("stop"):
                    if (activityKeeper.deleteActivity(user))
                        channel.sendMessage(String.format(Constants.GAME_WAS_STOPPED, userTag)).queue();
                    else
                        channel.sendMessage(String.format(Constants.PLAYER_ALREADY_IN_GAME, user.getAsTag(), "gallows")).queue();
                    break;
                default:
                    channel.sendMessage(Constants.UNKNOWN_COMMAND).queue();
                    break;
            }
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
            channel.sendMessage(String.format(Constants.PLAYER_ALREADY_IN_GAME, user.getAsTag(), "gallows")).queue();
    }

    private void tryGuess (TextChannel channel, User user, char letter)
    {
        if (activityKeeper.logContains(user))
        {
            GameModel gameModel = activityKeeper.getActivity(user);
            gameModel.makeMove(letter);
            byte[] image = gameModel.gameField.getImage();
            channel.sendFile(image, "nextMove.jpg").queue();
            if (gameModel.isGameEnded())
                activityKeeper.deleteActivity(user);
        }
        else
            channel.sendMessage(String.format(Constants.GAME_IS_NOT_CREATED, user.getAsTag(), "gallows")).queue();
    }

    public boolean checkCommand (@NotNull String[] command)
    {
        return command.length == 2 || command.length == 3;
    }

    private boolean isLetter (@NotNull String argument)
    {
        return argument.length() == 1 && Character.isLetter(argument.charAt(0));
    }
}