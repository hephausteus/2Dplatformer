package com.framework.engine;


import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetsManager {

    public static final String[] STANCE_REGION_NAMES = new String[] {"stance1", "stance2", "stance3", "stance4"};
    public static final String[] RUN_REGION_NAMES = new String[] {"run1", "run2", "run3", "run4", "run5", "run6", "run7", "run8"};
    public static final String[] SWING_WEAPON_REGION_NAMES = new String[] {"swing_weapon1", "swing_weapon2", "swing_weapon3", "swing_weapon4"};
    public static final String[] BLOCK_REGION_NAMES = new String[] {"block1", "block2"};
    public static final String[] HIT_AND_DIE_REGION_NAMES = new String[] {"hit_and_die1", "hit_and_die2", "hit_and_die3", "hit_and_die4", "hit_and_die5", "hit_and_die6"};
    public static final String[] CAST_SPELL_REGION_NAMES = new String[] {"cast_spell1", "cast_spell2", "cast_spell3", "cast_spell4"};
    public static final String[] SHOOT_BOW_REGION_NAMES = new String[] {"shoot_bow1", "shoot_bow2", "shoot_bow3", "shoot_bow4"};
    public static final String[] WALK_REGION_NAMES = new String[] {"walk1", "walk2", "walk3", "walk4", "walk5", "walk6", "walk7", "walk8"};
    public static final String[] DUCK_REGION_NAMES = new String[] {"duck1", "duck2"};
    public static final String[] JUMP_AND_FALL_REGION_NAMES = new String[] {"jump_and_fall1", "jump_and_fall2", "jump_and_fall3", "jump_and_fall4", "jump_and_fall5", "jump_and_fall6"};
    public static final String[] ASCEND_STAIRS_REGION_NAMES = new String[] {"ascend_stairs1", "ascend_stairs2", "ascend_stairs3", "ascend_stairs4", "ascend_stairs5", "ascend_stairs6", "ascend_stairs7", "ascend_stairs8"};
    public static final String[] DESCEND_STAIRS_REGION_NAMES = new String[] {"descend_stairs1", "descend_stairs2", "descend_stairs3", "descend_stairs4", "descend_stairs5", "descend_stairs6", "descend_stairs7", "descend_stairs8"};
    public static final String[] STAND_REGION_NAMES = new String[] {"stand"};

    private Animation stanceAnimation;
    private Animation runAnimation;
    private Animation swingWeaponAnimation;
    private Animation blockAnimation;
    private Animation hitAndDieAnimation;
    private Animation castSpellAnimation;

    private Animation shootBowAnimation;
    private Animation walkAnimation;
    private Animation duckAnimation;
    private Animation jumpAndFallAnimation;
    private Animation ascendStairsAnimation;
    private Animation descendStairsAnimation;
    private Animation standAnimation;

    public AssetsManager() {

    }

    public void prepare() {
        TextureAtlas textureAtlas = new TextureAtlas("test.atlas");

        TextureRegion[] stanceFrames = new TextureRegion[4];
        TextureRegion[] runFrames = new TextureRegion[8];
        TextureRegion[] swingWeaponFrames = new TextureRegion[4];
        TextureRegion[] blockFrames = new TextureRegion[2];
        TextureRegion[] hitAndDieFrames = new TextureRegion[6];
        TextureRegion[] castSpellFrames = new TextureRegion[4];
        TextureRegion[] shootBowFrames = new TextureRegion[4];
        TextureRegion[] walkFrames = new TextureRegion[8];
        TextureRegion[] duckFrames = new TextureRegion[2];
        TextureRegion[] jumpAndFallFrames = new TextureRegion[6];
        TextureRegion[] ascendStairsFrames = new TextureRegion[8];
        TextureRegion[] descendStairsFrames = new TextureRegion[8];
        TextureRegion[] standFrames = new TextureRegion[1];

        for (int i = 0; i < 4; i++) {
            String path = STANCE_REGION_NAMES[i];
            stanceFrames[i] = textureAtlas.findRegion(path);
            path = SWING_WEAPON_REGION_NAMES[i];
            swingWeaponFrames[i] = textureAtlas.findRegion(path);
            path = CAST_SPELL_REGION_NAMES[i];
            castSpellFrames[i] = textureAtlas.findRegion(path);
            path = SHOOT_BOW_REGION_NAMES[i];
            shootBowFrames[i] = textureAtlas.findRegion(path);
        }

        for (int i = 0; i < 6; i++) {
            String path = HIT_AND_DIE_REGION_NAMES[i];
            hitAndDieFrames[i] = textureAtlas.findRegion(path);
            path = JUMP_AND_FALL_REGION_NAMES[i];
            jumpAndFallFrames[i] = textureAtlas.findRegion(path);
        }

        for (int i = 0; i < 8; i++) {
            String path = RUN_REGION_NAMES[i];
            runFrames[i] = textureAtlas.findRegion(path);
            path = WALK_REGION_NAMES[i];
            walkFrames[i] = textureAtlas.findRegion(path);
            path = ASCEND_STAIRS_REGION_NAMES[i];
            ascendStairsFrames[i] = textureAtlas.findRegion(path);
            path = DESCEND_STAIRS_REGION_NAMES[i];
            descendStairsFrames[i] = textureAtlas.findRegion(path);
        }

        for (int i = 0; i < 2; i++) {
            String path = BLOCK_REGION_NAMES[i];
            blockFrames[i] = textureAtlas.findRegion(path);
            path = DUCK_REGION_NAMES[i];
            duckFrames[i] = textureAtlas.findRegion(path);
        }

        standFrames[0] = textureAtlas.findRegion(STAND_REGION_NAMES[0]);

        stanceAnimation = new Animation(0.1f, stanceFrames);
        runAnimation = new Animation(0.1f, runFrames);
        swingWeaponAnimation = new Animation(0.1f, swingWeaponFrames);
        blockAnimation = new Animation(0.1f, blockFrames);
        hitAndDieAnimation = new Animation(0.1f, hitAndDieFrames);
        castSpellAnimation = new Animation(0.1f, castSpellFrames);
        shootBowAnimation = new Animation(0.2f, shootBowFrames);
        walkAnimation = new Animation(0.1f, walkFrames);
        duckAnimation = new Animation(0.1f, duckFrames);
        jumpAndFallAnimation = new Animation(0.2f, jumpAndFallFrames);
        ascendStairsAnimation = new Animation(0.1f, ascendStairsFrames);
        descendStairsAnimation = new Animation(0.1f, descendStairsFrames);
        standAnimation = new Animation(0.1f, standFrames);

        shootBowAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    }

    public Animation getStanceAnimation() {
        return stanceAnimation;
    }

    public Animation getRunAnimation() {
        return runAnimation;
    }

    public Animation getSwingWeaponAnimation() {
        return swingWeaponAnimation;
    }

    public Animation getBlockAnimation() {
        return blockAnimation;
    }

    public Animation getHitAndDieAnimation() {
        return hitAndDieAnimation;
    }

    public Animation getCastSpellAnimation() {
        return castSpellAnimation;
    }

    public Animation getShootBowAnimation() {
        return shootBowAnimation;
    }

    public Animation getWalkAnimation() {
        return walkAnimation;
    }

    public Animation getDuckAnimation() {
        return duckAnimation;
    }

    public Animation getJumpAndFallAnimation() {
        return jumpAndFallAnimation;
    }

    public Animation getAscendStairsAnimation() {
        return ascendStairsAnimation;
    }

    public Animation getDescendStairsAnimation() {
        return descendStairsAnimation;
    }

    public Animation getStandAnimation() {
        return standAnimation;
    }
}
