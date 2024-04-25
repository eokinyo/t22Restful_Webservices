package data;

public class Robot {
	private int id;
	private String name;
	private float speed;
	private int iswhite;
	
	
	
	

	public Robot() {
		super();
	}
	
	public Robot(int id, String name, float speed) {
		super();
		this.id = id;
		this.name = name;
		this.speed = speed;
	}
	
	
	public Robot(String name, String speed, int iw) {
		// TODO Auto-generated constructor stub
		this.name=name;
		try {
			this.speed=Float.parseFloat(speed);
		}
		catch(NumberFormatException e) {
			
		}
		
		this.iswhite=iw;
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
	
	public int getIswhite() {
		return iswhite;
	}

	public void setIswhite(int iswhite) {
		this.iswhite = iswhite;
	}
	
	public String toString() {
		return this.id+"/"+this.name+"/"+this.speed+"/"+this.iswhite;
	}
	

}
