package com.prolificidea.codeoff;

import java.awt.*;

/**
 * Created by Corneil on 2016-07-20.
 */
public interface Drop {
    void draw(Graphics2D g2);
    boolean isOffScreen();
}
