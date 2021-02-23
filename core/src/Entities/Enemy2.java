package Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

import Map.GameMap;

public class Enemy2 extends Entity{
	Texture image;
	private boolean direction = false;
	Circle circle;

	public Enemy2(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER2, map);
		image = new Texture("Skeleton.png");//Enemy2 Image
		circle = new Circle(x,y,map);
	}

	public void update(float deltaTime,float gravity) {
		//super.update(deltaTime, gravity);
		move();
		circle.update(this.pos.x,this.pos.y);
	}
	
	public void move(){
		direction();
		if(!direction) {
			this.pos.x += 5;
		}else if(direction) {
			this.pos.x -= 5;
		}
	}
	
	public void direction(){
		if(this.pos.x > map.getPixelWidth()-55) {
			direction = true;
		}else if(this.pos.x < 5) {
			direction = false;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image,pos.x,pos.y,getWidth(),getHeight());
	}
	
	
	
}








