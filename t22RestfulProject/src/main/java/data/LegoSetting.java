package data;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="legosetting")
public class LegoSetting {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int speed;
	private int intensity;
	private int distance;
	private int direction;
	private java.sql.Timestamp time=new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	/*
	 * Here is the code for creating the legosetting table
	 * CREATE TABLE legosetting (
    id INT AUTO_INCREMENT PRIMARY KEY,
    speed INT,
    intensity INT,
    distance INT,
    direction INT,
    time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    lego_id INT,
    FOREIGN KEY (lego_id) REFERENCES lego(lego_id)
	);
*/
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonBackReference //To handle converting object to JSON and backwards
	@JoinColumn(name="lego_id")	
	private Lego lego;
	
	public LegoSetting() {
		super();
	}
	public LegoSetting(int id, int speed, int direction, Timestamp time, int intensity, int distance) {
		super();
		this.id = id;
		this.speed = speed;
		this.time = time;
		this.intensity = intensity;
		this.distance = distance;
		this.direction = direction;
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public java.sql.Timestamp getTime() {
		return time;
	}
	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}
	public int getIntensity() {
		return intensity;
	}
	public void setIntensity(int intensity) {
		this.intensity = intensity;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Lego getLego() {
		return lego;
	}
	public void setLego(Lego lego) {
		this.lego = lego;
	}
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	@Override
	public String toString() {
		return id+"#"+speed+"#"+intensity+"#"+distance+"#"+direction+"#"+time;
	}
	
}
