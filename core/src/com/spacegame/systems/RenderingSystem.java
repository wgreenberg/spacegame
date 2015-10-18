package com.spacegame.systems;

import java.lang.System;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import com.spacegame.components.DrawableComponent;
import com.spacegame.components.TransformComponent;

public class RenderingSystem extends IteratingSystem {
    SpriteBatch batch;

    private ComponentMapper<DrawableComponent> dm = ComponentMapper.getFor(DrawableComponent.class);
    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public RenderingSystem (SpriteBatch batch) {
        super(Family.all(DrawableComponent.class, TransformComponent.class).get());
        this.batch = batch;
    }

    @Override
    public void processEntity (Entity entity, float deltaTime) {
        DrawableComponent drawable = dm.get(entity);
        TransformComponent transform = tm.get(entity);

        drawable.sprite.setCenter(transform.pos.x, transform.pos.y);
        drawable.sprite.setRotation(transform.rot);

        batch.begin();
        drawable.sprite.draw(batch);
        batch.end();
    }
}
