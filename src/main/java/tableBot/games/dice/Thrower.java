package tableBot.games.dice;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Random;

public class Thrower
{
    private static Random random = new Random();

    @NotNull
    public static String throwCoin ()
    {
        return random.nextInt(2) == 0 ?  "obverse" : "reverse";
    }

    @NotNull
    public static String throwDice (int diceCount, int SidesCount)
    {
        int[] result = new int[diceCount];
        int total = 0;
        for (int i = 0; i < diceCount; i++)
        {
            result[i] = random.nextInt(SidesCount) + 1;
            total+=result[i];
        }
        Arrays.sort(result);
        return Arrays.toString(result) + "\nTotal is: " + total;
    }
}
