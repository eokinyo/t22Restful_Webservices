package app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import data.Robot;

public class RobotClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String uri = "http://127.0.0.1:8080/rest/t22RestfulProject/getonerobot/1";
	    
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Robot d=b.get(Robot.class);
		String s=b.get(String.class);
		System.out.println("Object: "+d);
		System.out.println("JSON: "+s);
	}
}
