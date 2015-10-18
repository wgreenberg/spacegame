package com.spacegame.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.ashley.core.Component;

public class MovementComponent implements Component {
    public Vector2 vel = new Vector2();
    public Vector2 acc = new Vector2();
    public float rotVel = 0f;
}
