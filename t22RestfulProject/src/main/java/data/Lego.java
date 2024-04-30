package data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="lego")
public class Lego{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int lego_id;
	private String name="";
	private int run =1;
	
	/*
	 * The code to create the legotable
	 * CREATE TABLE lego (
    lego_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    run INT DEFAULT 1
	);*/

	@OneToMany(mappedBy="lego", fetch=FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JsonManagedReference //To handle converting object to JSON and backwards
	private List<LegoSetting> legoSetting;
	
	public List<LegoSetting> getLegoSetting() {
		return legoSetting;
	}
	public void setLegoSetting(List<LegoSetting> legoSetting) {
		this.legoSetting = legoSetting;
	}
	public Lego() {
		super();
	}
	public Lego(int lego_id, String name) {
		super();
		this.lego_id = lego_id;
		this.name = name;
	}
	public int getId() {
		return lego_id;
	}
	public void setId(int lego_id) {
		this.lego_id = lego_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRun() {
		return run;
	}
	public void setRun(int run) {
		this.run = run;
	}
	@Override
	public String toString() {
		return lego_id+"#"+name;
	}
	
}
