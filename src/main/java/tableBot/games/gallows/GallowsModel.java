package tableBot.games.gallows;

public class GallowsModel
{
    public GameField gameField;

    private int lifes = 10;
    private WordStorage wordStorage;

    public GallowsModel()
    {
        //Creation is correct
        wordStorage = new WordStorage();
        gameField = new GameField();
        gameField.drawOpenedLetters(wordStorage.getOpenedWord());
    }

    public void makeMove(String letter)
    {
        //TODO check that hidden word contains this letter
        /*TODO if true
                    1) open all of the same letters in word
                    2) add letter to used letters
                    3) update bottom and top-right rectangles
                    4) if word is full opened
                            return true
                       else
                            return false
                else
                    1) add letter to used letters
                    2) update gallows and top-right rectangle
                    3) Decrease lifes count
                    4) if life count equal 0
                            end of game
         */
    }

}
