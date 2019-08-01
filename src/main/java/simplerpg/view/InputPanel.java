package simplerpg.view;

import simplerpg.controller.Controllable;
import simplerpg.controller.UserCmdEvent;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputPanel extends JPanel
{
    private Controllable listener;
    private Insets marginZero = new Insets(0, 0, 0, 0);

    public InputPanel()
    {
        // TODO replace Strings with final String variables
        final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;

        // setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        final Border ETCH_BORDER = BorderFactory.createEtchedBorder(1);
        final Border EMPTY_PNL_BORDER = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(ETCH_BORDER, EMPTY_PNL_BORDER));

        JButton btnNorth = new JButton("N");
        //btnNorth.setActionCommand(UserCmdEvent.NORTH);
        btnNorth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, -1, 0));
            }
        });
        btnNorth.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), "NORTH");
        btnNorth.getActionMap().put("NORTH", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, -1, 0));
            }
        });

        JButton btnWest = new JButton("W");
        btnWest.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 0, -1));
            }
        });
        btnWest.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), "WEST");
        btnWest.getActionMap().put("WEST", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 0, -1));
            }
        });

        JButton btnEast = new JButton("E");
        btnEast.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 0, 1));
            }
        });
        btnEast.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), "EAST");
        btnEast.getActionMap().put("EAST", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 0, 1));
            }
        });

        JButton btnSouth = new JButton("S");
        btnSouth.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 1, 0));
            }
        });
        btnSouth.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), "SOUTH");
        btnSouth.getActionMap().put("SOUTH", new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, 1, 0));
            }
        });

        JButton btn1 = new JButton("Rest");
        //btn1.setActionCommand("B1");
        btn1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, UserCmdEvent.REST));
            }
        });

        JButton btn2 = new JButton("Button");
        //btn2.setActionCommand("B2");

        JButton btn3 = new JButton("Button");
        //btn3.setActionCommand("B3");

        JButton btn4 = new JButton("Button");
        //btn4.setActionCommand("B4");

        JPanel movementPnl = new JPanel();
        movementPnl.setLayout(new GridLayout(3, 3));
        // movementPnl.setMaximumSize(new Dimension(90,90));
        movementPnl.add(new JPanel());
        movementPnl.add(btnNorth, BorderLayout.NORTH);
        movementPnl.add(new JPanel());
        movementPnl.add(btnWest, BorderLayout.WEST);
        movementPnl.add(new JPanel());
        movementPnl.add(btnEast, BorderLayout.EAST);
        movementPnl.add(new JPanel());
        movementPnl.add(btnSouth, BorderLayout.SOUTH);

        JPanel btnPnl = new JPanel();
        btnPnl.setLayout(new BoxLayout(btnPnl, BoxLayout.PAGE_AXIS));
        btnPnl.add(Box.createVerticalGlue());
        btnPnl.add(btn1);
        btnPnl.add(Box.createRigidArea(new Dimension(0, 5)));
        btnPnl.add(btn2);
        btnPnl.add(Box.createRigidArea(new Dimension(0, 5)));
        btnPnl.add(btn3);
        btnPnl.add(Box.createRigidArea(new Dimension(0, 5)));
        btnPnl.add(btn4);
        btnPnl.add(Box.createVerticalGlue());

        for (Component component : btnPnl.getComponents())
        {
            if (component instanceof JButton)
            {
                // TODO cleanup comments
                // ((JButton) component).setMargin(new Insets(30, 0, 0, 10));
                // ((JButton) component).setBorder(BorderFactory.createRaisedSoftBevelBorder());
                final Border BEVEL_BORDER = BorderFactory.createRaisedSoftBevelBorder();
                final Border EMPTY_BTN_BORDER = BorderFactory.createEmptyBorder(0, 5, 0, 5);
                ((JButton) component).setBorder(BorderFactory.createCompoundBorder(BEVEL_BORDER, EMPTY_BTN_BORDER));
                // ((JButton) component).setFocusPainted(false);
                // ((JButton) component).setBackground(new Color(180, 180, 180));
                // ((JButton) component).setAlignmentX(CENTER_ALIGNMENT);
            }
        }

        for (Component component : movementPnl.getComponents())
        {
            if (component instanceof JButton)
            {
                // ((JButton) component).setMargin(marginZero);
                // ((JButton) component).setMaximumSize(new Dimension(35, 35));
                ((JButton) component).setPreferredSize(new Dimension(30, 30));
                ((JButton) component).setBorder(BorderFactory.createRaisedSoftBevelBorder());
                // ((JButton) component).setFocusPainted(false);
                // ((JButton) component).setBackground(new Color(180, 180, 180));
            }
        }

        // add(movementPnl, BorderLayout.WEST);
        // add(btnPnl, BorderLayout.CENTER);
        add(movementPnl);
        add(Box.createRigidArea(new Dimension(30, 0)));
        add(btnPnl);

        iterateComponents(this);
    }

    void setListener(Controllable listener)
    {
        this.listener = listener;
    }

    private void iterateComponents(Container container)
    {
        for (Component component : container.getComponents())
        {
            if (component instanceof JButton)
            {
                ((JButton) component).setAlignmentX(CENTER_ALIGNMENT);
                // ((JButton) component).setMargin(marginZero);
                // ((JButton) component).setBorder(BorderFactory.createRaisedSoftBevelBorder());
                ((JButton) component).setFocusPainted(false);
                ((JButton) component).setBackground(new Color(180, 180, 180));
            }
            else if (component instanceof JPanel)
            {
                iterateComponents((Container) component);
            }
        }
    }

    // public void debugPrintButtonSize()
    // {
    //     Logger.debug("Movement Button width: " + btnNorth.getWidth() + ",  height: " + btnNorth.getHeight());
    // }
}
