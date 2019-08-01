package simplerpg.model;

import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class MapIcon
{
    private final int ID;
    private final String NAME;
    private final String FILENAME;
    private final String CATEGORY;
    private final boolean TRAVERSABLE;
    private final ImageIcon ICON;

    public MapIcon(int id, String name, String filename, String category, boolean traversable)
    {
        ID = id;
        NAME = name;
        FILENAME = filename;
        CATEGORY = category;
        TRAVERSABLE = traversable;

        ICON = setIcon();
    }

    public int getID()
    {
        return ID;
    }

    public boolean isTRAVERSABLE()
    {
        return TRAVERSABLE;
    }

    public String getNAME()
    {
        return NAME;
    }

    public String getFILENAME()
    {
        return FILENAME;
    }

    public String getCATEGORY()
    {
        return CATEGORY;
    }

    public ImageIcon getICON()
    {
        return ICON;
    }

    private ImageIcon setIcon()
    {
        String path = "img/" + CATEGORY + "/" + FILENAME;

        try
        {
            URL imgUrl = getClass().getClassLoader().getResource(path);

            if (imgUrl != null)
            {
                return new ImageIcon(ImageIO.read(imgUrl));
            }
            else
            {
                throw new IOException();
            }
        }
        catch (IOException e)
        {
            Logger.error("Error reading file " + FILENAME);
            e.printStackTrace();
            System.exit(1);
        }

        throw new IllegalStateException("Could not read file");
    }
}
