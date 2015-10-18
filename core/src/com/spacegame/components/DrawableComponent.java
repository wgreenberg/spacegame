package com.spacegame.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.ashley.core.Component;

public class DrawableComponent implements Component {
    public Sprite sprite;
    public DrawableComponent (Texture tex, int height, int width) {
        this.sprite = new Sprite(tex); 
        this.sprite.setSize(height, width);
    }
}
