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
	private int direction;//0=forward, 1=backward
	private java.sql.Timestamp time=new java.sql.Timestamp(Calendar.getInstance().getTimeInMillis());
	
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonBackReference //To handle converting object to JSON and backwards
	@JoinColumn(name="legoid")	
	private Lego lego;
	
	public LegoSetting() {
		super();
	}
	public LegoSetting(int id, int speed, int direction, Timestamp time) {
		super();
		this.id = id;
		this.speed = speed;
		this.direction = direction;
		this.time = time;
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
	public int getDirection() {
		return direction;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public java.sql.Timestamp getTime() {
		return time;
	}
	public void setTime(java.sql.Timestamp time) {
		this.time = time;
	}
	public Lego getLego() {
		return lego;
	}
	public void setLego(Lego lego) {
		this.lego = lego;
	}
	@Override
	public String toString() {
		return id+"#"+speed+"#"+direction+"#"+time;
	}
	
}
