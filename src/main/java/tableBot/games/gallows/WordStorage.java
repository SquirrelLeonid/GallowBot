package tableBot.games.gallows;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class WordStorage
{
    private int length;
    private char[] hiddenWord;
    private char[] openedWord;
    private ArrayList<Character> usedLetters;

    public WordStorage ()
    {
        usedLetters = new ArrayList<>();
        hiddenWord = WordGenerator.getWord().toCharArray();
        length = hiddenWord.length;
        openedWord = createOpenedWord();
    }

    public String getHiddenWord ()
    {
        return new String(hiddenWord);
    }

    public String getOpenedWord ()
    {
        return new String(openedWord);
    }

    public char[] getUsedLetters ()
    {
        char[] result = new char[usedLetters.size()];
        for (int i = 0; i < result.length; i++)
            result[i] = usedLetters.get(i);
        return result;
    }

    public void openLetters (char letter)
    {
        for (int i = 0; i < length; i++)
            if (letter == hiddenWord[i])
                openedWord[i] = letter;
    }

    public boolean isOpened ()
    {
        for (int i = 0; i < length; i++)
        {
            if (hiddenWord[i] != openedWord[i])
                return false;
        }
        return true;
    }

    public boolean wordHasLetter (char letter)
    {
        if (usedLetters.contains(letter))
            return false;
        usedLetters.add(letter);
        for (char element : hiddenWord)
        {
            if (letter == element)
                return true;
        }
        return false;
    }

    @NotNull
    private char[] createOpenedWord ()
    {
        StringBuilder builder = new StringBuilder(length);
        builder.append(hiddenWord[0]);
        usedLetters.add(hiddenWord[0]);
        for (int i = 0; i < length - 1; i++)
            builder.append('?');
        return builder.toString().toCharArray();
    }
}
