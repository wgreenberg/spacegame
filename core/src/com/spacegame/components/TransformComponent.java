package com.spacegame.components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.ashley.core.Component;

public class TransformComponent implements Component {
    public Vector2 pos = new Vector2();
    public float rot = 0f;
}
