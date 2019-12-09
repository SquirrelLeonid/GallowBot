package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import tableBot.Constants;
import tableBot.InfoGetter;
import tableBot.PathGetter;
import tableBot.games.dice.Thrower;

public class DiceRollHandler implements Handler
{

    @Override
    public void handleCommand (TextChannel channel, String[] command, User user)
    {
        String userTag = user.getAsTag();
        if (! checkCommand(command))
            channel.sendMessage(String.format(Constants.WRONG_ARGUMENTS_NUMBER, "throw")).queue();
        else
        {
            switch (command[1].toLowerCase())
            {
                case ("coin"):
                    channel.sendMessage(userTag + " throwed a(n) " + Thrower.throwCoin()).queue();
                    break;
                case ("dice"):
                    if (command.length != 4)
                        channel.sendMessage(String.format(Constants.WRONG_ARGUMENTS_NUMBER, "throw")).queue();
                    else if (! isNumbers(command[2], command[3]))
                        channel.sendMessage(String.format(Constants.WRONG_ARGUMENTS_FORMAT, "throw")).queue();
                    else
                    {
                        int diceCount = Integer.parseInt(command[2]);
                        int sidesCount = Integer.parseInt(command[3]);
                        channel.sendMessage(userTag + " throwed next numbers: " + Thrower.throwDice(diceCount, sidesCount)).queue();
                    }
                    break;
                case ("help"):
                    String path = PathGetter.getTextFolder() + "ThrowHelp.txt";
                    InfoGetter.showHelp(channel, path);
                    break;
                default:
                    channel.sendMessage(Constants.UNKNOWN_COMMAND).queue();
                    break;
            }
        }
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return command.length == 2 || command.length == 4;
    }

    private boolean isNumbers (String first, String second)
    {
        String splice = first + second;
        try
        {
            Integer.parseInt(splice);
            return true;
        } catch (NumberFormatException exception)
        {
            return false;
        }
    }
}
