package tableBot.discord;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import tableBot.handlers.CardGameHandler;

public class ReactionsHandler extends ListenerAdapter
{
    private final CardGameHandler cardGameHandler = new CardGameHandler();

    public void onGuildMessageReactionAdd (GuildMessageReactionAddEvent event)
    {
        if (!event.getUser().isBot())
        {
            if (event.getReactionEmote().getEmoji().compareTo("🃏") == 0)
            {
                cardGameHandler.addUserReaction(event.getMessageId(), event.getUser());
            }
            else if (event.getReactionEmote().getEmoji().compareTo(("🎲")) == 0)
                event.getChannel().sendMessage("It's a dices!").queue();
        }
    }
}
