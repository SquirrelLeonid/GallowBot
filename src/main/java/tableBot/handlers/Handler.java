package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;

public interface Handler
{
    void handleCommand (TextChannel channel, String[] command, String userTag);

    boolean checkCommand (String[] command);
}
