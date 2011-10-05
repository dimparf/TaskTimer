package com.blogspot.raibowdev.tasktimer;

import javax.swing.*;
import java.awt.event.*;

public class TaskNamerDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField taskField;
    private TaskTimerWindow owner;

    public TaskNamerDialog(TaskTimerWindow owner) {
        this.owner = owner;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

// call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

// call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {
// add your code here
        double duration = owner.getStopTime() - owner.getStartTime(); //в миллисекундах
        double durationInHour = duration / 3600000.0; //в часах
        String sresult;
        if (durationInHour < 1.0) {
            sresult = Math.round(duration / 60000.0) + " минут";
        } else {
            sresult = durationInHour + " часов";
        }
        owner.tasks.put(taskField.getText().trim(), sresult);
        System.out.println(owner.tasks.stringPropertyNames());
        owner.toStopState();
        dispose();
    }

    private void onCancel() {
// add your code here if necessary
        dispose();
    }
}
