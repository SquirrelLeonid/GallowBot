package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;
import tableBot.Constants;
import tableBot.InfoGetter;
import tableBot.PathGetter;
import tableBot.activityKeepers.CardsActivityKeeper;
import tableBot.games.cards.GameModel;

import java.util.*;

public class CardsHandler implements Handler
{
    private Timer timer;
    private CardsActivityKeeper activityKeeper;
    private HashMap<String, ArrayList<User>> votesList; //MessageID => list of voted users.

    public CardsHandler ()
    {
        timer = new Timer();
        activityKeeper = new CardsActivityKeeper();
        votesList = new HashMap<>();
    }

    @Override
    public void handleCommand (TextChannel channel, String[] command, User user, String messageId)
    {
        switch (command[1].toLowerCase())
        {
            case ("start"):
                votesList.put(messageId, new ArrayList<>());
                channel.addReactionById(messageId, "🃏").queue();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run ()
                    {
                        ArrayList<User> votedUsers = new ArrayList<>(votesList.get(messageId));
                        if (votedUsers.size() < 1)
                            channel.sendMessage(Constants.TOO_FEW_PLAYERS).queue();
                        else
                        {
                            channel.sendMessage(getVotedNicknames(messageId)).queue();
                            GameModel cardsModel = new GameModel(votedUsers, channel);
                            cardsModel.makePreparations();
                            cardsModel.startGame(); //Will start main loop for card game in other thread.
                        }
                        votesList.remove(messageId);
                    }
                };
                timer.schedule(task, 10000); //30 seconds
                break;
            case ("help"):
                String path = PathGetter.getTextFolder() + "CardsHelp.txt";
                InfoGetter.showHelp(channel, path);
                break;
            default:
                break;
        }
    }

    @NotNull
    private String getVotedNicknames (String messageId)
    {
        StringBuilder builder = new StringBuilder("Players in game\n");
        votesList.get(messageId).forEach(user -> builder.append(user.getAsTag()).append("\n"));
        return builder.toString();
    }

    public void addUserVoice (TextChannel channel, @NotNull User user, String messageId)
    {
        if (votesList.containsKey(messageId))
        {
            if (votesList.get(messageId).size() < Constants.CARDS_PLAYER_LIMIT)
                if (!activityKeeper.logContains(user))
                {
                    votesList.get(messageId).add(user);
                    activityKeeper.addActivity(user);
                }
                else
                    channel.sendMessage(String.format(Constants.PLAYER_ALREADY_IN_GAME, user.getAsTag(), "cards")).queue();
            else
                channel.sendMessage(Constants.TOO_MANY_PLAYERS).queue();
        }
    }

    public void removeUserVoice (TextChannel channel, @NotNull User user, String messageId)
    {
        if (votesList.containsKey(messageId))
            if (votesList.get(messageId).removeIf(x  -> x.getAsTag().compareTo(user.getAsTag()) == 0))
                activityKeeper.deleteActivity(user);
    }

    @Override
    public boolean checkCommand (String[] command)
    {
        return false;
    }
}