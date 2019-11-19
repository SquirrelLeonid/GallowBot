package tableBot.handlers;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Handler
{
    default void handleCommand (TextChannel channel, String[] command, String userTag)
    {
        switch (command[1].toLowerCase())
        {
            case ("start"):
                //TODO: create a game
                break;
            case ("help"):
                //TODO: send help info about THIS game in the chat
                break;
            case ("stop"):
                //TODO: stop a game
                break;
            default:
                break;
        }
    }

    boolean checkCommand (String[] command);
}
