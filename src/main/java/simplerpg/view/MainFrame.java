package simplerpg.view;

import org.pmw.tinylog.Logger;
import simplerpg.controller.Controllable;
import simplerpg.controller.UserCmdEvent;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private NewCharacterPanel newCharacterPnl;
    private MapPanel gridPnl;
    private InputPanel inputPnl;
    private PlayerPanel infoPnl;
    private TextPanel textPnl;

    public MainFrame()
    {
        this(375, 325);
    }

    public MainFrame(int width, int height)
    {
        createCharacter(width, height);
    }

    private void createCharacter(int width, int height)
    {
        newCharacterPnl = new NewCharacterPanel();

        add(newCharacterPnl, BorderLayout.CENTER);

        setTitle("Character Creation");
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //////////////
        // Automatically submit default character
        if (newCharacterPnl.setNewCharacterInfo())
        {
            newCharacterPnl.printNewCharacterInfo();

            remove(newCharacterPnl);
            startGame();
        }
        /////////////

        newCharacterPnl.setListener(e ->
        {
            if (e.getPayload().equals(newCharacterPnl.CREATE))
            {
                newCharacterPnl.printNewCharacterInfo();

                if (newCharacterPnl.setNewCharacterInfo())
                {
                    remove(newCharacterPnl);
                    startGame();
                }
            }
            else if (e.getPayload().equals(newCharacterPnl.RESET))
            {
                newCharacterPnl.clearFields();
            }
            else if (e.getPayload().equals(newCharacterPnl.DEFAULT))
            {
                newCharacterPnl.setDefaultValues();
            }
        });
    }

    private void startGame()
    {
        // Logger.debug("Game started");
        gridPnl = new MapPanel();
        inputPnl = new InputPanel();
        infoPnl = new PlayerPanel(newCharacterPnl.getNewCharacterInfo());
        textPnl = new TextPanel();

        JPanel outerContainer = new JPanel();
        outerContainer.setLayout(new BorderLayout());
        outerContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outerContainer.setPreferredSize(new Dimension(300, 710));
        outerContainer.add(infoPnl, BorderLayout.NORTH);
        outerContainer.add(textPnl, BorderLayout.CENTER);
        outerContainer.add(inputPnl, BorderLayout.SOUTH);

        add(gridPnl, BorderLayout.WEST);
        add(outerContainer, BorderLayout.CENTER);

        inputPnl.setListener(new Controllable()
        {
            @Override
            public void controlCmdEvent(UserCmdEvent e)
            {
                switch (e.getPayload())
                {
                    case "":
                        Logger.debug("rowMovement: " + e.getRowMovement()
                                + "  colMovement: " + e.getColMovement());
                        gridPnl.movePlayer(e.getRowMovement(), e.getColMovement());
                        break;
                    case UserCmdEvent.REST:
                        infoPnl.setHealth(10);
                        break;
                }
            }
        });

        revalidate();
        pack();
        repaint();
        // setPreferredSize(new Dimension(1000, 1200));
        // setSize(950, 800);
        setTitle("Game Application");
        setLocationRelativeTo(null);
        // revalidate();
        // repaint();

        Logger.debug("Frame Width: " + this.getWidth() + "  Height: " + this.getHeight());
        Logger.debug("MapPanel Width: " + gridPnl.getWidth() + "  Height: " + gridPnl.getHeight());
        Logger.debug("outerContainer Width: " + outerContainer.getWidth() + "  Height: " + outerContainer.getHeight());
        Logger.debug("PlayerPanel Width: " + infoPnl.getWidth() + "  Height: " + infoPnl.getHeight());
        Logger.debug("TextPanel Width: " + textPnl.getWidth() + "  Height: " + textPnl.getHeight());
        Logger.debug("InputPanel Width: " + inputPnl.getWidth() + "  Height: " + inputPnl.getHeight() + "\n");
    }
}
