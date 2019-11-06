package tableBot.games.gallows;

import tableBot.PathGetter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagesKeeper
{
    private static File[] images = null;

    public static BufferedImage getImageByIndex(int index)
    {
        if (images == null || images.length == 0)
        {
            //Pay attention to relative path
            File folder = new File(PathGetter.getImageFolder() + File.separator + "Gallows");
            images = folder.listFiles();
        }
        try
        {
            assert images != null;
            return ImageIO.read(images[index]);
        }
        //TODO return an error image or handle null value in other methods
        catch (ArrayIndexOutOfBoundsException exception)
        {
            return null;
        }
        catch (IOException exception)
        {
            return null;
        }
    }
}
