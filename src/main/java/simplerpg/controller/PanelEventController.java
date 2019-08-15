package simplerpg.controller;

import simplerpg.view.InputPanel;
import simplerpg.view.MapPanel;
import simplerpg.view.NewCharacterPanel;
import simplerpg.view.TextPanel;

public class PanelEventController
{
    private MapPanel mapPnl;
    private TextPanel textPnl;

    public void setMapPnl(MapPanel mapPnl)
    {
        this.mapPnl = mapPnl;
    }

    public void setTextPnl(TextPanel textPnl)
    {
        this.textPnl = textPnl;
    }

    public void handleMoveCommand(int rowMovement, int colMovement)
    {
        mapPnl.movePlayer(rowMovement, colMovement);
    }

    public void handleRestCommand()
    {
        // TODO finish method
    }
}
