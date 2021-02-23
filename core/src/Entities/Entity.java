package Entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import Map.GameMap;
import Map.TileType;

public abstract class Entity {
	protected Vector2 pos;
	protected EntityType type;
	protected float velocityY;
	protected GameMap map;
	protected boolean grounded = false;
	
	public Entity(float x,float y,EntityType type, GameMap map) {
		this.pos = new Vector2(x,y);
		this.type = type;
		this.map = map;
	}
	
	public void update(float deltaTime,float gravity) {
		if(!onGroundRight() && !onGroundLeft()) {
			pos.y -= 2 ;
		}
		
		moveY(deltaTime,gravity);
	}
	
	public abstract void render(SpriteBatch batch);
	
	protected void moveY(float deltaTime,float gravity) {
		float newY = pos.y;
		this.velocityY += gravity * deltaTime * getWeight();
		newY += this.velocityY * deltaTime;
		
		//Collision
		if(map.doesRectCollideWithMap(pos.y, newY, getWidth(), getHeight())) {
			if(velocityY < 0) {
				this.pos.y = (float) Math.floor(pos.y);
				grounded = true;
			}
			this.velocityY = 0;
		}else {
			this.pos.y = newY;
			grounded = false;
		}
	}

	public boolean onGroundRight(){
		TileType tile = map.getTileTypeByLocation(1,pos.x+13,pos.y-1);
		if(tile == null) {
			return false;
		}else {
			return true;
		}
	}
	public boolean onGroundLeft(){
		TileType tile = map.getTileTypeByLocation(1,pos.x,pos.y-1);
		if(tile == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public void moveRight(float amount) {
		float newX = pos.x + amount;
		if(!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
		}else {
			this.pos.y = pos.y + 13;
		}
	}
	public void moveLeft(float amount) {
		float newX = pos.x + amount;
		if(!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
		}else {
			this.pos.y = pos.y + 13;
		}
	}
	
	protected void moveX(float amount) {
		/*float newX = pos.x + amount;
		if(!map.doesRectCollideWithMap(newX, pos.y, getWidth(), getHeight())) {
			this.pos.x = newX;
		}*/
	}

	public Vector2 getPos() {
		return pos;
	}
	
	public float getX() {
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}

	public EntityType getType() {
		return type;
	}

	public boolean isGrounded() {
		return grounded;
	}
	
	public int getWidth() {
		return type.getWidth();
	}
	public int getHeight() {
		return type.getHeight();
	}
	public float getWeight() {
		return type.getWeight();
	}
}
