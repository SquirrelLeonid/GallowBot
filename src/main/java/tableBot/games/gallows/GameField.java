package tableBot.games.gallows;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

//Only non-static, because each player has him own field
public class GameField
{
    private Font font;
    private Graphics2D graphics;
    private BufferedImage statement;
    private int nextImgIndex;

    public GameField (char[] usedLetters)
    {
        setFont();
        updateImage(); //statement will get first image from images list
        drawUsedLetters(usedLetters);
    }

    //called when the user made a mistake.
    public void updateImage ()
    {
        statement = ImagesKeeper.getImageByIndex(nextImgIndex);
        assert statement != null;
        graphics = statement.createGraphics();
        graphics.setFont(font);
        nextImgIndex++;
    }

    //called when the user has guessed a letter.
    public void drawOpenedLetters (String word)
    {
        //A Bottom rectangle of image
        String formattedWord = formatWord(word);
        String underline = getUnderline(formattedWord);
        int x = 20;
        int y = 340;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(11, 304, 618, 35);
        graphics.setColor(Color.BLACK);
        graphics.drawString(formattedWord, x, y);
        graphics.drawString(underline, x, y + 5);
    }

    public void drawWinMessage ()
    {
        graphics.setColor(Color.BLACK);
        graphics.drawString("You win! Perfect!", 100, 400);
    }

    public void drawHiddenWord (String word)
    {
        String formattedWord = formatWord(word);
        String underline = getUnderline(formattedWord);
        int x = 20;
        int y = 410;
        graphics.setColor(Color.BLACK);
        graphics.drawString(formattedWord, x, y);
        graphics.drawString(underline, x, y + 5);
    }

    //called after any change
    public void drawUsedLetters (@NotNull char[] usedLetters)
    {
        //A top right part of image
        graphics.setColor(Color.white);
        graphics.fillRect(262, 11, 366, 286);
        graphics.setColor(Color.black);
        int x = 272;
        int y = 41;
        for (int i = 0; i < usedLetters.length; i++)
        {
            if (i % 6 == 0)
            {
                x = 272;
                y += 30;
            }
            graphics.drawChars(usedLetters, i, 1, x, y);
            x += 30;
        }
    }

    public byte[] getImage ()
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try
        {
            ImageIO.write(statement, "jpg", output);
            return output.toByteArray();
        } catch (IOException exception)
        {
            return null;
        }
    }

    private String getUnderline (String word)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
        {
            if (i % 2 == 0)
                builder.append("_");
            else
                builder.append(" ");
        }
        return builder.toString();
    }

    private String formatWord (String word)
    {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
            builder.append(word.charAt(i) + " ");
        return builder.toString().trim();
    }

    private void setFont ()
    {
        font = new Font("monospaced", Font.BOLD, 30);
    }
}