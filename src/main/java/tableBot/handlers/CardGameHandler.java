package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import tableBot.InfoGetter;
import tableBot.PathGetter;
import tableBot.activityKeepers.CardsActivityKeeper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CardGameHandler implements Handler
{
    private CardsActivityKeeper activityKeeper;
    public ArrayList<String> playerList;
    private Timer timer;

    public CardGameHandler ()
    {
        activityKeeper = new CardsActivityKeeper();
        timer = new Timer();
        playerList = new ArrayList<>();
    }

    @Override
    public void handleCommand (TextChannel channel, String[] command, User user)
    {
        String userTag = user.getAsTag();
        switch (command[1].toLowerCase())
        {
            case ("start"):
                channel.sendMessage("Who's in card game?").queue(ch -> ch.addReaction("🃏").queue());
                TimerTask task = new TimerTask() {
                    @Override
                    public void run ()
                    {
                        System.out.println(System.identityHashCode(playerList));
                        channel.sendMessage(String.valueOf(System.identityHashCode(playerList))).queue();
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
        playerList.add(user.getAsTag());
        System.out.println(user.getAsTag() + "was added, identity hash for list is " + System.identityHashCode(playerList));
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}
