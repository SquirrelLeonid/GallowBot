package tableBot.games.cards;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

public class Player
{
    private User user;
    private int score;

    public Player (User user)
    {
        this.user = user;
    }

    public void takeCard (@NotNull Card card)
    {
        score += card.getCardValue();
        user.openPrivateChannel().queue(channel -> channel.sendMessage("You took this card").addFile(card.getImage(), "card.png").queue());
    }

    public String getAsTag ()
    {
        return user.getAsTag();
    }
}
