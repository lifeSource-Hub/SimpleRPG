package simplerpg.view;

import org.pmw.tinylog.Logger;
import simplerpg.controller.MapGrid;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class MapPanel extends JPanel
{
    private final String PLAYER_ICON_PATH = "img/people/player.png";
    private final ImageIcon PLAYER_ICON;
    private int plyrRowPos;
    private int plyrColPos;
    private int lblRowCount;
    private int lblColCount;
    private JLabel[][] lblMap;
    private MapGrid mapGrid;

    public MapPanel()
    {
        this(13, 8);
    }

    public MapPanel(int plyrStartRow, int plyrStartCol)
    {
        plyrRowPos = plyrStartRow;
        plyrColPos = plyrStartCol;

        mapGrid = new MapGrid();
        lblRowCount = mapGrid.getGridSizeY();
        lblColCount = mapGrid.getGridSizeX();
        lblMap = new JLabel[lblRowCount][lblColCount];

        PLAYER_ICON = setPlyrIcon();

        setLayout(new GridLayout(lblRowCount, lblColCount));
        setBackground(new Color(173, 173, 173));

        for (int i = 0; i < lblRowCount; i++)
        {
            for (int j = 0; j < lblColCount; j++)
            {
                lblMap[i][j] = new JLabel();
                add(lblMap[i][j]);
                // For testing - lblMap[i][j].setPreferredSize(new Dimension(20, 20));
            }
        }

        initializeGrid();
        // Logger.debug("MapPanel rows: " + lblRowCount + "  columns: " + lblColCount + "\n");
    }

    private void initializeGrid()
    {
        for (int i = 0; i < lblRowCount; i++)
        {
            for (int j = 0; j < lblColCount; j++)
            {
                lblMap[i][j].setIcon(mapGrid.getIcon(i, j));
            }
        }

        lblMap[plyrRowPos][plyrColPos].setLayout(new BorderLayout());
        lblMap[plyrRowPos][plyrColPos].add(new JLabel(PLAYER_ICON));
        // Logger.debug("MapPanel initialized");
    }

    public void movePlayer(int rowMove, int colMove)
    {
        int newRowPos = plyrRowPos + rowMove;
        int newColPos = plyrColPos + colMove;

        if (mapGrid.isTraversable(newRowPos, newColPos))
        {
            Logger.debug("CURRENT: row " + plyrRowPos + ", column " + plyrColPos
                    + " — NEW: row " + newRowPos + ", column " + newColPos);

            redisplayGrid(newRowPos, newColPos);
            plyrRowPos = newRowPos;
            plyrColPos = newColPos;
        }
    }

    private void redisplayGrid(int newRow, int newCol)
    {
        lblMap[plyrRowPos][plyrColPos].removeAll();
        lblMap[plyrRowPos][plyrColPos].setIcon(mapGrid.getIcon(plyrRowPos, plyrColPos));

        lblMap[newRow][newCol].setLayout(new BorderLayout());
        lblMap[newRow][newCol].add(new JLabel(PLAYER_ICON));

        revalidate();
        repaint();
    }

    private void wipeGrid()
    {
        for (int i = 0; i < lblRowCount; i++)
        {
            for (int j = 0; j < lblColCount; j++)
            {
                lblMap[i][j].setIcon(null);
            }
        }
        Logger.debug("MapPanel wiped");
    }

    private ImageIcon setPlyrIcon()
    {
        try
        {
            URL imgUrl = getClass().getClassLoader().getResource(PLAYER_ICON_PATH);

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
            Logger.error("Error reading " + PLAYER_ICON_PATH);
            e.printStackTrace();
            System.exit(1);
        }

        throw new IllegalStateException("Could not read file");
    }

    // /**
    //  * Print size of all label icons to console. For debugging only.
    //  */
    // public void debugPrintMapLblSize()
    // {
    //     // Logger.debug("Icon[0][0] width: " + lblMap[0][0].getWidth() + ",  height: " + lblMap[0][0].getHeight());
    //     // Logger.debug("Icon[0][1] width: " + lblMap[0][1].getWidth() + ",  height: " + lblMap[0][1].getHeight());
    //
    //     for (JLabel[] jLabels : lblMap)
    //     {
    //         for (JLabel jLabel : jLabels)
    //         {
    //             Logger.debug("(" + jLabel.getWidth() + ", " + jLabel.getIcon().getIconWidth() + "), ");
    //         }
    //
    //         Logger.debug("\n");
    //     }
    //
    //     Logger.debug("\n");
    // }
}
