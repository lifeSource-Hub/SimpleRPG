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

// For reference
/*
    @Test
    public void testCellConstructor()
    {
        LifeCell lifeCell = null;

        lifeCell = new LifeCell();
        Assert.assertNotNull(lifeCell);
    }

    @Test
    public void testIsAlive()
    {
        LifeCell lifeCell = null;

        try
        {
            lifeCell = new LifeCell(true, 0);
            Logger.info(lifeCell.getIsAlive());

            Assert.assertTrue(lifeCell.getIsAlive());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }       lifeCell.regenerate();
            Assert.assertTrue(lifeCell.getIsAlive());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
*/