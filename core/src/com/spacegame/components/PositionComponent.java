package com.spacegame.components;

import com.badlogic.ashley.core.Component;

public class PositionComponent implements Component {
    public float x;
    public float y;

    public PositionComponent (float pX, float pY) {
        this.x = pX;
        this.y = pY;
    }
}
