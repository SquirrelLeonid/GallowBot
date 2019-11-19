package tableBot;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class InfoGetter
{
    //Unsorted records
    private static HashMap<String, String> activities = new HashMap<>();

    public static void addRecord (String userTag, String gameName)
    {
        activities.put(userTag, gameName);
    }

    public static void removeRecord (String userTag)
    {
        activities.remove(userTag);
    }

    @NotNull
    public static String showActivities ()
    {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : activities.entrySet())
        {
            builder.append(getUserTag(entry));
            builder.append(" plays ");
            builder.append(getGameName(entry));
            builder.append("\n");//Возможно \n\r
        }
        return builder.length() == 0 ? "There is no activity." : builder.toString().trim();
    }

    private static String getUserTag (@NotNull Map.Entry<String, String> entry)
    {
        return entry.getKey();
    }

    private static String getGameName (@NotNull Map.Entry<String, String> entry)
    {
        return entry.getValue();
    }

}
