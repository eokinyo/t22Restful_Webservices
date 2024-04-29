package app;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import data.AddData;
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
		
		//Begining of new lines(added - 29.4)
		RunLego runLego = new RunLego();
		ReadData readData = new ReadData();
//		ColorDetector colorDetector = new ColorDetector();
//		DistanceDetector distanceDetector  = new DistanceDetector();
		DataCollection dataCollection = new AddData();
		
		System.out.println("Run in Threads");
		System.out.println("Collection new Datas");
		
		Thread threadRun =  new Thread(runLego);
		Thread threadRead =  new Thread(readData);
//		Thread threadColor =  new Thread(colorDetector);
//		Thread threadDistance =  new Thread(distanceDetector);
		Thread threadCollection =  new Thread(dataCollection);
//		
		threadRun.start();
		threadRead.start();
//		threadColor.start();
//		threadDistance.start();
		threadCollection.start();
		
		Robot.setStarttTime(System.currentTimeMillis());
		
		//End of new lines(added - 29.4)
	}
}
