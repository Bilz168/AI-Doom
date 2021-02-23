package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Map.GameMap;
import Map.TileType;

public class Circle extends Entity{
	Texture image;
	public boolean land = false;
	
	public Circle(float x, float y, GameMap map) {
		super(x, y, EntityType.Circle, map);
		image = new Texture("Circle.png");
	}
	
	public void update(float enemyX,float enemyY) {
		TileType tile = map.getTileTypeByLocation(1,pos.x,pos.y-6);
		if(tile == null) {
			this.pos.y -= 2;
		}else if(tile.isCollidable()) {
			System.out.println("e: "+enemyX);
			this.pos.x = enemyX;
			this.pos.y = enemyY+740;
			System.out.println(pos.x);
		}else {
			this.pos.y -= 2;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image,pos.x,pos.y,getWidth(),getHeight());
	}
}







