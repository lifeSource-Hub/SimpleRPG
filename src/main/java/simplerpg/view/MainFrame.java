package simplerpg.view;

import org.pmw.tinylog.Logger;
import simplerpg.controller.PanelEventController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private NewCharacterPanel newCharacterPnl;
    private MapPanel mapPnl;
    private PlayerPanel playerInfoPnl;

    public MainFrame()
    {
        createCharacter();
    }

    private void createCharacter()
    {
        createCharacter(375, 325);
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

        //region Automatically submit default character
        //-----------------------------------------------------------------------------
        if (newCharacterPnl.setNewCharacterInfo())
        {
            remove(newCharacterPnl);
            startGame();
        }
        //-----------------------------------------------------------------------------
        //endregion

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
        Logger.debug("Game started");
        mapPnl = new MapPanel();
        playerInfoPnl = new PlayerPanel(newCharacterPnl.getNewCharacterInfo());

        PanelEventController controller = new PanelEventController();
        InputPanel inputPnl = new InputPanel();//controller);
        TextPanel textPnl = new TextPanel();

        controller.setMapPnl(mapPnl);
        controller.setInputPnl(inputPnl);
        controller.setNewCharacterPnl(newCharacterPnl);
        inputPnl.setController(controller);

        JPanel outerContainer = new JPanel();
        outerContainer.setLayout(new BorderLayout());
        outerContainer.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        outerContainer.setPreferredSize(new Dimension(300, 710));
        outerContainer.add(playerInfoPnl, BorderLayout.NORTH);
        outerContainer.add(textPnl, BorderLayout.CENTER);
        outerContainer.add(inputPnl, BorderLayout.SOUTH);

        add(mapPnl, BorderLayout.WEST);
        add(outerContainer, BorderLayout.CENTER);

        revalidate();
        pack();
        repaint();
        setTitle("Game Application");
        setLocationRelativeTo(null);

        Logger.debug("Frame Width: " + this.getWidth() + "  Height: " + this.getHeight());
        Logger.debug("MapPanel Width: " + mapPnl.getWidth() + "  Height: " + mapPnl.getHeight());
        Logger.debug("outerContainer Width: " + outerContainer.getWidth() + "  Height: " + outerContainer.getHeight());
        Logger.debug("PlayerPanel Width: " + playerInfoPnl.getWidth() + "  Height: " + playerInfoPnl.getHeight());
        Logger.debug("TextPanel Width: " + textPnl.getWidth() + "  Height: " + textPnl.getHeight());
        Logger.debug("InputPanel Width: " + inputPnl.getWidth() + "  Height: " + inputPnl.getHeight() + "\n");
    }
}
