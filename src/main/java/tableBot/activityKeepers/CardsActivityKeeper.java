package tableBot.activityKeepers;

import net.dv8tion.jda.api.entities.User;

public class CardsActivityKeeper implements ActivityKeeper
{

    @Override
    public void addActivity (User user)
    {

    }

    @Override
    public boolean deleteActivity (User user)
    {
        return false;
    }

    @Override
    public boolean logContains (User user)
    {
        return false;
    }
}
