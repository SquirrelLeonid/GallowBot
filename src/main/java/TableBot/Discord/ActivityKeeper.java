package TableBot.Discord;

import TableBot.Games.GameModels;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class ActivityKeeper
{
    private HashMap<String, GameModels> Activities;

    public ActivityKeeper ()
    {
        Activities = new HashMap<>();
    }

    public void addActivity (String username, GameModels game)
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

    public String showActivities()
    {
        return convertToString();
    }

    @NotNull
    private String convertToString()
    {
        StringBuilder activities = new StringBuilder();
        for (Map.Entry<String, GameModels> entry: Activities.entrySet())
        {
            activities.append(entry.getKey());
            activities.append(" plays the ");
            activities.append(entry.getValue().getGameName());
            activities.append("\n\r");
        }

        return  activities.length() == 0 ? "There are no active actions" : activities.toString();
    }



}
