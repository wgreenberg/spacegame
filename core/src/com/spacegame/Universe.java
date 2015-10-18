package com.spacegame.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import com.spacegame.components.PositionComponent;
import com.spacegame.components.VelocityComponent;
import com.spacegame.components.DrawableComponent;

public class Universe {
    public PooledEngine engine;

    public Universe (PooledEngine engine) {
        this.engine = engine;
    }

    public void create () {
        Entity ship = createShip();
        this.engine.addEntity(ship);
    }

    public void update (float delta) {
        this.engine.update(delta);
    }

    Entity createShip () {
        Entity entity = new Entity();
        Texture tex = new Texture("ship.png");
        entity.add(new PositionComponent(50, 50));
        entity.add(new VelocityComponent(10, 10));
        entity.add(new DrawableComponent(tex, 100, 100));
        return entity;
    }
}
