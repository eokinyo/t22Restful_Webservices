package data;

public class Robot {
    private int id;
    private String name;
    private float speed;
    private int iswhite;
    private float angle; // New angle property

    // Existing constructors

    public Robot() {
        super();
    }

    public Robot(int id, String name, float speed) {
        super();
        this.id = id;
        this.name = name;
        this.speed = speed;
    }

    public Robot(int id, String name, float speed, int iswhite, float angle) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.iswhite = iswhite;
        this.angle = angle;
    }

    public Robot(String name, String speed, int iw, float angle) {
        this.name = name;
        try {
            this.speed = Float.parseFloat(speed);
        } catch (NumberFormatException e) {
            // Handle invalid input
        }
        this.iswhite = iw;
        this.angle = angle;
    }

    // Getter and setter methods for id, name, speed, iswhite, and angle

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

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    @Override
    public String toString() {
        return this.id + "/" + this.name + "/" + this.speed + "/" + this.iswhite + "/" + this.angle;
    }
}
