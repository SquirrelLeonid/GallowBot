package tableBot.games.gallows;

public class GallowsModel
{
    private int lives = 10;
    private boolean isEnded = false;

    public GameField gameField;
    private WordStorage wordStorage;

    public GallowsModel ()
    {
        wordStorage = new WordStorage();
        gameField = new GameField(wordStorage.getUsedLetters());
        gameField.drawOpenedLetters(wordStorage.getOpenedWord());
    }

    public void makeMove (char letter)
    {
        if (wordStorage.wordHasLetter(letter))
        {
            wordStorage.openLetters(letter);
        }
        else
        {
            gameField.updateImage();
            wordStorage.openLetters(letter);
            lives--;
        }

        gameField.drawOpenedLetters(wordStorage.getOpenedWord());

        if (wordStorage.isOpened())
        {
            isEnded = true;
            gameField.drawWinMessage();
        }
        if (lives == 0)
        {
            isEnded = true;
            gameField.drawHiddenWord((wordStorage.getHiddenWord()));
        }
        gameField.drawUsedLetters(wordStorage.getUsedLetters());
    }

    public boolean isGameEnded ()
    {
        return isEnded;
    }
}