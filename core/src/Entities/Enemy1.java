package Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import Map.GameMap;

public class Enemy1 extends Entity {
	private static final int SPEED = 80;
	private static final int JUMP_VELOCITY = 6;
	Texture image;
	private boolean direction = false;

	public Enemy1(float x, float y, GameMap map) {
		super(x, y, EntityType.PLAYER, map);
		image = new Texture("player.png");//Enemy Image
	}
	
	@Override //Movement
	public void update(float deltaTime, float gravity) {
		/*if(Gdx.input.isKeyPressed(Keys.SPACE) && grounded) {
			this.velocityY += JUMP_VELOCITY * getWeight();
		}*/
		super.update(deltaTime, gravity);
		
		move();
		
		if(Gdx.input.isKeyPressed(Keys.A)) {
			//moveX(-SPEED * deltaTime);
			moveLeft(-SPEED * deltaTime);
		}
		if (Gdx.input.isKeyPressed(Keys.D)){
			//moveX(SPEED * deltaTime);
			moveRight(SPEED * deltaTime);
		}
	}

	public void move(){
		direction();
		if(direction) {
			moveRight(2);
		}else if(!direction) {
			moveLeft(-2);
		}
	}
	
	public void direction(){
		if(pos.x>map.getPixelWidth() - 25) {
			direction = false;
		}else if(pos.x<1){
			direction = true;
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		batch.draw(image,pos.x,pos.y,getWidth(),getHeight());
	}

}
