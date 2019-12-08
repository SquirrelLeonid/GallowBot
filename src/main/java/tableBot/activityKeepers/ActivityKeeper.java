package tableBot.activityKeepers;

import net.dv8tion.jda.api.entities.User;

public interface ActivityKeeper
{
    void addActivity (User user);

    boolean deleteActivity (User user);

    boolean logContains (User user);
}
