package tableBot.handlers;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReactionsHandler extends ListenerAdapter
{

    public void onGuildMessageReactionAdd (@NotNull GuildMessageReactionAddEvent event)
    {
        if (!event.getUser().isBot())
        {
            if (event.getReactionEmote().getEmoji().compareTo("🃏") == 0)
                HandlersKeeper.cardsHandler.addUserVoice(event.getChannel(), event.getUser(), event.getMessageId());
        }
    }

    public void onGuildMessageReactionRemove (@NotNull GuildMessageReactionRemoveEvent event)
    {
        if (!event.getUser().isBot())
        {
            if (event.getReactionEmote().getEmoji().compareTo("🃏") == 0)
                HandlersKeeper.cardsHandler.removeUserVoice(event.getChannel(), event.getUser(), event.getMessageId());
        }
    }
}
