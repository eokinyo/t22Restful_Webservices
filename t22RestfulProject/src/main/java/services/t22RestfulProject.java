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
        Robot r = new Robot(1, "Haris", 200, 0, 0.0f, 0);
        return r;
    }

    @POST
    @Path("/addrobot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Robot> addRobot(Robot t22) {
        ArrayList<Robot> list = new ArrayList<>();

        Connection conn = null;
        try {
            conn = Connections.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO robot(name, speed, iswhite, angle, color) VALUES(?, ?, ?, ?, ?)");
            pstmt.setString(1, t22.getName());
            pstmt.setFloat(2, t22.getSpeed());
            pstmt.setInt(3, t22.getIswhite());
            pstmt.setFloat(4, t22.getAngle());
            pstmt.setFloat(5, t22.getColor());
            pstmt.executeUpdate();

            // Read all robots from the database
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM robot");
            while (rs.next()) {
                Robot robot = new Robot();
                robot.setId(rs.getInt("id"));
                robot.setName(rs.getString("name"));
                robot.setSpeed(rs.getFloat("speed"));
                robot.setIswhite(rs.getInt("iswhite"));
                robot.setAngle(rs.getFloat("angle"));
                robot.setColor(rs.getFloat("color"));
                list.add(robot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    @POST
    @Path("/savejsonrobot")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Robot saveJsonRobot(Robot robot) {
        Connection conn = null;
        try {
            conn = Connections.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO robot(name, speed, iswhite, angle, color) VALUES(?, ?, ?, ?, ?)");
            pstmt.setString(1, robot.getName());
            pstmt.setFloat(2, robot.getSpeed());
            pstmt.setInt(3, robot.getIswhite());
            pstmt.setFloat(4, robot.getAngle());
            pstmt.setFloat(5, robot.getColor());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return robot;
    }

    @POST
    @Path("/updaterobot")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateRobot(@FormParam("id") int id, @FormParam("name") String name, @FormParam("speed") float speed,
                            @FormParam("iswhite") int iw, @FormParam("angle") float angle, @FormParam("color") float color) {
        Connection conn = null;
        try {
            conn = Connections.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("UPDATE robot SET name=?, speed=?, iswhite=?, angle=?, color=? WHERE id=?");
            pstmt.setString(1, name);
            pstmt.setFloat(2, speed);
            pstmt.setInt(3, iw);
            pstmt.setFloat(4, angle);
            pstmt.setFloat(5, color);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GET
    @Path("/deleterobot/{id}")
    public void deleteRobot(@PathParam("id") int id) {
        Connection conn = null;
        try {
            conn = Connections.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM robot WHERE id = ?");
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @GET
    @Path("/readforupdate/{id}")
    public void readForUpdate(@PathParam("id") int id) {
        Connection conn = null;
        Robot r = new Robot();
        try {
            conn = Connections.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM robot WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setSpeed(rs.getFloat("speed"));
                r.setIswhite(rs.getInt("iswhite"));
                r.setAngle(rs.getFloat("angle"));
                r.setColor(rs.getFloat("color"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        RequestDispatcher rd = request.getRequestDispatcher("/jsp/updaterobot.jsp");
        request.setAttribute("robot", r);
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/readrobots")
    @Produces(MediaType.TEXT_PLAIN)
    public String readRobots() {
        ArrayList<Robot> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Connections.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM robot");
            while (rs.next()) {
                Robot robot = new Robot();
                robot.setId(rs.getInt("id"));
                robot.setName(rs.getString("name"));
                robot.setSpeed(rs.getFloat("speed"));
                robot.setIswhite(rs.getInt("iswhite"));
                robot.setAngle(rs.getFloat("angle"));
                robot.setColor(rs.getFloat("color"));
                list.add(robot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Return information about the last robot added
        Robot finalRobot = list.get(list.size() - 1);
        return finalRobot.getName() + "#" + finalRobot.getSpeed() + "#" + finalRobot.getIswhite() + "#" + finalRobot.getAngle() + "#" + finalRobot.getColor();
    }

    // Define any additional helper methods here as needed

    public ArrayList<Robot> getRobotList() {
        ArrayList<Robot> list = new ArrayList<>();
        list.add(new Robot(1, "K", 30, 0, 0.0f, 0.0f));
        list.add(new Robot(2, "Hari", 25, 0, 0.0f, 0.0f));
        list.add(new Robot(3, "Har", 40, 0, 0.0f, 0.0f));
        list.add(new Robot(4, "Ha", 20, 0, 0.0f, 0.0f));
        list.add(new Robot(5, "H", 1, 0, 0.0f, 0.0f));
        return list;
    }
    
    @GET
    @Path("/getallrobots")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Robot> getAllRobots() {
        ArrayList<Robot> robots = new ArrayList<>();
        Connection conn = null;
        try {
            conn = Connections.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM robot");
            while (rs.next()) {
                Robot robot = new Robot();
                robot.setId(rs.getInt("id"));
                robot.setName(rs.getString("name"));
                robot.setSpeed(rs.getFloat("speed"));
                robot.setIswhite(rs.getInt("iswhite"));
                robot.setAngle(rs.getFloat("angle"));
                robot.setColor(rs.getFloat("color"));
                robots.add(robot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return robots;
    }
}