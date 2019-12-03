package tableBot;

import net.dv8tion.jda.api.entities.User;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InfoGetter
{
    //Game => players tags;
    private static HashMap<String, ArrayList<User>> activities = new HashMap<>();

    public static void addRecord (String gameName, User user)
    {
        if (! activities.containsKey(gameName))
            activities.put(gameName, new ArrayList<>());
        activities.get(gameName).add(user);
    }

    public static void removeRecord (String gameName, User user)
    {
        ArrayList<User> users = activities.get(gameName);
        users.removeIf(element -> element.getAsTag().compareTo(user.getAsTag()) == 0);
        if (users.size() == 0)
            activities.remove(gameName);
    }

    @NotNull
    public static String showActivities ()
    {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, ArrayList<User>> entry : activities.entrySet())
        {
            builder.append(String.format("playing the %s:\n", entry.getKey()));
            entry.getValue().forEach(user-> builder.append(user.getAsTag()).append("\n"));
            builder.append("------------------------\n");
        }
        return builder.length() == 0 ? "There is no activity." : builder.toString().trim();
    }
}
