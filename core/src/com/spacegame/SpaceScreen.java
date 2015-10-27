package com.spacegame.screen;

import java.lang.System;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.spacegame.game.SpaceGame;
import com.spacegame.test.Checkerboard;

public class SpaceScreen extends ScreenAdapter implements InputProcessor {
    SpaceGame game;
    OrthographicCamera camera;
    Vector3 cameraDestination;

    Checkerboard checkerboard;

    public SpaceScreen (SpaceGame game) {
        this.game = game;

        checkerboard = new Checkerboard(20, 256, 256);
        camera = new OrthographicCamera(500, 500);
        cameraDestination = new Vector3(0, 0, 0);

        Gdx.input.setInputProcessor(this);
    }

	@Override
	public void resize (int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
	}

	@Override
	public void render (float deltaTime) {
        // LERP to the camera destination
        camera.position.set(camera.position.lerp(cameraDestination, deltaTime));
        camera.update();

        // orient our SpriteBatch to the camera
        game.batch.setProjectionMatrix(camera.combined);

        // update the stuff we're gonna draw
        checkerboard.update();

        // draw stuff
        game.batch.begin();
        checkerboard.sprite.draw(game.batch);
        game.batch.end();
	}

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // set the camera's destination to where the user just clicked
        float dx = screenX - (camera.viewportWidth / 2);
        float dy = (camera.viewportHeight / 2) - screenY;
        cameraDestination.x = camera.position.x + dx;
        cameraDestination.y = camera.position.y + dy;
        return false;
    }

    // since we implement InputProcessor, we need to write all of these method
    // stubs even though we aren't using them. Thanks, Java
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
