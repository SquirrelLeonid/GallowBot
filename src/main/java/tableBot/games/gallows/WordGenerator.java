package tableBot.games.gallows;

import java.io.*;
import java.util.*;
import tableBot.PathGetter;

public class WordGenerator
{
    private static final int lengthLimit = 15;
    private static int length;
    private static ArrayList<String> wordList = new ArrayList<>();

    public static String getWord()
    {
        if (wordList.isEmpty())
        {
            try
            {
                createWordList();
            }
            catch (IOException exception)
            {
                return "exception";
            }
        }
        Random random = new Random();
        int number = random.nextInt(length);
        return wordList.get(number).toLowerCase();
    }

    private static void createWordList() throws IOException
    {

        BufferedReader bufferedReader = getReader();
        String line = bufferedReader.readLine();
        while (line != null)
        {
            if (line.length() <= lengthLimit)
                wordList.add(line);
            line = bufferedReader.readLine();
        }
        length = wordList.size();
    }

    private static BufferedReader getReader() throws FileNotFoundException
    {
        File file = new File(PathGetter.getTextFolder() +  "Word list.txt");
        String path = file.getAbsolutePath();
        FileReader fileReader = new FileReader(path);
        return new BufferedReader(fileReader);
    }
}
