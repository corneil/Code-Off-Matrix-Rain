package com.prolificidea.codeoff;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Rain");
        // Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // frame.setSize(Config.SCREEN_SIZE, Config.SCREEN_SIZE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        int dropCount = frame.getWidth() / Config.FONT_SIZE;
        frame.add(new Rain(dropCount));

    }

}