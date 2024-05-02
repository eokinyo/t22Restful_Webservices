package data;

public class Robot {
    private int id;
    private String name;
    private float speed;
    private int iswhite;
    private float angle; // Existing angle property
    private float color; // New color property

    // Existing constructors

    public Robot() {
        super();
    }

    public Robot(int id, String name, float speed) {
        super();
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.iswhite = 0; // Default iswhite to 0
        this.angle = 0.0f; // Default angle to 0.0
        this.color = 0.0f; // Default color to 0.0
    }

    public Robot(int id, String name, float speed, int iswhite, float angle, float color) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.iswhite = iswhite;
        this.angle = angle;
        this.color = color;
    }

    public Robot(String name, String speed, int iw, float angle, float color) {
        this.name = name;
        try {
            this.speed = Float.parseFloat(speed);
        } catch (NumberFormatException e) {
            // Handle invalid input
            this.speed = 0.0f;
        }
        this.iswhite = iw;
        this.angle = angle;
        this.color = color;
    }

    // Getter and setter methods for id, name, speed, iswhite, angle, and color

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

    public float getColor() {
        return color;
    }

    public void setColor(float color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return this.id + "/" + this.name + "/" + this.speed + "/" + this.iswhite + "/" + this.angle + "/" + this.color;
    }
}
