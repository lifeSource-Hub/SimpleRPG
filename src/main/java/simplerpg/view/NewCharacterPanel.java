package simplerpg.view;

import org.pmw.tinylog.Logger;
import simplerpg.controller.Controllable;
import simplerpg.controller.UserCmdEvent;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NewCharacterPanel extends JPanel
{
    final String CREATE = "create";
    final String RESET = "reset";
    final String DEFAULT = "default";

    private Controllable listener;
    private Map<String, String> newCharacterInfo = new HashMap<>();
    private JPanel txtFieldPnl;
    private JLabel errorMsg;

    private JTextField nameInput;
    private JComboBox<String> genderInput;
    private JTextField raceInput;
    private JTextField clazzInput;

    NewCharacterPanel()
    {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(0, 1));
        JPanel labelPnl = new JPanel(new GridLayout(0, 1, 5, 5));
        txtFieldPnl = new JPanel(new GridLayout(0, 1, 5, 5));
        JPanel btnPanel = new JPanel();

        final JLabel header = new JLabel("Create Your Character", JLabel.CENTER);
        errorMsg = new JLabel("", JLabel.CENTER);

        header.setFont(new Font("Lucida Sans", Font.BOLD, 14));

        final JLabel nameLbl = new JLabel("Name:");
        final JLabel genderLbl = new JLabel("Gender:");
        final JLabel raceLbl = new JLabel("Race:");
        final JLabel clazzLbl = new JLabel("Class:");

        nameInput = new JTextField(20);
        genderInput = new JComboBox<>(new String[]{"Male", "Female"});
        raceInput = new JTextField(20);
        clazzInput = new JTextField(20);

        nameInput.setName("name");
        genderInput.setName("gender");
        raceInput.setName("race");
        clazzInput.setName("clazz");
        genderInput.setSelectedIndex(-1);

        JButton btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, CREATE));
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, RESET));
            }
        });

        JButton btnDefault = new JButton("Default");
        btnDefault.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                listener.controlCmdEvent(new UserCmdEvent(this, DEFAULT));
            }
        });

        headerPanel.add(header);
        headerPanel.add(errorMsg);

        labelPnl.add(nameLbl);
        labelPnl.add(genderLbl);
        labelPnl.add(raceLbl);
        labelPnl.add(clazzLbl);

        txtFieldPnl.add(nameInput);
        txtFieldPnl.add(genderInput);
        txtFieldPnl.add(raceInput);
        txtFieldPnl.add(clazzInput);

        btnPanel.add(btnCreate);
        btnPanel.add(btnDefault);
        btnPanel.add(btnReset);

        add(headerPanel, BorderLayout.NORTH);
        add(labelPnl, BorderLayout.WEST);
        add(txtFieldPnl, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        setPanelStyle(this);

        setDefaultValues();
    }

    public Map<String, String> getNewCharacterInfo()
    {
        return newCharacterInfo;
    }

    void setListener(Controllable listener)
    {
        this.listener = listener;
    }

    private void setErrorMsg(String msg)
    {
        this.errorMsg.setText(msg);
    }

    private void setPanelStyle(Container container)
    {
        for (Component component : container.getComponents())
        {
            if (component instanceof JButton)
            {
                Border etchBorder = BorderFactory.createRaisedSoftBevelBorder();
                Border emptyBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
                ((JButton) component).setBorder(BorderFactory.createCompoundBorder(etchBorder, emptyBorder));
                ((JButton) component).setMargin(new Insets(5, 0, 5, 0));
            }
            else if (component instanceof JTextField)
            {
                ((JTextField) component).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
            }
            else if (component instanceof JPanel)
            {
                ((JPanel) component).setBorder(BorderFactory.createEmptyBorder(10, 10, 2, 10));
                setPanelStyle((Container) component);
            }
        }
    }

    /**
     * @return Return true if successful, false if there are empty fields.
     */
    boolean setNewCharacterInfo()
    {
        for (Component component : txtFieldPnl.getComponents())
        {
            if (component instanceof JTextField)
            {
                if (((JTextField) component).getText().isEmpty())
                {
                    Logger.debug("empty textField");
                    setErrorMsg("There are empty fields");
                    clearNewCharacterInfo();

                    return false;
                }
                else
                {
                    newCharacterInfo.put(component.getName(), ((JTextField) component).getText());
                }
            }
            else if (component instanceof JComboBox)
            {
                if (((JComboBox) component).getSelectedItem() != null)
                {
                    newCharacterInfo.put(component.getName(), ((JComboBox) component).getSelectedItem().toString());
                }
                else
                {
                    Logger.debug("empty comboBox");
                    setErrorMsg("There are empty fields");
                    clearNewCharacterInfo();

                    return false;
                }
            }
        }

        return true;
    }

    private void clearNewCharacterInfo()
    {
        newCharacterInfo.clear();
    }

    void clearFields()
    {
        setErrorMsg("");

        for (Component component : txtFieldPnl.getComponents())
        {
            if (component instanceof JTextField)
            {
                ((JTextField) component).setText("");
            }
            else if (component instanceof JComboBox)
            {
                ((JComboBox) component).setSelectedIndex(-1);
            }
        }
    }

    void setDefaultValues()
    {
        setErrorMsg("");

        nameInput.setText("John");
        genderInput.setSelectedIndex(0);
        raceInput.setText("Human");
        clazzInput.setText("Warrior");
    }

    /**
     * Print new character to console. For debugging only.
     */
    void printNewCharacterInfo()
    {
        Logger.debug("--newCharacterInfo--");

        if (newCharacterInfo.isEmpty())
        {
            Logger.debug("no info to display");
        }
        else
        {
            for (String k : newCharacterInfo.keySet())
            {
                Logger.debug(k + ": " + newCharacterInfo.get(k));
            }
        }
    }
}
