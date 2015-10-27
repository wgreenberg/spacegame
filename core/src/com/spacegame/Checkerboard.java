package com.spacegame.test;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Checkerboard {
    public Sprite sprite;

    Texture texture;
    Pixmap pixmap;
    int squareSize;
    int height, width;

    int offset = 0;

    public Checkerboard (int squareSize, int height, int width) {
        this.squareSize = squareSize;

        this.height = height;
        this.width = width;

        pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        texture = new Texture(pixmap);
        sprite = new Sprite(texture, 0, 0, width, height);
    }

    public void update () {
        // dynamically makes the flying checkerboard pattern
        // protip: never do this during render cycle
        int white = 0xFFFFFFFF;
        int black = 0x000000FF;
        int pattern1, pattern2;
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                if ((j + offset) / squareSize % 2 == 0) {
                    pattern1 = white;
                    pattern2 = black;
                } else {
                    pattern1 = black;
                    pattern2 = white;
                }

                if ((i + offset) / squareSize % 2 == 0)
                    pixmap.drawPixel(i, j, pattern1);
                else
                    pixmap.drawPixel(i, j, pattern2);
            }
        }
        texture.draw(pixmap, 0, 0);

        offset++;

        // dat int overflow
        if (offset > squareSize) offset = 0;
    }
}
