package simplerpg.controller;

import simplerpg.view.InputPanel;
import simplerpg.view.MapPanel;
import simplerpg.view.NewCharacterPanel;

public class PanelEventController
{
    private MapPanel mapPnl;
    private InputPanel inputPnl;
    private NewCharacterPanel newCharacterPnl;

    public void setMapPnl(MapPanel mapPnl)
    {
        this.mapPnl = mapPnl;
    }

    public void setInputPnl(InputPanel inputPnl)
    {
        this.inputPnl = inputPnl;
    }

    public void setNewCharacterPnl(NewCharacterPanel newCharacterPnl)
    {
        this.newCharacterPnl = newCharacterPnl;
    }

    public void handleMoveCommand(int rowMovement, int colMovement)
    {
        mapPnl.movePlayer(rowMovement, colMovement);
    }
}
