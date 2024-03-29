package com.blogspot.raibowdev.tasktimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: stream
 * Date: 04.10.11
 * Time: 17:04
 */
public class TaskTimerWindow extends JFrame implements ActionListener {
    public JButton ttButton = new JButton();
    public Properties tasks = new Properties();
    private static long startTime;
    private static long stopTime;

    protected TaskTimerWindow() {
        setTitle("TaskTimer");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setJMenuBar(createMenuBar());
        setVisible(true);
        initGUI();
        try {
            File f = new File("tasktimer.data");
            if (f.exists()) {
                tasks.load(new FileReader("tasktimer.data"));
            } else {
                f.createNewFile();
            }
        } catch (IOException ioe) {
            JOptionPane.showMessageDialog(this, ioe);
        }
    }

    private void initGUI() {
        ttButton.setFont(new Font("Serif", Font.BOLD, 36));
        ttButton.setText("Старт");
        ttButton.setActionCommand("redState");
        ttButton.setFocusPainted(false);
        ttButton.setBackground(Color.RED);
        ttButton.addActionListener(this);
        getContentPane().add(ttButton, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sMenu = new JMenu("Статистика");
        JMenuItem sMenuItem = new JMenuItem("Диаграмма");
        sMenuItem.setActionCommand("showChart");
        sMenuItem.addActionListener(this);
        sMenu.add(sMenuItem);
        menuBar.add(sMenu);
        return menuBar;
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("redState")) {
            startTime = System.currentTimeMillis();
            toDelayState();
        } else if (e.getActionCommand().equals("greenState")) {
            stopTime = System.currentTimeMillis();
            TaskNamerDialog d = new TaskNamerDialog(this);
            d.setLocationRelativeTo(this);
            d.setVisible(true);
        }
        if (e.getActionCommand().equals("showChart")) {
            ChartDialog chartDialog = new ChartDialog(this);
            chartDialog.setVisible(true);
        }
    }

    private void toDelayState() {
        ttButton.setBackground(Color.GREEN);
        ttButton.setActionCommand("greenState");
        ttButton.setText("Делаю...");
    }

    public void toStopState() {
        ttButton.setBackground(Color.RED);
        ttButton.setText("Старт");
        ttButton.setActionCommand("redState");
    }

    public long getStartTime() {
        return startTime;
    }

    public long getStopTime() {
        return stopTime;
    }
}