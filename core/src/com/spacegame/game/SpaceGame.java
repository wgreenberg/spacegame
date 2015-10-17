package com.spacegame.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture texture;
    Pixmap pixmap;
    Sprite sprite;

    int cDim = 20;
    int offset = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
	}

	@Override
	public void render () {
        // I'm 99% we shouldn't ever actually do this kinda shit in the render cycle
        pixmap = new Pixmap(256, 256, Pixmap.Format.RGBA8888);
        int white = 0xFFFFFFFF;
        int black = 0x000000FF;
        int pattern1, pattern2;
        for (int i=0; i<256; i++) {
            for (int j=0; j<256; j++) {
                if ((j + offset) / cDim % 2 == 0) {
                    pattern1 = white;
                    pattern2 = black;
                } else {
                    pattern1 = black;
                    pattern2 = white;
                }

                if ((i + offset) / cDim % 2 == 0)
                    pixmap.drawPixel(i, j, pattern1);
                else
                    pixmap.drawPixel(i, j, pattern2);
            }
        }
        offset++;

        // dat int overflow
        if (offset > cDim) offset = 0;

        texture = new Texture(pixmap);
        pixmap.dispose();
        sprite = new Sprite(texture);

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		sprite.draw(batch);
		batch.end();
	}
}
