package com.framework.engine;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {

    private Animation currentAnimation;

    private Vector2 currentSpeed;
    private Vector2 currentPosition;
    private GameWorld world;



    public Player(GameWorld world) {
        this.world = world;
        currentPosition = new Vector2(0, 0);
        currentSpeed = new Vector2(0, 0);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(currentAnimation.getKeyFrame(world.getAnimationRunTime(), true), currentPosition.x, currentPosition.y);

    }

    public void setCurrentPosition(float x, float y) {
        currentPosition.x = x;
        currentPosition.y = y;
    }

    public void setCurrentSpeed(float x, float y) {
        currentSpeed.x = x;
        currentSpeed.y = y;
    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public Vector2 getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentAnimation(Animation currentAnimation) {
        this.currentAnimation = currentAnimation;
    }

    public Animation getCurrentAnimation() {
        return currentAnimation;
    }
}
