package com.blogspot.rainbowdev.tasktimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TaskNamerDialog extends JDialog {
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField taskField;
    private TaskTimerWindow owner;

    public TaskNamerDialog(TaskTimerWindow owner) {
        this.owner = owner;
        setModal(true);
        setTitle("Add task name");
        taskField = new JTextField(16);
        buttonOK = new JButton("OK");
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        buttonCancel = new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        JPanel fieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.add(taskField);
        add(fieldPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(buttonOK);
        buttonPanel.add(buttonCancel);
        add(buttonPanel, BorderLayout.SOUTH);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        ((JPanel)getContentPane()).registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        pack();
    }

    private void onOK() {
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
        dispose();
    }
}
