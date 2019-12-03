package tableBot.games.gallows;

import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class GameField extends JPanel
{
    private Font font;
    private Graphics2D graphics;
    private BufferedImage statement;
    private int nextImgIndex;

    public GameField (char[] usedLetters)
    {
        setFont();
        updateImage();
        drawUsedLetters(usedLetters);
    }

    public void updateImage ()
    {
        statement = ImagesKeeper.getImageByIndex(nextImgIndex);
        assert statement != null;
        graphics = statement.createGraphics();
        graphics.setFont(font);
        nextImgIndex++;
    }

    public void drawOpenedLetters (String word)
    {
        String formattedWord = formatWord(word);
        String underline = getUnderline(formattedWord);
        int x = 5;
        int y = 295;
        graphics.drawImage(ImagesKeeper.getBottomFill(), 0, 270, this);
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
        int x = 5;
        int y = 375;
        graphics.setColor(Color.BLACK);
        graphics.drawString(formattedWord, x, y);
        graphics.drawString(underline, x, y + 5);
    }

    public void drawUsedLetters (@NotNull char[] usedLetters)
    {
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
        font = new Font("Monospaced", Font.BOLD, 30);
    }
}