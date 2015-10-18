package com.spacegame.screen;

import com.badlogic.gdx.ScreenAdapter;

import com.spacegame.game.SpaceGame;
import com.spacegame.components.PositionComponent;
import com.spacegame.components.VelocityComponent;
import com.spacegame.systems.MovementSystem;

public class SpaceScreen extends ScreenAdapter {
    SpaceGame game;

    public SpaceScreen (SpaceGame game) {
        this.game = game;
    }
}
