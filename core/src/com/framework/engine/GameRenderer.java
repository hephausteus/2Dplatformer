package com.framework.engine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class GameRenderer {

    private GameWorld world;

    private SpriteBatch batch;
    private SpriteBatch buttonBatch;

    private OrthographicCamera camera;



    public GameRenderer(GameWorld world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;

        batch = new SpriteBatch();
        buttonBatch = new SpriteBatch();
    }

    public void render() {

        cameraUpdate();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        world.draw();

        batch.begin();
        world.getPlayer().draw(batch);
        batch.end();
        buttonBatch.begin();
        world.getInputManager().draw(buttonBatch);
        buttonBatch.end();

        // draw world entities
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void cameraUpdate() {
        Vector3 position = camera.position;
        position.x = camera.position.x + (world.getPlayer().getCurrentPosition().x - camera.position.x) * .1f;
        position.y = camera.position.y + (world.getPlayer().getCurrentPosition().y - camera.position.y) * .1f +10f;
        camera.position.set(position);

        camera.update();
    }
}
