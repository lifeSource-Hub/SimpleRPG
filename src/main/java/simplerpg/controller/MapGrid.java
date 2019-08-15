/*
 * Icons made by Freepik from https://www.flaticon.com
 */

package simplerpg.controller;

import org.pmw.tinylog.Logger;
import simplerpg.model.MapPlot;
import simplerpg.view.TextPanel;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MapGrid
{
    private final String URL = "jdbc:sqlite::resource:simplerpg.db";
    private final String TABLE = "mapimagefile";

    private final String SEED_FILENAME = "Map.txt";

    private ArrayList<ArrayList<MapPlot>> gridRow;
    private ArrayList<MapPlot> gridCol;
    private int gridRowCount;
    private int gridColCount;

    public MapGrid()
    {
        gridRow = new ArrayList<>();
        gridCol = new ArrayList<>();

        scanGridSeedFile();

        gridRowCount = gridRow.size();
        gridColCount = gridRow.get(0).size();
    }

    // Initialize ArrayLists with values scanned from seed file
    private void scanGridSeedFile()
    {
        try
        {
            Scanner scan;
            InputStream stream = getClass().getClassLoader().getResourceAsStream(SEED_FILENAME);

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

                    initializeGrid(seed);
                }

                gridRow.add(gridCol);
                gridCol = new ArrayList<>();
            }
        }
        catch (IOException e)
        {
            Logger.error("Error reading file \"" + SEED_FILENAME + "\"");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void initializeGrid(int seed)
    {
        String query = "SELECT * FROM " + TABLE
                + " WHERE imageID IN(" + seed + ");";

        try (Connection conn = DriverManager.getConnection(URL))
        {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                int id = resultSet.getInt("imageID");
                String name = resultSet.getString("name");
                String filename = resultSet.getString("filename");
                String category = resultSet.getString("terrainType");
                boolean traversable = resultSet.getBoolean("traversable");
                String traversalText = resultSet.getString("traversalText");

                gridCol.add(new MapPlot(id, name, filename, category, traversable, traversalText));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public ImageIcon getMapPlotIcon(int row, int col)
    {
        // return gridRow.get(y).get(x);
        return gridRow.get(row).get(col).getIcon();
    }

    public int getGridRowCount()
    {
        return gridRowCount;
    }

    public int getGridColCount()
    {
        return gridColCount;
    }

    public boolean isPlotWithinBounds(int row, int col)
    {
        return (row >= 0) && (row < gridRowCount) && (col >= 0) && (col < gridColCount);
    }

    public boolean isPlotTraversable(int row, int col)
    {
        return gridRow.get(row).get(col).isTraversable();
    }

    public String getPlotTraversalText(int row, int col)
    {
        return gridRow.get(row).get(col).getTraversalText();
    }
}
