package tableBot.games.gallows;

import java.util.ArrayList;

public class WordStorage
{
    private int length;
    private char[] hiddenWord;
    private char[] openedWord;
    private ArrayList<Character> usedLetters;

    public WordStorage()
    {
        usedLetters = new ArrayList<>();
        hiddenWord = WordGenerator.getWord().toCharArray();
        length = hiddenWord.length;
        openedWord = createOpenedWord();
    }

    public String getHiddenWord()
    {
        return new String(hiddenWord);
    }

    public String getOpenedWord()
    {
        return new String(openedWord);
    }

    public void getUsedLetters()
    {
        //TODO return list of used letters.
    }

    public void openLetters(char letter)
    {
        for (int i = 0; i < length; i++)
            if (hiddenWord[i] == letter)
                openedWord[i] = letter;
    }

    public boolean isOpenedWord()
    {
        for (int i = 0; i < length; i++)
        {
            if (hiddenWord[i] != openedWord[i])
                return false;
        }
        return true;
    }

    public void hasLetter(char letter)
    {
        //TODO check for letter in hidden word
    }

    private char[] createOpenedWord()
    {
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            builder.append('?');
        return builder.toString().toCharArray();
    }
}
