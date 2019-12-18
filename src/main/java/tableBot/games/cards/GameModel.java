package tableBot.games.cards;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class GameModel
{
    private ScheduledExecutorService executor;
    private ArrayList<Player> activePlayers;
    private ArrayList<Card> deck;
    private TextChannel channel;
    private Runnable gameTask;


    public GameModel (ArrayList<User> votedUsers, TextChannel ch)
    {
        executor = Executors.newScheduledThreadPool(1);
        activePlayers = convertToPlayers(votedUsers);
        channel = ch;
        gameTask = createTask();
    }

    public void makePreparations ()
    {
        deck = Deck.getDeck();
        shuffleDeck();
    }

    public void startGame ()
    {
        activePlayers.forEach(this::giveCard);
        channel.sendMessage("Who will take next card?").queue(ch -> ch.addReaction("✅").queue());
    }

    private Runnable createTask()
    {
        return new Runnable() {
            @Override
            public void run ()
            {

            }
        };
    }

    private ArrayList<Player> takeVote()
    {
        //TODO: vote
        return null;
    }

    private void giveCard (@NotNull Player player)
    {
        Card card = deck.remove(deck.size() - 1);
        player.takeCard(card);
    }

    private void shuffleDeck ()
    {
        Collections.shuffle(deck);
    }

    @NotNull
    private ArrayList<Player> convertToPlayers (@NotNull ArrayList<User> votedUsers)
    {
        ArrayList<Player> playerList = new ArrayList<>();
        votedUsers.forEach(user -> playerList.add(new Player(user)));
        return playerList;
    }
}