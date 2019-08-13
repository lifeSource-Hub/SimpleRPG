package simplerpg.controller;

import java.util.EventObject;

public class UserCmdEvent extends EventObject
{
    private String payload;
    private int rowMovement;
    private int colMovement;

    public UserCmdEvent(Object source)
    {
        super(source);
    }

    public UserCmdEvent(Object source, String payload)
    {
        this(source, payload, 0, 0);
    }

    public UserCmdEvent(Object source, int rowMovement, int colMovement)
    {
        this(source, "", rowMovement, colMovement);
    }

    public UserCmdEvent(Object source, String payload, int rowMovement, int colMovement)
    {
        super(source);
        this.payload = payload;
        this.rowMovement = rowMovement;
        this.colMovement = colMovement;
    }

    public String getPayload()
    {
        return payload;
    }

    public int getRowMovement()
    {
        return rowMovement;
    }

    public int getColMovement()
    {
        return colMovement;
    }

    @Override
    public Object getSource()
    {
        return super.getSource();
    }

    @Override
    public String toString()
    {
        return super.toString();
    }
}
