package tableBot.handlers;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ReactionsHandler extends ListenerAdapter
{

    public void onGuildMessageReactionAdd (GuildMessageReactionAddEvent event)
    {
        if (!event.getUser().isBot())
        {
            if (event.getReactionEmote().getEmoji().compareTo("🃏") == 0)
            {
                HandlersKeeper.cardGameHandler.addUserReaction(event.getMessageId(), event.getUser());
            }
            else if (event.getReactionEmote().getEmoji().compareTo(("🎲")) == 0)
                event.getChannel().sendMessage("It's a dices!").queue();
        }
    }
}
