package tableBot.games.gallows;

import tableBot.PathGetter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesKeeper
{
    private static File[] images = null;
    private static File bottomFill = new File(PathGetter.getImageFolder() + "\\Gallows\\y_BottomFill.jpg");

    public static BufferedImage getImageByIndex (int index)
    {
        if (images == null || images.length == 0)
        {
            File folder = new File(PathGetter.getImageFolder() + File.separator + "Gallows");
            images = folder.listFiles();
        }
        try
        {
            assert images != null;
            return ImageIO.read(images[index]);
        }
        catch (ArrayIndexOutOfBoundsException exception)
        {
            return null;
        }
        catch (IOException exception)
        {
            return null;
        }
    }

    public static BufferedImage getBottomFill ()
    {
        try
        {
            return ImageIO.read(bottomFill);
        }
        catch (IOException exception)
        {
            return null;
        }

    }
}
