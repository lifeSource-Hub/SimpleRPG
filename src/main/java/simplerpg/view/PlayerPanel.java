package simplerpg.view;

import simplerpg.controller.PlayerCharacter;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Map;

public class PlayerPanel extends JPanel
{
    private PlayerCharacter pc;
    private JPanel statsPnl;
    private JPanel infoPnl;

    private final JLabel header;
    private final JLabel healthLbl;
    private final JLabel attackLbl;
    private final JLabel defenseLbl;
    private final JLabel weaponLbl;
    private final JLabel raceLbl;
    private final JLabel classLbl;
    private final JLabel genderLbl;
    private JLabel plyrHealth;
    private JLabel plyrAttack;
    private JLabel plyrDefense;
    private JLabel plyrWeapon;
    private JLabel plyrRace;
    private JLabel plyrClass;
    private JLabel plyrGender;

    PlayerPanel(Map<String, String> playerInfo)
    {
        pc = new PlayerCharacter(playerInfo);
        
        JPanel headerPnl = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        JTabbedPane tabbedPnl = new JTabbedPane(JTabbedPane.TOP);
        statsPnl = new JPanel(new GridLayout(0, 2));
        infoPnl = new JPanel(new GridLayout(0, 2));

        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        Border etchBorder = BorderFactory.createEtchedBorder(1);
        Border emptyBorder = BorderFactory.createEmptyBorder(0, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(etchBorder, emptyBorder));

        header = new JLabel(playerInfo.get("name"));

        healthLbl = new JLabel("Health:");
        plyrHealth = new JLabel("5");
        attackLbl = new JLabel("Attack:");
        plyrAttack = new JLabel("5");
        defenseLbl = new JLabel("Defense:");
        plyrDefense = new JLabel("5");
        weaponLbl = new JLabel("Weapon:");
        plyrWeapon = new JLabel("none");

        // TODO replace strings with final from NewCharacterPanel
        //  or create new object (class) for setting and accessing
        raceLbl = new JLabel("Race:");
        plyrRace = new JLabel(playerInfo.get("race"));
        classLbl = new JLabel("Class:");
        plyrClass = new JLabel(playerInfo.get("clazz"));
        genderLbl = new JLabel("Gender:");
        plyrGender = new JLabel(playerInfo.get("gender"));

        statsPnl.add(healthLbl);
        statsPnl.add(plyrHealth);
        statsPnl.add(attackLbl);
        statsPnl.add(plyrAttack);
        statsPnl.add(defenseLbl);
        statsPnl.add(plyrDefense);
        statsPnl.add(weaponLbl);
        statsPnl.add(plyrWeapon);

        infoPnl.add(raceLbl);
        infoPnl.add(plyrRace);
        infoPnl.add(classLbl);
        infoPnl.add(plyrClass);
        infoPnl.add(genderLbl);
        infoPnl.add(plyrGender);

        headerPnl.add(header);

        tabbedPnl.add("Stats", statsPnl);
        tabbedPnl.add("Info", infoPnl);
        tabbedPnl.setFocusable(false);

        add(headerPnl);
        add(tabbedPnl);

        // Font font = new Font("Palatino Linotype", Font.BOLD, 14);
        // setFont(headerPnl, font);
        // setFont(statsPnl, font);
        // setFont(infoPnl, font);
    }

    public void setLabel(String text)
    {
    }

    public void setHealth(int health)
    {
        plyrHealth.setText(Integer.toString(health));
    }

    public void setFont(Container container, Font font)
    {
        for (Component component : container.getComponents())
        {
                component.setFont(font);
        }
        
        // for (Component component : infoPnl.getComponents())
        // {
        //     if (component instanceof JLabel)
        //     {
        //         component.setFont(font);
        //     }
        // }
    }
}
