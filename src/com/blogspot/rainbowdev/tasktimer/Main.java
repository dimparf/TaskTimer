package com.blogspot.rainbowdev.tasktimer;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: stream
 * Date: 04.10.11
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String args[]) {
        final TaskTimerWindow ttWindow = new TaskTimerWindow();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

            public void run() {
                try {
                    ttWindow.tasks.store(new FileWriter("tasktimer.data"), "This is my tasktime list");
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }, "shutdownHook"));
    }
}
