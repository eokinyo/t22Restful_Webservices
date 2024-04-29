package data;

public class Robot {
	/*private int id;
	private String name;
	private float speed;
	private int iswhite;*/
	private static int speed;
	private static int motorASpeed;
	private static int motorBSpeed;
	private static int direction;
	private static int run=1;
	private static int intensity;
	private static int distance;
	private static long time;
	
	private static float currentThreshold = 0;
	private static float currentDistance = 0;
	private static int current_CMD = 1;

	private static long startTime = 0;
	private static long endTime = 0;
	private static int obstacleCount = 0;

	private  static boolean avoidingObstacle = false;
	private  static boolean stopThreads = false;

	public final static int followLine = 1;
	public final static int end = 2;
	public final static int avoid = 3;

	
	public static int getSpeed() {
		return speed;
	}
	public static void setSpeed(int speed) {
		Robot.speed = speed;
	}
	public static void setSpeed(String speed) {
	    try {
	        Robot.speed = Integer.parseInt(speed);
	    }
	    catch(NumberFormatException e) {
	        System.out.println("Invalid format for speed: " + speed);
	        e.printStackTrace();
	    }
	    catch(Exception e) {
	        System.out.println("Unexpected exception when setting speed: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	public static int getMotorASpeed() {
		return motorASpeed;
	}
	public static void setMotorASpeed() {
	    if (Robot.direction >= 0) {
	        Robot.motorASpeed = Robot.speed + Robot.direction;
	    } else {
	        Robot.motorASpeed = Robot.speed;
	    }
	}

	public static int getMotorBSpeed() {
		return motorBSpeed;
	}
	public static void setMotorBSpeed() {
	    if (Robot.direction < 0) {
	        Robot.motorBSpeed = Robot.speed + Robot.direction;
	    } else {
	        Robot.motorBSpeed = Robot.speed;
	    }
	}
	public static int getDirection() {
		return direction;
	}
	public static void setDirection(int direction) {
		Robot.direction = direction;
	}
	public static void setDirection(String direction) {
		try {
			Robot.direction = Integer.parseInt(direction);
		}
		catch(Exception e) {
			//direction remains unchanged
		}
	}
	public static int getRun() {
		return run;
	}
	public static void setRun(int run) {
		Robot.run = run;
	}
	public static void setRun(String run) {
		try {
			Robot.run = Integer.parseInt(run);
		}
		catch(Exception e) {
			//run remains unchanged
		}
	}
	
	public static int getIntensity() {
		return intensity;
	}
	public static void setIntensity(int intensity) {
		Robot.intensity = intensity;
	}
	public static void setIntensity(String intensity) {
		try {
			Robot.intensity = Integer.parseInt(intensity);
		}
		catch(Exception e) {
			//intensity remains unchanged
		}
	}
	public static int getDistance() {
		return distance;
	}
	public static void setDistance(int distance) {
		Robot.distance = distance;
	}
	public static void setDistance(String distance) {
		try {
			Robot.distance = Integer.parseInt(distance);
		}
		catch(Exception e) {
			//intensity remains unchanged
		}
	}
	public static float getCurrentThreshold() {
		return currentThreshold;
	}
	public static void setCurrentThreshold(float currentThreshold) {
		Robot.currentThreshold = currentThreshold;
	}
	public static float getCurrentDistance() {
		return currentDistance;
	}
	public static void setCurrentDistance(float currentDistance) {
		Robot.currentDistance = currentDistance;
	}
	public static long getTime() {
		return time;
	}
	public static void setTime(long time) {
		Robot.time = time;
	}
	public static void setTime(String time) {
		try {
			Robot.time =  Long.parseLong(time);
		}
		catch(Exception e) {
			//intensity remains unchanged
		}
	}
	
	
	public static synchronized int getObstacleCount() {
		return obstacleCount;
	}

	public static synchronized void setObstacleCount(int obstacleCount) {
		Robot.obstacleCount = obstacleCount;
		
	}

	public static synchronized int getCurrent_CMD() {
		return current_CMD;

	}

	public static synchronized void setCurrent_CMD(int current_CMD) {
		Robot.current_CMD = current_CMD;
		
	}

	public static synchronized long getStartTime() {
		return startTime;

	}

	public static synchronized void setStartTime(long startTime) {
		Robot.startTime = startTime;
	}

	public static synchronized long getEndTime() {
		return endTime;

	}

	public static synchronized void setEndTime(long endTime) {
		Robot.endTime = endTime;
	}
	public static synchronized boolean isAvoidingObstacle() {
		return avoidingObstacle;

	}

	public static synchronized void setAvoidingObstacle(boolean avoidingObstacle) {
		Robot.avoidingObstacle = avoidingObstacle;

	}

	public static synchronized boolean isStopThreads() {
		return stopThreads;

	}

	public static synchronized void setStopThreads(boolean stopThreads) {
		Robot.stopThreads = stopThreads;
	}

	public static synchronized void recordObstacleDetection() {

		if (!isAvoidingObstacle()) {
			obstacleCount++;
			avoidingObstacle = true;

		}
	}
	
	
	
	

	/*public Robot() {
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
	}*/
	

}
