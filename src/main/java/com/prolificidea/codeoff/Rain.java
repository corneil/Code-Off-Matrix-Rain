package com.prolificidea.codeoff;

import javax.swing.*;
import java.awt.*;

public class Rain extends JPanel {
    private Drop[] drops;

    public Rain(int dropCount) {
        drops = new RandomDrop[dropCount];

    }

    private RandomDrop createDrop(int i) {
        return new RandomDrop(i * Config.FONT_SIZE, getHeight());
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        Font font = new Font("Monospaced", Font.PLAIN, Config.FONT_SIZE);
        g2.setFont(font);
        for (int i = 0; i < drops.length; i++) {
            if(drops[i] == null || drops[i].isOffScreen()) {
                drops[i] = createDrop(i);
            }
            drops[i].draw(g2);
        }
        try {
            Thread.sleep(10);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        repaint();
    }
}
