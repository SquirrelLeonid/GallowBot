package tableBot.activityKeepers;

import net.dv8tion.jda.api.entities.User;
import tableBot.InfoGetter;
import tableBot.games.gallows.GallowsModel;

import java.util.HashMap;
import java.util.Map;

public class GallowsActivityKeeper implements ActivityKeeper
{
    private HashMap<User, GallowsModel> activityLog;

    public GallowsActivityKeeper ()
    {
        activityLog = new HashMap<>();
    }

    public GallowsModel getActivity (User user)
    {
        return activityLog.get(user);
    }

    @Override
    public void addActivity (User user)
    {
        activityLog.put(user, new GallowsModel());
        InfoGetter.addRecord("gallows", user);
    }

    @Override
    public boolean deleteActivity (User user)
    {
        for (Map.Entry<User, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getAsTag().compareTo(user.getAsTag()) == 0)
            {
                activityLog.remove(entry.getKey());
                InfoGetter.removeRecord("gallows", user);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean logContains (User user)
    {
        for (Map.Entry<User, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getAsTag().compareTo(user.getAsTag()) == 0)
                return true;
        }
        return false;
    }

    @Override
    public User getUser (String userTag)
    {
        for (Map.Entry<User, GallowsModel> entry : activityLog.entrySet())
        {
            if (entry.getKey().getAsTag().compareTo(userTag) == 0)
                return entry.getKey();
        }

        return null;
    }
}
