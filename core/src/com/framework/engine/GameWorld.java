package com.framework.engine;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class GameWorld {

    private AssetsManager assetsManager;
    private Player player;

    float elapsedTime;
    float animationRunTime;

    private InputManager inputManager;
    private OrthographicCamera camera;

    private GameMap gameMap;

    private boolean actionInProgress;

    private ShapeRenderer shapeRenderer;
    private Rectangle collisionBox;

    public GameWorld(OrthographicCamera camera) {
        player = new Player(this);
        player.setCurrentPosition(300, 200);
        player.setCurrentSpeed(0, 0);
        this.camera = camera;
        inputManager = new InputManager(camera);
        Gdx.input.setInputProcessor(inputManager);

        gameMap = new GameMap();
        shapeRenderer = new ShapeRenderer();
        collisionBox = new Rectangle(100, 100, 25, 60);
        assetsManager = new AssetsManager();
        assetsManager.prepare();
        player.setCurrentAnimation(assetsManager.getStandAnimation());
        animationRunTime = 0f;
        elapsedTime = 0f;
    }

    boolean airborne = true;
    boolean isFalling = true;

    public void update(float deltaTime) {
        elapsedTime += Gdx.graphics.getDeltaTime();
        animationRunTime += Gdx.graphics.getDeltaTime();

        float acceleration = 0.2f;
        float targetSpeed = 300f;
        float aerialAcceleration = 0.2f;
        float terminalSpeed = 400f;

        if (!airborne && !isFalling) {
            if (inputManager.getMyButtonLeft().isPressed()) {
                player.setCurrentAnimation(assetsManager.getWalkAnimation());
                float cSpeed = acceleration * (-1 * targetSpeed) + (1f - acceleration) * player.getCurrentSpeed().x;
                player.setCurrentSpeed(cSpeed, player.getCurrentSpeed().y);
            } else if (inputManager.getMyButtonRight().isPressed()) {
                player.setCurrentAnimation(assetsManager.getRunAnimation());
                float cSpeed = acceleration * targetSpeed + (1f - acceleration) * player.getCurrentSpeed().x;
                player.setCurrentSpeed(cSpeed, player.getCurrentSpeed().y);
            } else
                player.setCurrentSpeed(0f, player.getCurrentSpeed().y);

            /**
             * vector2f curSpeed = a * targetSpeed + (1-a) * curSpeed;
             if (fabs(curSpeed.x) < threshold) curSpeed.x = 0;
             if (fabs(curSpeed.y) < threshold) curSpeed.y = 0;
             * */

            if (inputManager.getMyButtonJump().isPressed()) {
                airborne = true;
                player.setCurrentAnimation(assetsManager.getJumpAndFallAnimation());
                player.setCurrentSpeed(player.getCurrentSpeed().x, 1000f);
            }

            if (inputManager.getMyButtonAction().isPressed()) {
                player.setCurrentAnimation(assetsManager.getShootBowAnimation());
            }
        }
        else if (airborne && !isFalling) {
            float cSpeed = aerialAcceleration * 0f + (1f - aerialAcceleration) * player.getCurrentSpeed().y;
            player.setCurrentSpeed(player.getCurrentSpeed().x, cSpeed);
            //System.out.println(cSpeed);

            if (cSpeed <= 2f) {
                isFalling = true;
                player.setCurrentSpeed(player.getCurrentSpeed().x, 0f);
            }
        }

        if (isFalling) {
            float cSpeed = aerialAcceleration * (-1 * terminalSpeed) + (1f - aerialAcceleration) * player.getCurrentSpeed().y;
            player.setCurrentSpeed(player.getCurrentSpeed().x, cSpeed);
        }

        // calculate new position
        float tempX = collisionBox.getX();
        tempX += player.getCurrentSpeed().x * deltaTime;
        float tempY = collisionBox.getY();
        tempY += player.getCurrentSpeed().y * deltaTime;

        if (!detectGroundCollision(tempX, tempY)) {
            if (tempX > 20 && tempX < 3780)
                collisionBox.setPosition(tempX, tempY);
            else
                collisionBox.setPosition(collisionBox.getX(), tempY);
        }
        else
        {
            if (tempX > 20 && tempX < 3780) {
                collisionBox.setPosition(tempX, collisionBox.getY());
            }
            else
                collisionBox.setPosition(collisionBox.getX(), collisionBox.getY());

            airborne = false;
            isFalling = false;
            player.setCurrentSpeed(player.getCurrentSpeed().x, 0f);
        }

        if (player.getCurrentSpeed().x == 0 && player.getCurrentSpeed().y == 0 ) {
            player.setCurrentAnimation(assetsManager.getStandAnimation());
        }

        //System.out.println(collisionBox.getX() + " " + collisionBox.getY() );
        player.setCurrentPosition(collisionBox.getX() - 20, collisionBox.getY());
    }

    private boolean detectGroundCollision(float x, float y) {
        return gameMap.detectGroundCollision(x, y);
    }

    public void draw() {
        gameMap.getMapRenderer().setView(camera);
        gameMap.getMapRenderer().render();

        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.identity();
        shapeRenderer.setColor(0, 1, 0, 1);
        shapeRenderer.rect(collisionBox.getX(), collisionBox.getY(), collisionBox.getWidth(), collisionBox.getHeight());
        shapeRenderer.end();
    }

    public Player getPlayer() {
        return player;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public float getAnimationRunTime() {
        return animationRunTime;
    }

    public InputManager getInputManager() {
        return inputManager;
    }
}
