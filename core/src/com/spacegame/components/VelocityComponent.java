package com.spacegame.components;

import com.badlogic.ashley.core.Component;

public class VelocityComponent implements Component {
    public float x;
    public float y;

    public VelocityComponent (float vX, float vY) {
        this.x = vX;
        this.y = vY;
    }
}
