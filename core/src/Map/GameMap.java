package Map;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Entities.Circle;
import Entities.Enemy1;
import Entities.Enemy2;
import Entities.Entity;
import javafx.geometry.Pos;

public abstract class GameMap {
	protected ArrayList<Entity> entities;
	
	public GameMap() {
		entities = new ArrayList<Entity>();
		entities.add(new Enemy1(190,600,this));
		entities.add(new Enemy2(190,700,this));
		entities.add(new Circle(190,700,this));
	}
	
	public void render(OrthographicCamera camera,SpriteBatch batch) {
		for(Entity entity : entities) {
			entity.render(batch);
		}
	}
	
	public void update(float delta) {
		for(Entity entity : entities) {
			//entity.update(delta, -9.8f);
			entity.update(delta, 0);
		}
	}
	
	
	public abstract void dispose();
	
	public TileType getTileTypeByLocation(int layer,float x,float y) {
		return getTileTypeByCoordinates(layer,(int) (x/TileType.TILE_SIZE),(int) (y/TileType.TILE_SIZE));
	}
	
	public abstract TileType getTileTypeByCoordinates(int layer,int col,int row);
	
	// COLLISION //
	public boolean doesRectCollideWithMap(float x,float y,int width,int height) {
		if(x<0 || y<0 || x+width>getPixelWidth() || y+height>getPixelHeight()) {
			return true;
		}
		
		for(int row=(int) (y/TileType.TILE_SIZE);row<Math.ceil((y+height)/TileType.TILE_SIZE);row++) {
			for(int col=(int) (x/TileType.TILE_SIZE);col<Math.ceil((x+width)/TileType.TILE_SIZE);col++) {
				for(int layer=0;layer<getLayers();layer++) {
					TileType type = getTileTypeByCoordinates(layer, col, row);
					if(type != null && type.isCollidable()) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getLayers();
	
	public int getPixelWidth() {
		return this.getWidth() * TileType.TILE_SIZE;
	}
	public int getPixelHeight() {
		return this.getHeight() * TileType.TILE_SIZE;
	}
}
