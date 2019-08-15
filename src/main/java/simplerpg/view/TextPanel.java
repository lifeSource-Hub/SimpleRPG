package simplerpg.view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TextPanel extends JPanel
{
    private static JTextArea textArea;

    TextPanel()
    {
        Border etchBorder = BorderFactory.createEtchedBorder(1);
        Border emptyBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(etchBorder, emptyBorder));
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setBackground(UIManager.getColor("Label.background"));
        // textArea.setFont(UIManager.getFont("Label.font"));
        textArea.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));

        add(textArea, BorderLayout.CENTER);

        displayIntro();
    }

    static void setText(String str)
    {
        textArea.setText(str);
    }

    static void displayOutOfBounds()
    {
        setText("To continue farther in this direction would lead you further from your " +
                "mission. Knowing you have more to accomplish, you retrace your steps.");
    }

    private void displayIntro()
    {
        setText("You wake to find yourself in a trampled clearing, a few embers " +
                "of last night's fire still glowing dully beside you. As you look " +
                "around you see the stygian atmosphere quickly giving way to the " +
                "hopeful light of pre-dawn, adding definition to nearby trees.\n\n" +
                "Reluctantly, you leave your bedroll and begin breaking camp. A " +
                "short while later you sling your pack over your shoulder under a " +
                "now grey sky, eager to deliver your missive and leave this forsaken " +
                "valley. You've come to the valley without a clear notion of where " +
                "to go from here and you think you'll likely have a long day " +
                "searching. At least your pack is lighter than when you started...");
    }

    // public void debugPrintTextAreaSize()
    // {
    //     Logger.debug("TextArea width: " + textArea.getWidth() + ",  height: " + textArea.getHeight() + "\n");
    // }
}
