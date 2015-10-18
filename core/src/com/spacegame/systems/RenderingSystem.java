package com.spacegame.systems;

import java.lang.System;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.spacegame.components.DrawableComponent;
import com.spacegame.components.PositionComponent;

public class RenderingSystem extends IteratingSystem {
    SpriteBatch batch;

    private ComponentMapper<DrawableComponent> dm = ComponentMapper.getFor(DrawableComponent.class);
    private ComponentMapper<PositionComponent> pm = ComponentMapper.getFor(PositionComponent.class);

    public RenderingSystem (SpriteBatch batch) {
        super(Family.all(DrawableComponent.class, PositionComponent.class).get());
        this.batch = batch;
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        DrawableComponent drawable = dm.get(entity);
        PositionComponent position = pm.get(entity);

        drawable.sprite.setCenter(position.x, position.y);

        batch.begin();
        drawable.sprite.draw(batch);
        batch.end();
    }
}
