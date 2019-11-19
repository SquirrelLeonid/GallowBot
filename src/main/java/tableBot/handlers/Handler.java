package tableBot.handlers;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Handler
{
    default void handleCommand (GuildMessageReceivedEvent event, String[] message)
    {
        String userTag = event.getAuthor().getAsTag();
        switch (message[1].toLowerCase())
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
