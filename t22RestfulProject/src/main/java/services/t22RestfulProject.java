package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import conn.Connections;
import data.Robot;

@Path("/t22RestfulProject")
public class t22RestfulProject {
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;

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

	@GET
	@Path("/getrobotsbyspeed/{p1}/{p2}")
	@Produces(MediaType.APPLICATION_JSON)

	public ArrayList<Robot> getRobotsByName(@PathParam("p1") float low, @PathParam("p1") float high) {
		ArrayList<Robot> list = getRobotList();
		ArrayList<Robot> result = new ArrayList<>();
		for (Robot d : list) {
			if (d.getSpeed() >= low && d.getSpeed() <= high) {
				result.add(d);
			}
		}
		return result;
	}

	@GET
	@Path("/readall")
	public void readAll() {
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/printrobots.jsp");
		request.setAttribute("Robots", getRobotList());
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/addrobotbyget")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Robot> addRobotByGet(@QueryParam("id") int id, @QueryParam("name") String name,
			@QueryParam("speed") float speed) {
		Robot r = new Robot(id, name, speed);
		ArrayList<Robot> list = getRobotList();
		list.add(r);
		return list;

	}

	@POST
	@Path("/addrobotbypost")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes("application/x-www-form-urlencoded")
	public void addRobotByPost(@FormParam("id") int id, @FormParam("name") String name, @FormParam("speed") float speed,
			@DefaultValue("-1") @FormParam("iswhite") int iw) {
		Robot r = new Robot(id, name, speed);
		r.setIswhite(iw);
		ArrayList<Robot> list = getRobotList();
		list.add(r);

		RequestDispatcher rd = request.getRequestDispatcher("/jsp/printrobots.jsp");
		request.setAttribute("Robots", list);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@POST
	@Path("/addrobot")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void addRobot(@FormParam("name") String name, @FormParam("speed") String speed,
			@FormParam("iswhite") int iw) {
		/* ArrayList<Robot> list=new ArrayList<>(); */
		speed = speed.replace(",", ".");// If user uses comma
		/*Robot r = new Robot(name, speed, iw);*/
		Connection conn = null;
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {
			/*
			 * r=new Robot(0, "Adding robots failed", 0); //For debugging if connection
			 * fails //list.add(r); return r;
			 */
		}
		// Using normal Prepared statement to add the values into the database
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into robot(name, speed, iswhite) values(?,?,?)");
			pstmt.setString(1, name);
			pstmt.setString(2, speed);
			pstmt.setInt(3, iw);
			pstmt.executeUpdate();

			// Using common statement while reading, because there are no variables in the
			// sql statement
			/*
			 * Statement stmt=conn.createStatement(); ResultSet
			 * RS=stmt.executeQuery("select * from robot"); while (RS.next()) { Robot
			 * robot=new Robot(); robot.setId(RS.getInt("id"));
			 * robot.setBreed(RS.getString("name")); robot.setWeight(RS.getFloat("speed"));
			 * list.add(robot); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		readRobots();
	}
	
	@POST
	@Path("/savejsonrobot")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Robot saveJsonRobot(Robot robot) {
		Connection conn = null;
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {
			/*
			 * r=new Robot(0, "Adding robots failed", 0); //For debugging if connection
			 * fails //list.add(r); return r;
			 */
		}
		// Using normal Prepared statement to add the values into the database
		try {
			PreparedStatement pstmt = conn.prepareStatement("insert into robot(name, speed, iswhite) values(?,?,?)");
			pstmt.setString(1, robot.getName());
			pstmt.setFloat(2, robot.getSpeed());
			pstmt.setInt(3, robot.getIswhite());
			pstmt.executeUpdate();

			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return robot;
	}
	
	
	@POST
	@Path("/updaterobot")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void updateRobot(@FormParam("id") int id, @FormParam("name") String name, @FormParam("speed") String speed,
			@FormParam("iswhite") int iw) {
		speed = speed.replace(",", ".");// If user uses comma
		Robot r = new Robot(name, speed, iw);
		Connection conn = null;
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {
			
		}
		// Using normal Prepared statement to add the values into the database
		try {
			PreparedStatement pstmt = conn.prepareStatement("update robotset name=?, speed=?, iswhite=? where id =?");
			pstmt.setString(1, name);
			pstmt.setString(2, speed);
			pstmt.setInt(3, iw);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		readRobots();
	}

	@GET
	@Path("/deleterobot/{id}")
	public void deleteRobot(@PathParam("id") int id) {
		Connection conn = null;
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {
			/*
			 * r=new Robot(0, "Adding robots failed", 0); //For debugging if connection
			 * fails //list.add(r); return r;
			 */
		}
		// Using normal Prepared statement to add the values into the database
		try {
			PreparedStatement pstmt = conn.prepareStatement("delete from robot where id = ?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

			// Using common statement while reading, because there are no variables in the
			// sql statement
			/*
			 * Statement stmt=conn.createStatement(); ResultSet
			 * RS=stmt.executeQuery("select * from robot"); while (RS.next()) { Robot
			 * robot=new Robot(); robot.setId(RS.getInt("id"));
			 * robot.setBreed(RS.getString("name")); robot.setWeight(RS.getFloat("speed"));
			 * list.add(robot); }
			 */
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		readRobots();
	}

	@GET
	@Path("/readforupdate/{id}")
	public void readForUpdate(@PathParam("id") int id) {
		Connection conn = null;
		Robot r = new Robot();
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {

		}
		try {
			PreparedStatement pstmt = conn.prepareStatement("select * from robot where id = ?");
			pstmt.setInt(1, id);
			ResultSet RS = pstmt.executeQuery();
			if (RS.next()) {
				r.setId(RS.getInt("id"));
				r.setName(RS.getString("name"));
				r.setSpeed(RS.getFloat("speed"));
				r.setIswhite(RS.getInt("iswhite"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/updaterobot.jsp");
		request.setAttribute("robot", r);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GET
	@Path("/readrobots")
	public void readRobots() {
		ArrayList<Robot> list = new ArrayList<>();
		Connection conn = null;
		try {
			conn = Connections.getConnection();
		} catch (Exception e) {

		}
		// Using normal Prepared statement to add the values into the database
		try {

			// Using common statement while reading, because there are no variables in the
			// sql statement
			Statement stmt = conn.createStatement();
			ResultSet RS = stmt.executeQuery("select * from robot");
			while (RS.next()) {
				Robot robot = new Robot();
				robot.setId(RS.getInt("id"));
				robot.setName(RS.getString("name"));
				robot.setSpeed(RS.getFloat("speed"));
				robot.setIswhite(RS.getInt("iswhite"));
				list.add(robot);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// Before the function ends, the connection should be closed
		// This closing just returns the connection to the connection pool
		finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/jsp/printrobots.jsp");
		request.setAttribute("Robots", list);
		try {
			rd.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Robot> getRobotList() {
		ArrayList<Robot> list = new ArrayList<>();
		list.add(new Robot(1, "Haris", 30));
		list.add(new Robot(2, "Hari", 25));
		list.add(new Robot(3, "Har", 40));
		list.add(new Robot(4, "Ha", 20));
		list.add(new Robot(5, "H", 1));
		return list;
	}

}
