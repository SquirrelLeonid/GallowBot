package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import tableBot.InfoGetter;
import tableBot.PathGetter;
import tableBot.activityKeepers.CardsActivityKeeper;

import java.util.Timer;
import java.util.TimerTask;

public class CardGameHandler implements Handler
{
    private CardsActivityKeeper activityKeeper;
    public StringBuilder playerList;

    public CardGameHandler ()
    {
        activityKeeper = new CardsActivityKeeper();
        playerList = new StringBuilder("testUser");
    }

    @Override
    public void handleCommand (TextChannel channel, String[] command, User user)
    {
        String userTag = user.getAsTag();
        switch (command[1].toLowerCase())
        {
            case ("start"):
                channel.sendMessage("Who's in card game?").queue(ch -> ch.addReaction("🃏").queue());
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run ()
                    {
                        channel.sendMessage(playerList.toString()).queue();
                    }
                };
                timer.schedule(task, 5000); //30 seconds
                break;
            case ("help"):
                String path = PathGetter.getTextFolder() + "CardsHelp.txt";
                InfoGetter.showHelp(channel, path);
                break;
            default:
                break;
        }
    }

    public void addUserReaction (String messageId, User user)
    {
        playerList.append(user.getAsTag());
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}
