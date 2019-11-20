package tableBot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoGetter
{
    //Game => players tags;
    private static HashMap<String, ArrayList<String>> activities = new HashMap<>();

    public static void addRecord (String userTag, String gameName)
    {
        if (! activities.containsKey(gameName))
            activities.put(gameName, new ArrayList<>());
        activities.get(gameName).add(userTag);
    }

    public static void removeRecord (String userTag, String gameName)
    {
        ArrayList<String> players = activities.get(gameName);
        players.removeIf(element -> element.compareTo(userTag) == 0);
        if (players.size() == 0)
            activities.remove(gameName);
    }

    @NotNull
    public static String showActivities ()
    {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ArrayList<String>> entry : activities.entrySet())
        {
            builder.append(String.format("playing the %s:\n", entry.getKey()));
            entry.getValue().forEach(userTag -> builder.append(userTag).append("\n"));
            builder.append("------------------------\n");
        }
        return builder.length() == 0 ? "There is no activity." : builder.toString().trim();
    }
}
