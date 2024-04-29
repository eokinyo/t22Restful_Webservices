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

@Entity
@Table(name="lego")
public class Lego {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name="";

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
	public Lego(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	@Override
	public String toString() {
		return id+"#"+name;
	}
	
}
