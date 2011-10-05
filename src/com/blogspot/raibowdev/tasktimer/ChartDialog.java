package com.blogspot.raibowdev.tasktimer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: stream
 * Date: 05.10.11
 * Time: 12:15
 */

public class ChartDialog extends JDialog {

    public ChartDialog(TaskTimerWindow owner) {
        setModal(true);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(300, 300);
        add(createChartPanel(owner.tasks, "TaskTimer"), BorderLayout.CENTER);
    }

    private static PieDataset createDataset(Properties tasks) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Enumeration taskNames = tasks.keys();
        while (taskNames.hasMoreElements()) {
            String key = (String) taskNames.nextElement();
            String value = tasks.get(key).toString();
            Double dvalue = Double.parseDouble(value.substring(0, value.indexOf(" ")).trim());
            System.out.println(key + " # " + value);
            dataset.setValue(key, dvalue);
        }
        return dataset;
    }

    private static JFreeChart createChart(PieDataset dataset, String title) {
        JFreeChart chart = ChartFactory.createPieChart3D(
                title,  // chart title
                dataset,             // data
                true,               // include legend
                true,                //tool tips
                false                //urls
        );
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(false);
        plot.setLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        plot.setNoDataMessage("No data available");
        //plot.setCircular(false);
        plot.setLabelGap(0.02);
        return chart;
    }

    public static JPanel createChartPanel(Properties tasks, String title) {
        JFreeChart chart = createChart(createDataset(tasks), title);
        return new ChartPanel(chart);
    }
}
