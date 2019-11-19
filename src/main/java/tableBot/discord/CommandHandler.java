package tableBot.discord;

import org.jetbrains.annotations.NotNull;

import tableBot.InfoGetter;
import tableBot.handlers.GallowsHandler;
import tableBot.handlers.CardGameHandler;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHandler extends ListenerAdapter
{
    private final String prefix = "-";
    private final GallowsHandler gallowsHandler = new GallowsHandler();
    private final CardGameHandler cardGameHandler = new CardGameHandler();

    public void onGuildMessageReceived (GuildMessageReceivedEvent event)
    {
        String[] message = event.getMessage().getContentRaw().split("\\s+");
        switch (message[0].toLowerCase())
        {
            case (prefix + "help"):
                event.getChannel().sendMessage("I do nothing").queue();
                break;
            case (prefix + "gallows"):
                gallowsHandler.handleCommand(event, message);
                break;
            case (prefix + "twenty-one"):
                cardGameHandler.handleCommand(event, message);
                break;
            case (prefix + "activities"):
                event.getChannel().sendMessage(InfoGetter.showActivities()).queue();
                break;
            default:
                break;
        }
    }

    public void onGuildMessageUpdate (@NotNull GuildMessageUpdateEvent event)
    {
        GuildMessageReceivedEvent e = new GuildMessageReceivedEvent(event.getJDA(), event.getResponseNumber(), event.getMessage());
        onGuildMessageReceived(e);
    }
}
