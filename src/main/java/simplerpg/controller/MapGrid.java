/*
 * Icons made by Freepik from https://www.flaticon.com
 */

package simplerpg.controller;

import org.pmw.tinylog.Logger;
import simplerpg.model.MapIcon;
import simplerpg.view.TextPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

// TODO replace hard-coded filename
public class MapGrid
{
    private ArrayList<ArrayList<MapIcon>> gridY;
    private ArrayList<MapIcon> gridX;
    private int gridSizeY;
    private int gridSizeX;

    private final String URL = "jdbc:sqlite::resource:simplerpg.db";
    private final String TABLE = "mapicon";

    public MapGrid()
    {
        gridY = new ArrayList<>();
        gridX = new ArrayList<>();

        initializeGrid();

        gridSizeY = gridY.size();
        gridSizeX = gridY.get(0).size();
    }

    // Initialize ArrayLists with values scanned from map text file
    private void initializeGrid()
    {
        try
        {
            Scanner scan;
            InputStream stream = getClass().getClassLoader().getResourceAsStream("Map.txt");

            if (stream != null)
            {
                scan = new Scanner(stream);
            }
            else
            {
                throw new IOException();
            }

            while (scan.hasNextLine())
            {
                Scanner scan2 = new Scanner(scan.nextLine());

                while (scan2.hasNext())
                {
                    int seed = scan2.nextInt();

                    queryIcon(seed);
                }

                gridY.add(gridX);
                gridX = new ArrayList<>();
            }
        }
        catch (IOException e)
        {
            Logger.error("Error reading file "); // TODO add filename
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void queryIcon(int seed)
    {
        String query = "SELECT * FROM " + TABLE
                + " WHERE mapIconID IN(" + seed + ");";

        try (Connection conn = DriverManager.getConnection(URL))
        {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int id = resultSet.getInt("mapIconID");
                String name = resultSet.getString("name");
                String filename = resultSet.getString("filename");
                String category = resultSet.getString("category");
                boolean traversable = resultSet.getBoolean("traversable");

                gridX.add(new MapIcon(id, name, filename, category, traversable));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public ImageIcon getIcon(int y, int x)
    {
        // return gridY.get(y).get(x);
        return gridY.get(y).get(x).getICON();
    }

    public int getGridSizeY()
    {
        return gridSizeY;
    }

    public int getGridSizeX()
    {
        return gridSizeX;
    }

    public boolean isTraversable(int y, int x)
    {
        if (y >= 0 && y < gridSizeY && x >= 0 && x < gridSizeX)
        {
            if (gridY.get(y).get(x).isTRAVERSABLE())
            {
                TextPanel.setText("");
                return true;
            }
            else
            {
                TextPanel.displayUntraversableMsg(gridY.get(y).get(x).getID());
            }
        }
        else
        {
            TextPanel.setText("You can't leave the area until you've completed your mission.");
        }

        return false;
    }

    /**
     * Print ASCII representation of map to console. For debugging only.
     */
    public void printMapGrid()
    {
        Logger.debug("\n");

        for (ArrayList<MapIcon> y : gridY)
        {
            for (MapIcon mapIcon : y)
            {
                Logger.debug(mapIcon.getID() + " ");
            }

            Logger.debug("\n");
        }

        Logger.debug("\n");
    }
}
