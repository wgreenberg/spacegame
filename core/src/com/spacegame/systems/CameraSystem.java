package com.spacegame.systems;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.utils.Bag;
import com.badlogic.ashley.utils.ImmutableArray;

import com.spacegame.components.CameraFocusedComponent;
import com.spacegame.components.TransformComponent;

public class CameraSystem extends EntitySystem implements EntityListener {
    OrthographicCamera camera;
    Family family;
    Bag<Entity> entities;

    private ComponentMapper<TransformComponent> tm = ComponentMapper.getFor(TransformComponent.class);

    public CameraSystem (OrthographicCamera camera) {
        super(0);
        this.family = Family.all(CameraFocusedComponent.class, TransformComponent.class).get();
        this.camera = camera;
    }

    @Override
    public void update (float deltaTime) {
        float sumX = 0, sumY = 0;
        TransformComponent t;
        Entity e;

        for (int i = 0; i < entities.size(); i++) {
            e = entities.get(i);
            t = tm.get(e);
            sumX += t.pos.x;
            sumY += t.pos.y;
        }

        Vector3 centerpoint = new Vector3(sumX / entities.size(), sumY / entities.size(), 0);
        camera.position.set(camera.position.lerp(centerpoint, deltaTime));
    }

    @Override
    public void addedToEngine (Engine engine) {
        engine.addEntityListener(this);
        entities = new Bag();
        ImmutableArray<Entity> arr = engine.getEntitiesFor(family);
        for (Entity e : arr)
            entities.add(e);
    }

    @Override
    public void entityAdded (Entity entity) {
        if (family.matches(entity))
            entities.add(entity);
    }

    @Override
    public void entityRemoved (Entity entity) {
        if (family.matches(entity))
            entities.remove(entity);
    }
}
