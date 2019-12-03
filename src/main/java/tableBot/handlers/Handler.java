package tableBot.handlers;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public interface Handler
{
    void handleCommand (TextChannel channel, String[] command, User user);

    boolean checkCommand (String[] command);
}
