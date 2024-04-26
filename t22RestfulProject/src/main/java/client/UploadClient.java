package client;

import java.io.File;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.*;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class UploadClient {

	public static void main(String[] args) 
	{
		try {
		    Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
		 
		    FileDataBodyPart filePart = new FileDataBodyPart("file", new File("C:/temp/ukkometso.jpg"));
		    FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
		    FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
		      
		    WebTarget target = client.target("http://localhost:8081/rest/uploadservice/fileupload");
		    Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
		     
		    //Use response object to verify upload success
		    if (Response.Status.OK.toString().equals(response.getStatusInfo().toString())) {
		    	System.out.println("Upload OK!");
		    	System.out.println(response.readEntity(String.class));
		    }
		    else {
		    	System.out.println("Upload NOT OK!");
		    }
		     
		    formDataMultiPart.close();
		    multipart.close();
		}
		catch(Exception e) {
			System.out.println("Something went wrong!!");
		}
	}
}