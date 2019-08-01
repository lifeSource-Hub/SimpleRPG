package simplerpg.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends JPanel
{
    private static JTextArea textArea;

    public TextPanel()
    {
        setLayout(new BorderLayout());
        // setBorder(BorderFactory.createEtchedBorder(1));
        Border etchBorder = BorderFactory.createEtchedBorder(1);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(etchBorder, emptyBorder));

        // textLbl = new JLabel("Message");
        String text = "You wake to find yourself in a trampled clearing, a few embers " +
                "of last night's fire still glowing dully beside you. As you look " +
                "around you see the stygian atmosphere quickly giving way to the " +
                "hopeful light of pre-dawn, adding definition to nearby trees.\n\n" +
                "Reluctantly, you leave your bedroll and begin breaking camp. A " +
                "short while later you sling your pack over your shoulder under a " +
                "now grey sky, eager to deliver your missive and leave this forsaken " +
                "valley. You've come to the valley without a clear notion of where " +
                "to go from here and you think you'll likely have a long day " +
                "searching. At least your pack is lighter than when you started...";

        textArea = new JTextArea();

        textArea.setText(text);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(UIManager.getColor("Label.background"));
        // textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));

        add(textArea, BorderLayout.CENTER);
    }

    public static void setText(String str)
    {
        textArea.setText(str);
    }

    // TODO rename method
    public static void displayUntraversableMsg(int seed)
    {
        switch (seed)
        {
            case 37:
                setText("The mountains are impassible.");
                break;
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                setText("You can't swim.");
                break;
            case 55:
                setText("Walking through walls? You haven't learned that skill yet.");
                break;
            case 90:
                setText("Do you want to trample the old man?");
                break;
            default:
                setText("");
        }        
    }

    // public void debugPrintTextAreaSize()
    // {
    //     Logger.debug("TextArea width: " + textArea.getWidth() + ",  height: " + textArea.getHeight() + "\n");
    // }
}
