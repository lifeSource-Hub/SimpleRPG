package simplerpg.model;

import org.pmw.tinylog.Logger;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;
import java.net.URL;

public class MapPlot
{
    private final int ID;
    private final String NAME;
    private final String FILENAME;
    private final String CATEGORY;
    private final boolean TRAVERSABLE;
    private final String TRAVERSAL_TEXT;
    private final ImageIcon ICON;

    public MapPlot(int id, String name, String filename, String category, boolean traversable, String traversalText)
    {
        ID = id;
        NAME = name;
        FILENAME = filename;
        CATEGORY = category;
        TRAVERSABLE = traversable;
        TRAVERSAL_TEXT = traversalText;

        ICON = setIcon();
    }

    public int getID()
    {
        return ID;
    }

    public boolean isTraversable()
    {
        return TRAVERSABLE;
    }

    public String getName()
    {
        return NAME;
    }

    public String getFilename()
    {
        return FILENAME;
    }

    public String getCategory()
    {
        return CATEGORY;
    }

    public String getTraversalText()
    {
        return TRAVERSAL_TEXT;
    }

    public ImageIcon getIcon()
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
            Logger.error("Error reading file \"" + FILENAME + "\"");
            e.printStackTrace();
            System.exit(1);
        }

        throw new IllegalStateException("Could not read file");
    }
}
