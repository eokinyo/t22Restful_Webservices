package data;

public class Robot {
	private int id;
	private String name;
	private float speed;
	
	
	
	public Robot() {
		super();
	}
	
	public Robot(int id, String name, float speed) {
		super();
		this.id = id;
		this.name = name;
		this.speed = speed;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	

}
