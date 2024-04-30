package data;

import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "robotinfo")
public class RobotInfo {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private int id;
 @Column(name = "currentSpeed")
 private int currentSpeed;
 @Column(name = "distanceTravelled")
 private int distanceTravelled;
 private int timeTaken;
 private int currentIntensity;
 
	public RobotInfo() {
		super();
	}
	public RobotInfo(int id, int currentSpeed, int timeTaken, int intensity, int distance) {
		super();
		this.id = id;
		this.currentSpeed = currentSpeed;
		this.timeTaken = timeTaken;
		this.currentIntensity = intensity;
		this.distanceTravelled = distance;
		/*
		 * This is the mysql statement to create the robotinfo table. 
		 * CREATE TABLE robotinfo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    currentSpeed INT,
    distanceTravelled INT,
    timeTaken INT,
    currentIntensity INT
);
*/
	}
 
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getCurrentSpeed() {
	return currentSpeed;
}
public void setCurrentSpeed(int currentSpeed) {
	this.currentSpeed = currentSpeed;
}

public int getTimeTaken() {
	return timeTaken;
}
public void setTimeTaken(int timeTaken) {
	this.timeTaken = timeTaken;
}
public int getDistanceTravelled() {
	return distanceTravelled;
}
public void setDistanceTravelled(int distanceTravelled) {
	this.distanceTravelled = distanceTravelled;
}
public int getCurrentIntensity() {
	return currentIntensity;
}
public void setCurrentIntensity(int currentIntensity) {
	this.currentIntensity = currentIntensity;
}

@Override
public String toString() {
	return id+"#"+currentSpeed+"#"+currentIntensity+"#"+distanceTravelled+"#"+timeTaken+"#";
}
}

