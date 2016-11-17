package com.framework.engine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    private OrthographicCamera camera;

    public GameScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = 640;
        float gameHeight = screenHeight / (screenWidth / gameWidth);

        camera = new OrthographicCamera(640, 640 * (screenHeight / screenWidth));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        world = new GameWorld(camera);
        renderer = new GameRenderer(world, camera);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);
        renderer.render();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
