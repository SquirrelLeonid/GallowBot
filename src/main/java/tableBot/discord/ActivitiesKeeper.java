package tableBot.discord;

import tableBot.games.Activity;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ActivitiesKeeper
{
    private HashMap<String, Activity> Activities;

    public ActivitiesKeeper ()
    {
        Activities = new HashMap<>();
    }

    public void addActivity (String username, Activity game)
    {
        Activities.put(username, game);
    }

    public void deleteActivity (String username)
    {
        Activities.remove(username);
    }

    public boolean userHasActivity (String username)
    {
        return Activities.containsKey(username);
    }

    public Activity getActivity(String username)
    {
        return Activities.get(username);
    }

    public String showActivities()
    {
        return convertToString();
    }

    @NotNull
    private String convertToString()
    {
        StringBuilder activities = new StringBuilder();
        for (Map.Entry<String, Activity> entry: Activities.entrySet())
        {
            activities.append(entry.getKey());
            activities.append(" plays the ");
            activities.append(entry.getValue().getGameName());
            activities.append("\n\r");
        }

        return  activities.length() == 0 ? "There are no active actions" : activities.toString();
    }
}
