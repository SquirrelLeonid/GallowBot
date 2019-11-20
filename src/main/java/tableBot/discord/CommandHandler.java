package tableBot.discord;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
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
        handleCommand(event.getChannel(), event.getMessage(), event.getAuthor().getAsTag());
    }

    public void onGuildMessageUpdate (GuildMessageUpdateEvent event)
    {
        handleCommand(event.getChannel(), event.getMessage(), event.getAuthor().getAsTag());
    }

    private void handleCommand (TextChannel channel, Message message, String userTag)
    {
        String[] command = message.getContentRaw().split("\\s+");
        switch (command[0].toLowerCase())
        {
            case (prefix + "help"):
                channel.sendMessage("I do nothing").queue();
                break;
            case (prefix + "gallows"):
                gallowsHandler.handleCommand(channel, command, userTag);
                break;
            case (prefix + "cards"):
                cardGameHandler.handleCommand(channel, command, userTag);
                break;
            case (prefix + "activity"):
                channel.sendMessage(InfoGetter.showActivities()).queue();
                break;
            default:
                break;
        }
    }
}