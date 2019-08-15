package simplerpg;

import org.junit.Assert;
import org.junit.Test;
import simplerpg.controller.MapGrid;

public class TestMapGrid
{
    @Test
    public void testMapGridConstructor()
    {
        MapGrid mapGrid = new MapGrid();

        Assert.assertNotNull(mapGrid);
    }
}