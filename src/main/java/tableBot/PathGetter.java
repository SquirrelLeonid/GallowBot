package tableBot;

import java.io.File;

public class PathGetter
{
    private static String separator = File.separator;

    public static String getTextFolder()
    {
        return "Resources" + separator + "TextPatterns" + separator;
    }

    public static String getImageFolder()
    {
        return "Resources" + separator + "ImagePatterns" + separator;
    }
}
