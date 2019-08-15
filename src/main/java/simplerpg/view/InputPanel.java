package simplerpg.view;

import org.pmw.tinylog.Logger;
import simplerpg.controller.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class InputPanel extends JPanel
{
    private PanelEventController CONTROLLER;
    // private final PanelEventController CONTROLLER;


    public void setController(PanelEventController controller)
    {
        this.CONTROLLER = controller;
    }

    InputPanel()//PanelEventController controller)
    {
        // this.CONTROLLER = controller;

        final Border ETCH_BORDER = BorderFactory.createEtchedBorder(1);
        final Border EMPTY_PNL_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(ETCH_BORDER, EMPTY_PNL_BORDER));

        JPanel movementPanel = new JPanel();
        JPanel btnPanel = new JPanel();

        JButton northBtn = new JButton(MovementCommand.NORTH.getLabel());
        JButton westBtn = new JButton(MovementCommand.WEST.getLabel());
        JButton eastBtn = new JButton(MovementCommand.EAST.getLabel());
        JButton southBtn = new JButton(MovementCommand.SOUTH.getLabel());
        JButton restBtn = new JButton(ButtonCommand.REST.getLabel());
        JButton inventoryBtn = new JButton(ButtonCommand.INVENTORY.getLabel());
        JButton questBtn = new JButton(ButtonCommand.QUEST.getLabel());

        MovementAction northCommand = new MovementAction(MovementCommand.NORTH);
        MovementAction westCommand = new MovementAction(MovementCommand.WEST);
        MovementAction eastCommand = new MovementAction(MovementCommand.EAST);
        MovementAction southCommand = new MovementAction(MovementCommand.SOUTH);
        final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

        northBtn.addActionListener(northCommand);
        northBtn.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), MovementCommand.NORTH);
        northBtn.getActionMap().put(MovementCommand.NORTH, northCommand);

        westBtn.addActionListener(westCommand);
        westBtn.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), MovementCommand.WEST);
        westBtn.getActionMap().put(MovementCommand.WEST, westCommand);

        eastBtn.addActionListener(eastCommand);
        eastBtn.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), MovementCommand.EAST);
        eastBtn.getActionMap().put(MovementCommand.EAST, eastCommand);
        
        southBtn.addActionListener(southCommand);
        southBtn.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), MovementCommand.SOUTH);
        southBtn.getActionMap().put(MovementCommand.SOUTH, southCommand);

        restBtn.addActionListener(new ButtonAction(ButtonCommand.REST));
        inventoryBtn.addActionListener(new ButtonAction(ButtonCommand.INVENTORY));
        questBtn.addActionListener(new ButtonAction(ButtonCommand.QUEST));

        // Thanks to Gridlayout, setting the size of one button sets size of all in panel
        northBtn.setPreferredSize(new Dimension(30, 30));

        movementPanel.setLayout(new GridLayout(3, 3));
        movementPanel.add(new JLabel());
        movementPanel.add(northBtn, BorderLayout.NORTH);
        movementPanel.add(new JLabel());
        movementPanel.add(westBtn, BorderLayout.WEST);
        movementPanel.add(new JLabel());
        movementPanel.add(eastBtn, BorderLayout.EAST);
        movementPanel.add(new JLabel());
        movementPanel.add(southBtn, BorderLayout.SOUTH);

        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.PAGE_AXIS));
        btnPanel.add(Box.createVerticalGlue());
        btnPanel.add(restBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        btnPanel.add(inventoryBtn);
        btnPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        btnPanel.add(questBtn);
        btnPanel.add(Box.createVerticalGlue());

        add(movementPanel);
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(btnPanel);

        setComponentsStyle(this);
    }

    /**
     * Recursive method sets certain visual properties on JButtons.
     *
     * @param container The Container holding all the JButtons to be set.
     */
    private void setComponentsStyle(Container container)
    {
        for (Component component : container.getComponents())
        {
            if (component instanceof JButton)
            {
                Border bevelBorder = BorderFactory.createRaisedSoftBevelBorder();
                Border emptyBtnBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
                ((JButton) component).setBorder(BorderFactory.createCompoundBorder(bevelBorder, emptyBtnBorder));
                ((JButton) component).setAlignmentX(CENTER_ALIGNMENT);
                ((JButton) component).setFocusPainted(false);

                // If you want to match the blue color around tabbed pane (windows 8) use RGB (200, 221, 242)
                ((JButton) component).setBackground(new Color(180, 180, 180));
            }
            else if (component instanceof JPanel)
            {
                setComponentsStyle((Container) component);
            }
        }
    }

    // public void debugPrintButtonSize()
    // {
    //     Logger.debug("Movement Button width: " + northBtn.getWidth() + ",  height: " + northBtn.getHeight());
    //     Logger.debug("Movement Button width: " + westBtn.getWidth() + ",  height: " + westBtn.getHeight() + "\n");
    // }

    private class MovementAction extends AbstractAction
    {
        private MovementCommand command;

        MovementAction(MovementCommand command)
        {
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Logger.debug(command);
            CONTROLLER.handleMoveCommand(command.getRowMovement(), command.getColMovement());
        }
    }

    private class ButtonAction extends AbstractAction
    {
        private ButtonCommand command;

        ButtonAction(ButtonCommand command)
        {
            this.command = command;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            Logger.debug(command);
            // handle button
            CONTROLLER.handleRestCommand();
        }
    }

    private enum MovementCommand
    {
        NORTH("N", -1, 0),
        WEST("W", 0, -1),
        EAST("E", 0, 1),
        SOUTH("S", 1, 0);

        private final String label;
        private final int rowMovement;
        private final int colMovement;

        MovementCommand(String label, int rowMovement, int colMovement)
        {
            this.label = label;
            this.rowMovement = rowMovement;
            this.colMovement = colMovement;
        }

        public String getLabel()
        {
            return label;
        }

        public int getRowMovement()
        {
            return rowMovement;
        }

        public int getColMovement()
        {
            return colMovement;
        }
    }

    private enum ButtonCommand
    {
        REST("Rest"),
        INVENTORY("Inventory"),
        QUEST("Quest");

        private final String label;

        ButtonCommand(String label)
        {
            this.label = label;
        }

        public String getLabel()
        {
            return label;
        }
    }
}