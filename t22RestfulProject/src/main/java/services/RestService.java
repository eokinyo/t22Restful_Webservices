package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import data.Robot;

@Path("/t22RestfulProject")
public class RestService {
	@GET
	@Path("/info")
	@Produces(MediaType.TEXT_PLAIN)
	public String info() {
		return "<h1>t22RestfulProject</h1>";
	}
	
	
	@GET
	@Path("/getrobot")
	@Produces(MediaType.APPLICATION_JSON)
	public Robot getRobot() {
		Robot r = new Robot(1, "Haris", 200);
		return r;
	}
	
	@GET
	@Path("/getonerobot/{par}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public Robot getOneRobot(@PathParam("par") int id) {
		ArrayList<Robot> list = getRobotList();
		return list.get(id);
	}
	
	
	/*@GET
	@Path("/getRobotsbyspeed/{p1}/{p2}")
	@Produces(MediaType.APPLICATION_JSON)
	
	public ArrayList<Robot> getRobotsByName(@PathParam("p1") float low, @PathParam("p1") float high) {
		ArrayList<Robot> list = getRobotList();
		ArrayList<Robot> result = new ArrayList<>();
		for (Robot d:list) {
			if (d.getSpeed() >= low && d.getSpeed() <= high) {
				result.add(d);
			}
		}
		return result;
	}
	
	@GET
	@Path("/readall")
	public void readAll() {
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/printRobots.jsp");
		request.setAttribute("Robots", getRobotList());
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/addRobotbyget")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Robot> addRobotByGet(@QueryParam("id") int id,@QueryParam("breed") String breed,
			@QueryParam("weight") float weight){
		Robot d = new Robot(id, breed, weight);
		ArrayList<Robot> list = getRobotList();
		list.add(d);
		return list;
		
	}
	
	@POST
	@Path("/addRobotbypost")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public void addRobotByPost(@FormParam("id") int id,@FormParam("breed") String breed,
			@FormParam("weight") float weight, @DefaultValue("-1") @FormParam("purebred") int pb){
		Robot d = new Robot(id, breed, weight);
		d.setPurebred(pb);
		ArrayList<Robot> list = getRobotList();
		list.add(d);
		
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/printRobots.jsp");
		request.setAttribute("Robots", list);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public ArrayList<Robot> getRobotList(){
		ArrayList<Robot> list = new ArrayList<>();
		list.add(new Robot(1, "Haris", 30));
		list.add(new Robot(2, "Hari", 25));
		list.add(new Robot(3, "Har", 40));
		list.add(new Robot(4, "Ha", 20));
		list.add(new Robot(5, "H", 1));
		return list;
	}

}
