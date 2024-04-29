package app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;

import data.Robot;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter out=response.getWriter();
		String uri = "http://127.0.0.1:8080/rest/t22RestfulProject/getonerobot/3";
		Client c=ClientBuilder.newClient();
		WebTarget wt=c.target(uri);
		Builder b=wt.request();
		Robot r=b.get(Robot.class);
		String s=b.get(String.class);
		out.println("Object: "+r);
		out.println("JSON: "+s);
//test
  }
}