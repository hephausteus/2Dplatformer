package com.framework.engine;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class GameMap {

    private OrthogonalTiledMapRenderer tmr;
    private TiledMap map;
    private TiledMapTileLayer layer;

    public GameMap() {
        map = new TmxMapLoader().load("test.tmx");
        tmr = new OrthogonalTiledMapRenderer(map);
        layer = (TiledMapTileLayer)map.getLayers().get(0);
    }

    public OrthogonalTiledMapRenderer getMapRenderer() {
        return tmr;
    }

    public boolean detectGroundCollision(float x, float y) {

        int tileX1 = (int)(x / 32);
        int tileY1 = (int)(y / 32);

        TiledMapTileLayer.Cell cell = layer.getCell(tileX1, tileY1);
        Object property = cell.getTile().getProperties().get("collision");
        if (property != null) {
            if (property.equals("1")) {
                return true;
            }
        }

        return false;
    }
}
