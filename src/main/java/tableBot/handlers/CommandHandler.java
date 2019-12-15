package tableBot.handlers;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import tableBot.InfoGetter;
import tableBot.PathGetter;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class CommandHandler extends ListenerAdapter
{
    private final static String prefix = "-";

    public void onGuildMessageReceived (GuildMessageReceivedEvent event)
    {
        handleCommand(event.getChannel(), event.getMessage(), event.getAuthor());
    }

    public void onGuildMessageUpdate (GuildMessageUpdateEvent event)
    {
        handleCommand(event.getChannel(), event.getMessage(), event.getAuthor());
    }

    private void handleCommand (TextChannel channel, Message message, User user)
    {
        String[] command = message.getContentRaw().split("\\s+");
        switch (command[0].toLowerCase())
        {
            case (prefix + "help"):
                String path = PathGetter.getTextFolder() + "Help.txt";
                InfoGetter.showHelp(channel, path);
                break;
            case (prefix + "gallows"):
                HandlersKeeper.gallowsHandler.handleCommand(channel, command, user);
                break;
            case (prefix + "cards"):
                HandlersKeeper.cardGameHandler.handleCommand(channel, command, user);
                break;
            case (prefix + "throw"):
                HandlersKeeper.diceRollHandler.handleCommand(channel, command, user);
                break;
            case (prefix + "activity"):
                channel.sendMessage(InfoGetter.showActivities()).queue();
                break;
            default:
                break;
        }
    }
}