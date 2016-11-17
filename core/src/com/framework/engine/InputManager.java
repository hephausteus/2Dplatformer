package com.framework.engine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class InputManager implements InputProcessor {

    private SimpleButton myButtonRight;
    private SimpleButton myButtonLeft;
    private SimpleButton myButtonJump;
    private SimpleButton myButtonAction;

    private OrthographicCamera camera;

    private int invertScreenVal;

    public InputManager(OrthographicCamera camera) {
        this.camera = camera;

        invertScreenVal = Gdx.graphics.getHeight();

        Texture texture = new Texture(Gdx.files.internal("button.png"));
        TextureRegion buttonDownTexture = new TextureRegion(texture, 30, 5, 65, 65);
        TextureRegion buttonUpTexture = new TextureRegion(texture, 185, 5, 65, 65);

        myButtonLeft = new SimpleButton(0, 0, buttonUpTexture.getRegionWidth(), buttonUpTexture.getRegionHeight(), buttonUpTexture, buttonDownTexture);
        myButtonRight = new SimpleButton(buttonUpTexture.getRegionWidth() + 10, 0, buttonUpTexture.getRegionWidth(), buttonUpTexture.getRegionHeight(), buttonUpTexture, buttonDownTexture);
        myButtonJump = new SimpleButton(640 - buttonUpTexture.getRegionWidth(), 0, buttonUpTexture.getRegionWidth(), buttonUpTexture.getRegionHeight(), buttonUpTexture, buttonDownTexture);
        myButtonAction = new SimpleButton(630 - 2 * buttonUpTexture.getRegionWidth(), 0, buttonUpTexture.getRegionWidth(), buttonUpTexture.getRegionHeight(), buttonUpTexture, buttonDownTexture);

        /*
        myButtonLeft = new SimpleButton(0, 0, 260, 260, buttonUpTexture, buttonDownTexture);
        myButtonRight = new SimpleButton(270, 0, 260, 260, buttonUpTexture, buttonDownTexture);
        myButtonJump = new SimpleButton(2000, 0, 260, 260, buttonUpTexture, buttonDownTexture);
        myButtonAction = new SimpleButton(1730, 0, 260, 260, buttonUpTexture, buttonDownTexture);
        */
    }

    public SimpleButton getMyButtonRight() {
        return myButtonRight;
    }

    public SimpleButton getMyButtonLeft() {
        return myButtonLeft;
    }

    public SimpleButton getMyButtonJump() {
        return myButtonJump;
    }

    public SimpleButton getMyButtonAction() {
        return myButtonAction;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            myButtonLeft.pressButton(true);
        }
        if (keycode == Input.Keys.RIGHT) {
            myButtonRight.pressButton(true);
        }
        if (keycode == Input.Keys.UP) {
            myButtonJump.pressButton(true);
        }
        if (keycode == Input.Keys.SPACE) {
            myButtonAction.pressButton(true);
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            myButtonLeft.pressButton(false);
        }
        if (keycode == Input.Keys.RIGHT) {
            myButtonRight.pressButton(false);
        }
        if (keycode == Input.Keys.UP) {
            myButtonJump.pressButton(false);
        }
        if (keycode == Input.Keys.SPACE) {
            myButtonAction.pressButton(false);
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 vec3 = new Vector3(screenX, screenY, 0);
        //camera.unproject(vec3);
        int newY = invertScreenVal - screenY;

        myButtonLeft.isTouchDown((int)vec3.x, newY);
        myButtonRight.isTouchDown((int)vec3.x, newY);
        myButtonJump.isTouchDown((int)vec3.x, newY);
        myButtonAction.isTouchDown((int)vec3.x, newY);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector3 vec3 = new Vector3(screenX, screenY, 0);
        //camera.unproject(vec3);
        int newY = invertScreenVal - screenY;

        myButtonLeft.isTouchUp((int)vec3.x, newY);
        myButtonRight.isTouchUp((int)vec3.x, newY);
        myButtonJump.isTouchUp((int)vec3.x, newY);
        myButtonAction.isTouchUp((int)vec3.x, newY);
        return true;
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

    public void draw(SpriteBatch batch) {
        myButtonLeft.draw(batch);
        myButtonRight.draw(batch);
        myButtonJump.draw(batch);
        myButtonAction.draw(batch);
    }

}
