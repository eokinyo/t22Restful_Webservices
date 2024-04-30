package services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import data.*;

@Path("/lego")
public class LegoService {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("lego");

	@Path("/getlego")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Lego getLego() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Lego l = em.find(Lego.class, 1);
		em.getTransaction().commit();
		return l;
	}

	@Path("/setvalues")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public LegoSetting setValues(LegoSetting ls) {
		System.out.println(ls);
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(ls);
		em.getTransaction().commit();
		return ls;
	}

	@Path("/getvalues")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LegoSetting getValues() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list = q.getResultList();
		em.getTransaction().commit();
		return list.get(0);
	}

	@Path("/getspeed")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getSpeed() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list = q.getResultList();
		em.getTransaction().commit();
		return "" + list.get(0).getSpeed() + "#";
	}

	@Path("/getdistance")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getDistance() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list = q.getResultList();
		em.getTransaction().commit();
		return "" + list.get(0).getDistance();
	}

	@Path("/getintensity")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIntensity() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list = q.getResultList();
		em.getTransaction().commit();
		return "" + list.get(0).getIntensity();
	}

	@Path("/getinformations")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getInformations() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select s from LegoSetting s order by s.id desc").setMaxResults(1);
		List<LegoSetting> list = q.getResultList();
		em.getTransaction().commit();
		return "" + list.get(0).getLego().getRun() + "#" + list.get(0).getSpeed() + "#" + list.get(0).getIntensity()
				+ "#" + list.get(0).getDistance() + "#"+ list.get(0).getDirection() + "#";
	}

	@Path("/updaterun")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateRun(Lego lego) {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Lego existingLego = em.find(Lego.class, 1);
			if (existingLego != null) {
				existingLego.setRun(lego.getRun());
				em.merge(existingLego);
			} else {
				return Response.status(Response.Status.NOT_FOUND).entity("Lego not found").build();
			}
			em.getTransaction().commit();
			return Response.ok(existingLego).build();
		} catch (Exception e) {
			if (em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating run status").build();
		} finally {
			em.close();
		}
	}
	
	@Path("/setDirection")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setDirection(LegoSetting legoSetting) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();
	        Lego lego = em.find(Lego.class, 1);
	        if (lego == null) {        
	            return Response.status(Response.Status.BAD_REQUEST).entity("Lego not found").build();
	        }    
	        legoSetting.setLego(lego); 
	        legoSetting.setId(0); 
	        em.persist(legoSetting);
	        em.getTransaction().commit();
	        return Response.status(Response.Status.CREATED).entity(legoSetting).build();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    } finally {
	        em.close();
	    }
	}


	
	@POST
	@Path("/setfeedbackcollection")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createLegoData(RobotInfo ri) {
	    EntityManager em = emf.createEntityManager();
	    try {
	        em.getTransaction().begin();
	        if (ri != null) {
	            em.persist(ri);
	            em.getTransaction().commit();
	            em.refresh(ri); 
	            return Response.status(Response.Status.CREATED).entity(ri).build();
	        } else {
	            em.getTransaction().rollback(); 
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        }
	    } catch (Exception e) {
	        em.getTransaction().rollback(); 
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    } finally {
	        em.close();
	    }
	}

	   
	@GET
	@Path("/getCurrentDatas")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLegoData() {
	    EntityManager em = emf.createEntityManager();
	  
	    try {
	        Query q = em.createQuery("select f from FeedbackCollection f order by f.id desc").setMaxResults(1);
	        List<RobotInfo> dataList = q.getResultList();
	        
	        if (dataList != null && !dataList.isEmpty()) {
	            String result = "" + dataList.get(0).getCurrentSpeed() + "#" +
	                            dataList.get(0).getCurrentIntensity() + "#" +
	                            dataList.get(0).getDistanceTravelled() + "#" +
	                            dataList.get(0).getTimeTaken() + "#";
	            return Response.status(Response.Status.OK).entity(result).build();
	        } else {
	            return Response.status(Response.Status.NO_CONTENT).build();
	        }
	    } catch (Exception e) {
	      
	        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	    } finally {
	        em.close(); 
	    }
	}

}
