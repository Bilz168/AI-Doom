package Entities;

public enum EntityType {
	//(Width,Height,?)
	PLAYER("player",14,32,40),
	PLAYER2("player2",50,64,64),
	Circle("circle",25,25,10);
	
	private String id;
	private int width,height;
	private float weight;
	
	private EntityType(String id, int width, int height, float weight) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.weight = weight;
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float getWeight() {
		return weight;
	}
}
