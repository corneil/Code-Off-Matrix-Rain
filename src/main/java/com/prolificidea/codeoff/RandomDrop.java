package com.prolificidea.codeoff;

import java.awt.*;
import java.util.Random;

public class RandomDrop implements Drop {
    private static Random rng = new Random();
    private int velocity, length, x, y;
    private char[][] text;
    private Color[] colors;
    private int screenHeight;
    boolean reverse = false;

    public RandomDrop(int x, int screenHeight) {
        this.x = x;
        this.screenHeight = screenHeight;
        assert screenHeight > 300;
        length = getRandomInteger(5, screenHeight / Config.FONT_SIZE / 2);
        colors = createColours(length);
        text = createContent(length);
        velocity = getRandomInteger(1, 10);
        reverse = rng.nextInt(10000) == 2;
        if (reverse) {
            this.y = screenHeight;
            velocity = velocity / 2;
        } else {
            this.y = (-1) * length * Config.FONT_SIZE;
        }
    }

    protected Color[] createColours(int length) {
        Color[] colors = new Color[length];
        int primary = rng.nextInt(100);
        if (primary >= 0 && primary <= 80) { // Green
            Color base = new Color(66, 255, 66);
            int start = 88;
            int inc = (base.getGreen() - start) / length;
            for (int i = 0; i < length - 1; i++) {
                colors[i] = new Color(base.getRed(), start + (i * inc), base.getBlue());
            }
            colors[length - 1] = base;
        } else if (primary <= 93) { // Red
            Color base = new Color(192, 25, 25);
            int start = 88;
            int inc = (base.getRed() - start) / length;
            for (int i = 0; i < length - 1; i++) {
                colors[i] = new Color(start + (i * inc), base.getGreen(), base.getBlue());
            }
            colors[length - 1] = base;
        } else { // Blue
            Color base = new Color(92, 92, 192);
            int start = 88;
            int inc = (base.getBlue() - start) / length;
            for (int i = 0; i < length - 1; i++) {
                colors[i] = new Color(base.getRed(), base.getGreen(), start + (i * inc));
            }
            colors[length - 1] = base;
        }
        return colors;
    }

    protected char[][] createContent(int length) {
        char[][] result = new char[length][1];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = getRandomCharacter();
        }
        return result;
    }

    @Override
    public void draw(Graphics2D g2) {
        int fontSize = g2.getFont().getSize();
        for (int i = 0; i < length; i++) {
            int ypos = this.y + (i * fontSize);
            if (ypos >= 0) {
                if (getRandomInteger(0, length) == i) {
                    text[i][0] = getRandomCharacter();
                }
                assert colors.length >= length;
                assert colors[i] != null;
                g2.setColor(colors[i]);
                g2.drawChars(text[i], 0, 1, x, ypos);
            }
        }
        if (reverse) {
            y -= velocity;
        } else {
            y += velocity;
        }
        if(rng.nextInt(50000) == 100) {
            // switch direction
            reverse = !reverse;
            if(reverse) {
                velocity = velocity / 2;
            } else {
                velocity = velocity * 2;
            }
        }
    }

    public char getRandomCharacter() {
        return (char) (rng.nextInt(255 - ' ') + ' ');
    }

    public int getRandomInteger(int min, int max) {
        int randomNum = rng.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    @Override
    public boolean isOffScreen() {
        if (reverse) {
            return y < (-1 * length * Config.FONT_SIZE);
        }
        return y > screenHeight;
    }
}