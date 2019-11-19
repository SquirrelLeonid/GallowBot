package tableBot.games.gallows;

public class GallowsModel
{
    private int lives = 10;
    private boolean isEnded = false;

    public GameField gameField;
    private WordStorage wordStorage;

    public GallowsModel ()
    {
        //Creation is correct
        wordStorage = new WordStorage();
        gameField = new GameField(wordStorage.getUsedLetters());
        gameField.drawOpenedLetters(wordStorage.getOpenedWord());
    }

    public void makeMove (char letter)
    {
        if (wordStorage.wordHasLetter(letter))
        {
            wordStorage.openLetters(letter);
            if (wordStorage.isOpened())
            {
                isEnded = true;
                gameField.drawWinMessage();
            }
        }
        else
        {
            gameField.updateImage();
            lives--;
            if (lives == 0)
            {
                gameField.drawHiddenWord(wordStorage.getHiddenWord());
                isEnded = true;
            }
        }
        gameField.drawOpenedLetters(wordStorage.getOpenedWord());
        gameField.drawUsedLetters(wordStorage.getUsedLetters());
    }

    public boolean isGameEnded ()
    {
        return isEnded;
    }

    public int getLives ()
    {
        return lives;
    }

}
