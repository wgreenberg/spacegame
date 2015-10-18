package com.spacegame.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;

import com.spacegame.systems.MovementSystem;
import com.spacegame.components.TransformComponent;
import com.spacegame.components.CameraFocusedComponent;
import com.spacegame.components.MovementComponent;
import com.spacegame.components.DrawableComponent;

public class Universe {
    public PooledEngine engine;

    boolean simulating = false;
    float turnDuration = 5f;
    float turnSoFar = 0f;

    Texture[] spoops = new Texture[6];
    String[] imgs = new String[]{"1.gif", "2.gif", "3.png", "4.png",  "5.png",  "6.gif"};

    public Universe (PooledEngine engine) {
        for (int i = 0; i < imgs.length; i++) {
            spoops[i] = new Texture(imgs[i]);
        }
        this.engine = engine;
    }

    public void create () {
        Entity ship = createShip();
        //this.engine.addEntity(ship);

        simulating = true;
    }

    public void update (float delta) {
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            this.engine.addEntity(spoop());
        }

        this.engine.update(delta);
    }

    void stopTurn () {
        simulating = false;
        this.engine.getSystem(MovementSystem.class).setProcessing(false);
    }

    void startTurn () {
        simulating = true;
        turnSoFar = 0;
        this.engine.getSystem(MovementSystem.class).setProcessing(true);
    }

    Entity createShip () {
        Entity entity = new Entity();

        TransformComponent transform = new TransformComponent();
        transform.pos.x = 100;
        transform.pos.y = 100;

        MovementComponent movement = new MovementComponent();
        movement.acc.x = 0.001f;
        movement.acc.y = 0.001f;

        Texture tex = new Texture("ship.png");
        DrawableComponent drawable = new DrawableComponent(tex, 100, 100);

        CameraFocusedComponent camera = new CameraFocusedComponent();

        entity.add(transform);
        entity.add(movement);
        entity.add(drawable);
        entity.add(camera);
        return entity;
    }

    Entity spoop () {
        Entity entity = new Entity();

        TransformComponent transform = new TransformComponent();
        transform.pos.x = MathUtils.random(-100, 100);
        transform.pos.y = MathUtils.random(-100, 100);

        MovementComponent movement = new MovementComponent();
        movement.acc.x = MathUtils.random(-0.01f, 0.01f);
        movement.acc.y = MathUtils.random(-0.01f, 0.01f);
        movement.rotVel = MathUtils.random(-0.5f, 0.5f);

        int imgidx = MathUtils.random(imgs.length - 1);
        Texture tex = spoops[imgidx];
        DrawableComponent drawable = new DrawableComponent(tex, 100, 100);

        CameraFocusedComponent camera = new CameraFocusedComponent();

        entity.add(transform);
        entity.add(movement);
        entity.add(drawable);
        entity.add(camera);
        return entity;
    }

    Entity createStarfield () {
        Entity entity = new Entity();
        return entity;
    }
}
